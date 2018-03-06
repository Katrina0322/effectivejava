# 设计一款分布式数据库 #
随着不断变化的需求和不断增加的数据量，越来越多的新型数据库，如hbase、mongodb、cassandra等，逐渐在一些领域取代了传统的关系型数据库，收到的越来越广泛的关注。
## 单机数据库设计 ##
对于任意一款软件,最重要的是它的设计思想和解决的问题.如程序语言一样,是面向对象,还是面向过程?是性能至上,还是开发效率优先?任何一种语言或软件,如果能解决一个甚至多个核心痛点,即使在其他方面有一些短板,也必然会受到追捧.除了设计思想,软件的架构也是十分重要的.初始架构的设计往往决定了它以后的发展.可扩展性,功能模块的添加和适配,性能的优化等等都会受到架构的影响,重构总是要付出很大代价的,所以核心模块和接口的设计,一定要谨慎决策.核心架构决定了一款软件的起点,设计思想决定了一款软件的终点.
如何设计一款可用的分布式数据库？首先，我们要简化思路,先设计在单机可上用的存储引擎和数据结构,然后再考虑分布式数据库所需要的譬如横向扩展,一致性,数据备份等等一系列问题.现在,我们先看看单机运行的数据库应该有哪些模块.为了快速搭起架子，我们先抛开性能和复杂的逻辑，一切以最简单的逻辑来完成。
首先,是核心模块的设计.预先设计的模块主要有文件系统（FileSystem),IO（文件读写）,NET（RPC）,读写缓存cache（Memtable），内存管理，磁盘存储(SSTable，类似HBase的storefile)，日志系统(commitlog)，服务（service)。
单机扩展到集群后，会增加一致性，分区路由，数据传输等模块，单机下的一些模块也会有所变化。

### 文件系统 ###
首先，我们设计文件系统接口IFileSystem和IFile。IFileSystem只是为了包装不同的文件系统（为后续接入HDFS等拓展做准备）。默认为操作系统的文件系统。IFile主要是为了包装不同的文件。
IFileSystem我们暂且不去管它，IFile接口如下,Mode是一个Enum,只有READ和WRITE。
```
public interface IFile<T> extends Closeable{
    boolean delete();
    boolean exists();
    boolean isDirectory();
    T open(Mode mode) throws FileNotFoundException;
    String getPath();
    List<IFile> listFiles();

    enum Mode {
        READ, WRITE
    }
}
```
DefaultFile，仅仅只是包装了File,并且是线程不安全的.这里有很多问题,我们暂且不管,先实现大体的架构
```
public class DefaultFile implements IFile<FileChannel> {
    private File file;
    private FileInputStream fileInputStream;
    private FileOutputStream fileOutputStream;

    public DefaultFile(File file) {
        this.file = file;
    }

    public DefaultFile(String path) {
        this.file = new File(path);
    }

    @Override
    public boolean delete() {
        return file.delete();
    }

    @Override
    public boolean exists() {
        return file.exists();
    }

    @Override
    public boolean isDirectory() {
        return file.isDirectory();
    }

    @Override
    public FileChannel open(Mode mode) throws FileNotFoundException {
        FileChannel fileChannel = null;
        synchronized (this) {
            switch (mode) {
                case READ:
                    if (fileInputStream == null) {
                        fileInputStream = new FileInputStream(file);
                    }
                    fileChannel = fileInputStream.getChannel();
                    break;
                case WRITE:
                    if (fileOutputStream == null) {
                        fileOutputStream = new FileOutputStream(file);
                    }
                    fileChannel = fileOutputStream.getChannel();
                    break;
            }
        }
        return fileChannel;
    }

    @Override
    public String getPath() {
        return file.getAbsolutePath();
    }

    @Override
    public List<IFile> listFiles() {
        File[] files = file.listFiles();
        if(files == null) return new ArrayList<>(0);
        List<IFile> result = new ArrayList<>(files.length);
        for(File file:files){
            IFile iFile = new DefaultFile(file);
            result.add(iFile);
        }
        return result;
    }

    @Override
    public void close() throws IOException {
        if(fileInputStream != null){
            fileInputStream.close();
        }
        if(fileOutputStream != null){
            fileOutputStream.flush();
            fileOutputStream.close();
        }
    }
}

```

### 文件IO ###
对于文件的读写，我们仅以BytBuffer的形式来进行。这里预先设计两个接口，
```
public interface IFileReader {
    byte[] read(long start, long size) throws IOException;
}
```
```
public interface IFileWriter {
    void write(ByteBuffer buffer) throws IOException;
}
```
先提供简单的实现方式,ByteBufferUtils为ByteBuffer的工具类，主要用于ByteBuffer的读写
```
public class DefaultFileReader implements IFileReader {
    private IFile<FileChannel> file;

    public DefaultFileReader(IFile<FileChannel> file) {
        this.file = file;
    }

    @Override
    public byte[] read(long start, long size) throws IOException {
        FileChannel fileChannel = file.open(IFile.Mode.READ);
//        FileLock lock = fileChannel.tryLock();
        try {
            MappedByteBuffer byteBuffer = fileChannel.map(FileChannel.MapMode.READ_ONLY, start, size);
            byteBuffer.flip();
            byte[] result = ByteBufferUtils.readBuffer(byteBuffer);
            byteBuffer.clear();
            ByteBufferUtils.release(byteBuffer);
            return result;
        } finally {
//            lock.release();
            fileChannel.close();
        }
    }
}
```
```
public class DefaultFileWriter implements IFileWriter {
    private IFile<FileChannel> iFile;

    public DefaultFileWriter(IFile<FileChannel> iFile) {
        this.iFile = iFile;
    }

    @Override
    public void write(ByteBuffer buffer) throws IOException {
        FileChannel fileChannel = iFile.open(IFile.Mode.WRITE);
        buffer.flip();
        FileLock lock = fileChannel.tryLock();
        try {
            fileChannel.write(buffer);
            buffer.clear();
        } finally {
            lock.release();
            fileChannel.close();
        }
    }
}
```

### 存储模块 ###
我们将存储模块分为内存存储和磁盘存储。借鉴Hbase和Cassandra的设计思想,我们先对操作写日志（CommitLog),然后将写入的数据先存储到内存中的某个结构中（Memtable),当满足一定的条件时（大小时间等限制）,将Memtable按照我们定义的格式写入SSTable.这里主要是借鉴Cassandra的存储结构,仍然以行为单位进行存储.Hbase的列存储,每一个单独的cell都存储了大量重复的字段,如果表的列设计过多,会浪费大量的存储.我们先做大量的接口设计
对于内存中的数据，我们需要先设计两个功能型接口，HeapSize和Snapshot。功能型接口，就是一看接口名就知道他的功能是什么，顾名思义，HeapSize就是获取当前对象占用内存，Snapshot就是获取一个快照，类似于深度克隆。
```
public interface HeapSize {
    long heapSize();
}

public interface Snapshot<T> {
    T snapshot();
}
```
#### 列单元Colum设计 ####
最底层存储单元,我们设计IColumn接口,方法暂定为获取ColumnName,ColumnValue,Timestamp,Type(插入,删除);
```
public interface IColumn extends Comparable<IColumn>{
     byte[] getColumnName();
     byte[] getColumnValue();
     long getTimestamp();

    Type getType();

    enum Type {
        Put((byte) 4),

        Delete((byte) 8),

        DeleteColumnFamily((byte) 12);

        private final byte code;

        Type(final byte c) {
            this.code = c;
        }

        public byte getCode() {
            return this.code;
        }
    }
}
```
#### Rowkey设计 ####
至于Rowkey的设计，暂时还没有考虑完善，暂且定位一个获取key值的接口。
```
public interface Rowkey extends Comparable<Rowkey>{
    byte[] getKey();
}
```
#### ColumFamily设计 ####
ColumnFamily,代表一列数据,数据结构采用ConcurrentSkipListMap<String, IColumn>;
```
public interface IColumnFamily extends HeapSize{
    void add(String key, IColumn column);
    IColumn get(String key);
    Map<String, IColumn> getFamily();
}
```
#### Memtable设计 ####
每个表在内存中的数据用Memtable表示，简单表示就是Map<Rowkey, ColumnFamily>;
这里需要注意的一点是，Memtable在写磁盘的时候，真是写入的数据是Memtable的副本。读写要如何进行。这里我们的策略是写入到新的Mentable中，读取数据可以到Mentable中读取，但是不能修改。
达到一定条件，使用特定的线程池来写磁盘；
```
public class Memtable implements HeapSize, Snapshot<Memtable> {
    private NavigableMap<Rowkey, IColumnFamily> memtable = new ConcurrentSkipListMap<>();

    private volatile boolean flush;
    private transient long heapSize;

    private SSTableWirter<Memtable> ssTableWirter;

    public Memtable(NavigableMap<Rowkey, IColumnFamily> memtable) {
        this.memtable = memtable;
    }

    public void add(Rowkey rowKey, IColumn column){
        if(flush) return;
        IColumnFamily old = memtable.get(rowKey);
        if(old == null) {
            IColumnFamily newFamily = new ColumnFamily();
            newFamily.add(new String(column.getColumnName()), column);
            memtable.put(rowKey, newFamily);
        }else {
            old.add(new String(column.getColumnName()), column);
        }
    }

    public long getHeapSize() {
        return heapSize;
    }

    public IColumnFamily getFamily(Rowkey rowKey){
        return memtable.get(rowKey);
    }

    public NavigableMap<Rowkey, IColumnFamily> getMemtable() {
        return memtable;
    }

    /**
     * if ready to flush to disk
     * @return
     */
    private boolean checkFlush() {
        return heapSize > 111;
    }

    private void flushToDisk(){
        Memtable snapshot = snapshot();
        flush = true;
        Constant.SERVICE.submit(new Runnable() {
            @Override
            public void run() {
                ssTableWirter.write(snapshot);
            }
        });
    }

    @Override
    public long heapSize() {
        return heapSize;
    }


    @Override
    public Memtable snapshot() {
        NavigableMap<Rowkey, IColumnFamily> readOnly = new ConcurrentSkipListMap<>(memtable);
        return new Memtable(readOnly);
    }

}
```
#### 磁盘存储SSTable ####
SSTable的读写，这里使用装饰模式。IFileReader和IFileWriter包装IFile,SSTableReader和SSTableWriter又包装IFileReader和IFileWriter。SSTable中保存相应的SSTableReader和索引,来快速完成数据读取
SSTable的写入与读取我们暂时只设计了接口，省去了具体的细节，比如文件的组织形式是B+Tree,而这样一整套内存和B+Tree的存储机制又称为LSM Tree。这些细节的代码，可以留着以后完成。并且上述所有的设计以及代码的细节都有很大的优化空间，不是可以一蹴而就的工作。
```
public class SSTable {
    private IndexBlock indexBlock;
    private SSTableReader<IColumnFamily> tableReader;
    private volatile boolean isWritable;

    private String getTableName(){
        return null;
    }
    public IColumnFamily getFamily(Rowkey rowkey){
        return null;
    }
}
```
#### SSTableWriter ####
```
public interface SSTableWirter<T> {
    void write(T t);
}
```
#### SSTableReader ####
```
public interface SSTableReader<T> {
    T read(long start, long size);
}
```

### client访问rpc设计 ###
最简单的rpc设计，仅需一小段代码就可以完成。性能的主要消耗是网络通信与序列化和反序列化。这里我们设计一个较为通用的rpc框架，可以完成java下的任意远程调用。
我们选择netty来做网络通信，暂时选择三种可用的java序列化方式hessian,kyro和jdk自己的序列化。
#### 通信数据 ####
根据请求的类名、方法名、方法参数，以及接口名，就可以根据反射和代理来处理请求。
```
public class MessageRequest implements Serializable {

    private static final long serialVersionUID = 779639215038924077L;
    private String messageId;   //唯一ID
    private String className;   //接口名
    private String methodName;  //方法名
    private Class<?>[] typeParameters;  //参数类型
    private Object[] parametersVal;     //参数

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Class<?>[] getTypeParameters() {
        return typeParameters;
    }

    public void setTypeParameters(Class<?>[] typeParameters) {
        this.typeParameters = typeParameters;
    }

    public Object[] getParameters() {
        return parametersVal;
    }

    public void setParameters(Object[] parametersVal) {
        this.parametersVal = parametersVal;
    }

    @Override
    public String toString() {
        return "MessageRequest{" +
                "messageId='" + messageId + '\'' +
                ", className='" + className + '\'' +
                ", methodName='" + methodName + '\'' +
                ", typeParameters=" + Arrays.toString(typeParameters) +
                ", parametersVal=" + Arrays.toString(parametersVal) +
                '}';
    }
}
```
```
public class MessageResponse implements Serializable {
    private static final long serialVersionUID = -4628239730293658445L;
    private String messageId;   //唯一ID
    private String error;       //错误消息
    private Object resultDesc;  //方法调用结果

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Object getResult() {
        return resultDesc;
    }

    public void setResult(Object resultDesc) {
        this.resultDesc = resultDesc;
    }

    @Override
    public String toString() {
        return "MessageResponse{" +
                "messageId='" + messageId + '\'' +
                ", error='" + error + '\'' +
                ", resultDesc=" + resultDesc +
                '}';
    }
}
```
```
public class MessageCallBack {
    private MessageResponse response;
    private Lock lock = new ReentrantLock();
    private Condition finish = lock.newCondition();

    public Object start() throws InterruptedException {
        try {
            lock.lock();
            finish.await(10 * 1000, TimeUnit.MILLISECONDS);
            if (this.response != null) {
                return this.response.getResult();
            } else {
                return null;
            }
        }finally {
            lock.unlock();
        }
    }

    public void over(MessageResponse reponse) {
        try {
            lock.lock();
            finish.signal();
            this.response = reponse;
        } finally {
            lock.unlock();
        }
    }
}
```
#### 序列化与反序列化 ####
类的encode和decode,设计一个接口
```
public interface MessageCodecUtil {
    int MESSAGE_LENGTH = 4;

    void encode(final ByteBuf out, final Object message) throws IOException;

    Object decode(byte[] body) throws IOException;
}
```
encode和decode都需要用到序列化和反序列化，再设计一个接口
```
public interface RpcSerialize {
    void serialize(OutputStream output, Object object) throws IOException;

    Object deserialize(InputStream input) throws IOException;
}
```
netty的childHandler需要pipeline添加一系列处理，再设计一个接口
```
public interface SerializeFrame {
    void select(ChannelPipeline pipeline, Map<String, Object> handlerMap);
}
```
因为有多种序列化方式可以选择，这里我们使用策略模式，不同的策略对应不同的序列化和encode方式
```
public class SerializeContext {
    private SerializeFrame serializeFrame;

    public SerializeContext(SerializeFrame serializeFrame) {
        this.serializeFrame = serializeFrame;
    }

    public void setSerializeFrame(SerializeFrame serializeFrame) {
        this.serializeFrame = serializeFrame;
    }

    public void select(ChannelPipeline pipeline, Map<String,Object> handlerMap) {
        serializeFrame.select(pipeline,handlerMap);
    }
}
```
具体实现就不贴代码了，只是在序列化对象构造时使用倒了对象池化技术，kyro是有自己的KryoPool的，hessian就需要使用common-pool2来池化对象。
另外，服务端还需要定义一个注解，用来确认对应的服务端实现类
```
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface RpcServer {
    Class<?> value();
}
```
对于这个注解的处理，我们在服务起来的时候模仿spring依赖注入的方式，将所有的service先实例化
```
public class RpcAnnotationFactory {
    public static Map<Annotation,Object> getBeansWithAnnotation(Class<? extends Annotation> annotation,String packageName){
        Map<Annotation,Object> handlerMap = new ConcurrentHashMap<>();
        String packageDirName = packageName.replace('.', '/');
        Enumeration<URL> dirs;
        try {
            dirs = Thread.currentThread().getContextClassLoader().getResources(
                    packageDirName);
            while (dirs.hasMoreElements()){
                URL url = dirs.nextElement();
                String protocol = url.getProtocol();
                if ("file".equals(protocol)) {
                    String filePath = URLDecoder.decode(url.getFile(), "UTF-8");
                    findAndAddClassesInPackageByFile(annotation, packageName, filePath,
                            handlerMap);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return handlerMap;
    }

    private static void findAndAddClassesInPackageByFile(Class<? extends Annotation> annotation, String packageName, String packagePath, Map<Annotation, Object> handlerMap){
        File dir = new File(packagePath);
        if (!dir.exists() || !dir.isDirectory()) {
            return;
        }
        File[] dirfiles = dir.listFiles(new FileFilter(){
            @Override
            public boolean accept(File pathname) {
                return pathname.isDirectory() || pathname.getName().endsWith(".class");
            }
        });

        for (File file : dirfiles){
            if (file.isDirectory()) {
                findAndAddClassesInPackageByFile(annotation,packageName + "."
                                + file.getName(), file.getAbsolutePath(),
                        handlerMap);
            }else{
                String className = file.getName().substring(0, file.getName().length() - 6);
                Object serviceBean = null;
                try {
                    Class clazz = Thread.currentThread().getContextClassLoader().loadClass(packageName + '.' + className);
                    Annotation anno = clazz.getAnnotation(annotation);
                    if(anno != null){
                        serviceBean = clazz.newInstance();
                        handlerMap.put(anno,serviceBean);
                    }

                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
```
在MessageRecvExecutor初始化的时候，实现依赖注入.这里还是用了google的ListeningExecutorService来做异步
```
public class MessageRecvExecutor {
    private static Logger LOG = Logger.getLogger(MessageRecvExecutor.class);
    private String serverAddress;

    private SerializeFrame serializeFrame;

    private final static String DELIMITER = ":";

    private Map<String, Object> handlerMap = new ConcurrentHashMap<>();

    private static ListeningExecutorService threadPoolExecutor;

    public MessageRecvExecutor(String serverAddress, SerializeFrame serializeFrame) {
        this.serverAddress = serverAddress;
        this.serializeFrame = serializeFrame;
    }

    public static void submit(Callable<Boolean> task, final ChannelHandlerContext context, final MessageRequest request,  final MessageResponse response){
        if (threadPoolExecutor == null) {
            synchronized (MessageRecvExecutor.class) {
                if (threadPoolExecutor == null) {
                    threadPoolExecutor = MoreExecutors.listeningDecorator((ThreadPoolExecutor) RpcThreadPool.getExecutor(16, -1));
                }
            }
        }

        ListenableFuture<Boolean> listenableFuture = threadPoolExecutor.submit(task);
        Futures.addCallback(listenableFuture, new FutureCallback<Boolean>() {
            @Override
            public void onSuccess(@Nullable Boolean aBoolean) {
                context.writeAndFlush(response).addListener(new ChannelFutureListener() {
                    @Override
                    public void operationComplete(ChannelFuture future) throws Exception {
                        LOG.info("RPC Server Send message-id respone:" + request.getMessageId());
                    }
                });
            }

            @Override
            public void onFailure(Throwable throwable) {
                throwable.printStackTrace();
            }
        }, threadPoolExecutor);
    }

    public void init(String packageName){
        Map<Annotation, Object> rpcServiceObject = RpcAnnotationFactory.getBeansWithAnnotation(RpcServer.class,packageName);

        if(MapUtils.isNotEmpty(rpcServiceObject)) {
            for(Object serviceBean : rpcServiceObject.values()) {
                String interfaceName = serviceBean.getClass().getAnnotation(RpcServer.class).value().getName();
                handlerMap.put(interfaceName, serviceBean);
            }
        }
    }

    public void afterPropertiesSet(){
        ThreadFactory threadRpcFactory = new NamedThreadFactory("NettyRPC ThreadFactory");
        int parallel = Runtime.getRuntime().availableProcessors() * 2;
        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup worker = new NioEventLoopGroup(parallel, threadRpcFactory, SelectorProvider.provider());
        ServerBootstrap bootstrap = new ServerBootstrap();
        bootstrap.group(boss, worker).channel(NioServerSocketChannel.class)
                .childHandler(new MessageRecvChannelInitializer(handlerMap).buildRpcSerializeProtocol(new SerializeContext(serializeFrame)))
                .option(ChannelOption.SO_BACKLOG, 128)
                .childOption(ChannelOption.SO_KEEPALIVE, true);
        String[] ipAddr = serverAddress.split(MessageRecvExecutor.DELIMITER);
        if(ipAddr.length == 2){
            String host = ipAddr[0];
            int port = Integer.parseInt(ipAddr[1]);
            try {
                ChannelFuture future = bootstrap.bind(host, port).sync();
                LOG.info(String.format("Netty RPC Server start success!\nip:%s\nport:%d\nprotocol:%s\n\n", host, port, new SerializeContext(serializeFrame).toString()));
                future.channel().closeFuture().sync();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                worker.shutdownGracefully();
                boss.shutdownGracefully();
            }

        }else {
            LOG.error("Netty RPC Server start fail!\n");
        }
    }

}
```
server端具体的计算是放在一个Callable里面，使用反射来进行
```
public class MessageRecvInitializeTask implements Callable<Boolean> {
    private MessageRequest request = null;
    private MessageResponse response = null;
    private Map<String, Object> handlerMap = null;

    public MessageResponse getResponse() {
        return response;
    }

    public MessageRequest getRequest() {
        return request;
    }

    public void setRequest(MessageRequest request) {
        this.request = request;
    }

    MessageRecvInitializeTask(MessageRequest request, MessageResponse response, Map<String, Object> handlerMap) {
        this.request = request;
        this.response = response;
        this.handlerMap = handlerMap;
    }

    @Override
    public Boolean call() throws Exception {
        response.setMessageId(request.getMessageId());
        try {
            Object result = reflect(request);
            response.setResult(result);
            return Boolean.TRUE;
        } catch (Throwable t) {
            response.setError(t.toString());
            t.printStackTrace();
            return Boolean.FALSE;
        }
    }

    private Object reflect(MessageRequest request) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        String className = request.getClassName();
        Object serviceBean = handlerMap.get(className);
        String methodName = request.getMethodName();
        Object[] parameters = request.getParameters();
        return MethodUtils.invokeMethod(serviceBean, methodName, parameters);
    }
}
```
具体的远程调用方法需要约定相同的接口和实现，然后client端封装MessageSendExecutor来进行调用。client端主要也是使用netty将MessageRequest发送，并将放回的消息序列化为MessageResponse。
```
public class RpcServerLoader {
    private volatile static RpcServerLoader rpcServerLoader;
    private final static String DELIMITER = ":";

    private final static int parallel = Math.max(2,Runtime.getRuntime().availableProcessors() * 2);

    private EventLoopGroup eventLoopGroup = new NioEventLoopGroup(parallel);

    private static ListeningExecutorService threadPoolExecutor = MoreExecutors.listeningDecorator((ThreadPoolExecutor) RpcThreadPool.getExecutor(16, -1));
    private MessageSendHandler messageSendHandler;
    private Lock lock = new ReentrantLock();
    private Condition connectStatus = lock.newCondition();
    private Condition handlerStatus = lock.newCondition();

    private RpcServerLoader() {
    }

    public static RpcServerLoader getInstance() {
        if (rpcServerLoader == null) {
            synchronized (RpcServerLoader.class) {
                if (rpcServerLoader == null) {
                    rpcServerLoader = new RpcServerLoader();
                }
            }
        }
        return rpcServerLoader;
    }

    public void load(String serverAddress, SerializeFrame serializeFrame) {
        String[] ipAddr = serverAddress.split(RpcServerLoader.DELIMITER);
        if (ipAddr.length == 2) {
            String host = ipAddr[0];
            int port = Integer.parseInt(ipAddr[1]);
            final InetSocketAddress remoteAddr = new InetSocketAddress(host, port);
            ListenableFuture<Boolean> listenableFuture = threadPoolExecutor.submit(new MessageSendInitializeTask(eventLoopGroup, remoteAddr, serializeFrame));
            Futures.addCallback(listenableFuture, new FutureCallback<Boolean>() {
                @Override
                public void onSuccess(@Nullable Boolean aBoolean) {
                    try {
                        lock.lock();
                        if (messageSendHandler == null) handlerStatus.await();
                        if (aBoolean == Boolean.TRUE && messageSendHandler != null) connectStatus.signalAll();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        lock.unlock();
                    }

                }

                @Override
                public void onFailure(Throwable throwable) {
                    throwable.printStackTrace();
                }
            }, threadPoolExecutor);
        }
    }

    public void setMessageSendHandler(MessageSendHandler messageInHandler) {
        try {
            lock.lock();
            this.messageSendHandler = messageInHandler;
            handlerStatus.signal();
        } finally {
            lock.unlock();
        }
    }

    public MessageSendHandler getMessageSendHandler() throws InterruptedException {
        try {
            lock.lock();
            if (messageSendHandler == null) {
                connectStatus.await();
            }
            return messageSendHandler;
        } finally {
            lock.unlock();
        }
    }

    public void unLoad() {
        messageSendHandler.close();
        threadPoolExecutor.shutdown();
        eventLoopGroup.shutdownGracefully();
    }
}
```
client提交任务Callable
```
public class MessageSendInitializeTask implements Callable<Boolean> {
    private EventLoopGroup eventLoopGroup = null;
    private InetSocketAddress serverAddress;
    private SerializeFrame serializeFrame;

    public MessageSendInitializeTask(EventLoopGroup eventLoopGroup, InetSocketAddress serverAddress, SerializeFrame serializeFrame) {
        this.eventLoopGroup = eventLoopGroup;
        this.serverAddress = serverAddress;
        this.serializeFrame = serializeFrame;
    }

    @Override
    public Boolean call() throws Exception {
        Bootstrap b = new Bootstrap();
        b.group(eventLoopGroup).channel(NioSocketChannel.class).option(ChannelOption.SO_KEEPALIVE, true);
        b.handler(new MessageSendChannelInitializer().buildRpcSerializeProtocol(new SerializeContext(serializeFrame)));
        ChannelFuture future = b.connect(serverAddress);
        future.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                if(future.isSuccess()){
                    MessageSendHandler handler = future.channel().pipeline().get(MessageSendHandler.class);
                    RpcServerLoader.getInstance().setMessageSendHandler(handler);
                }
            }
        });

        return Boolean.TRUE;
    }
}
```
MessageSendHandler主要用来处理返回数据MessageResponse
```
public class MessageSendHandler extends ChannelInboundHandlerAdapter {
    private ConcurrentHashMap<String, MessageCallBack> mapCallBack = new ConcurrentHashMap<>();
    private volatile Channel channel;
    private SocketAddress remoteAddr;
    public Channel getChannel() {
        return channel;
    }

    public SocketAddress getRemoteAddr() {
        return remoteAddr;
    }

    @Override
    public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
        super.channelRegistered(ctx);
        this.channel = ctx.channel();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        super.channelActive(ctx);
        this.remoteAddr = this.channel.remoteAddress();
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        MessageResponse response = (MessageResponse) msg;
        String messageId = response.getMessageId();
        MessageCallBack callBack = mapCallBack.get(messageId);
        if (callBack != null) {
            mapCallBack.remove(messageId);
            callBack.over(response);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }

    public void close() {
        channel.writeAndFlush(Unpooled.EMPTY_BUFFER).addListener(ChannelFutureListener.CLOSE);
    }

    public MessageCallBack sendRequest(MessageRequest request){
        MessageCallBack callBack = new MessageCallBack();
        mapCallBack.put(request.getMessageId(), callBack);
        channel.writeAndFlush(request);
        return callBack;
    }
}
```

### 本地server设计 ###

## 集群设计 ##
### p2p gossip协议 ###
### 数据写入与一致性hash ###
### 数据最终一致性 ###
### 分布式数据读取 ###
## api设计 ##
### 新建 ###
### 插入 ###
### 更新 ###
### 删除 ###

