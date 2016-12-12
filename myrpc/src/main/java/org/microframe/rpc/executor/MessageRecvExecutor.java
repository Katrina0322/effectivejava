package org.microframe.rpc.executor;

import org.microframe.rpc.handler.receive.MessageRecvHandler;
import org.microframe.rpc.rpccore.RpcThreadFactory;
import org.microframe.rpc.rpccore.RpcThreadPool;
import org.microframe.rpc.rpcmodel.MessageKeyVal;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.nio.channels.spi.SelectorProvider;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.logging.Level;
import java.util.regex.Pattern;

/**
 * used to
 * Created by tianjin on 12/9/16.
 */
public class MessageRecvExecutor implements ApplicationContextAware,InitializingBean {
    private String serverAddress;

    private final static Pattern DELIMITER = Pattern.compile(":");

    private Map<String, Object> handlerMap = new ConcurrentHashMap<String, Object>();

    private static ThreadPoolExecutor threadPoolExecutor;

    public MessageRecvExecutor(String serverAddress) {
        this.serverAddress = serverAddress;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        try {
            MessageKeyVal keyVal = (MessageKeyVal) applicationContext.getBean(Class.forName("com.oneapm.rpcmodel.MessageKeyVal"));
            Map<String, Object> rpcServiceObject = keyVal.getMessageKeyVal();
            for(Map.Entry<String, Object> entry:rpcServiceObject.entrySet()){
                handlerMap.put(entry.getKey(),entry.getValue());
            }
        } catch (ClassNotFoundException e) {
            java.util.logging.Logger.getLogger(MessageRecvExecutor.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        ThreadFactory factory = new RpcThreadFactory("NettyRPC ThreadFactory");
        int parallel = Runtime.getRuntime().availableProcessors() * 2;
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup worker = new NioEventLoopGroup(parallel,factory, SelectorProvider.provider());
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup,worker).channel(NioServerSocketChannel.class)
                    .childHandler(new MessageRecvHandler(handlerMap))
                    .option(ChannelOption.SO_BACKLOG,128)
                    .childOption(ChannelOption.SO_KEEPALIVE,true);

            String[] ipAddr = DELIMITER.split(serverAddress);
            if (ipAddr.length == 2) {
                String host = ipAddr[0];
                int port = Integer.parseInt(ipAddr[1]);
                ChannelFuture future = bootstrap.bind(host,port).sync();
                System.out.printf("Netty RPC Server start success ip:%s port:%d", host, port);
                future.channel().closeFuture().sync();
            }else {
                System.out.printf("Netty RPC Server start fail!\n");
            }
        } finally {
            worker.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }

    }

    public static void submit(Runnable task){
        if(threadPoolExecutor == null){
            synchronized (MessageRecvExecutor.class){
                if (threadPoolExecutor == null) {
                    threadPoolExecutor = (ThreadPoolExecutor) RpcThreadPool.getExecutor(16, -1);
                }
            }
        }
        threadPoolExecutor.submit(task);
    }
}
