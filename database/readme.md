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
```
public class SSTable {
    private IndexBlock indexBlock;
    private SSTableReader<IColumnFamily> tableReader;

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
### 文件合并 ###
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

