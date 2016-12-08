package com.oneapm.rpccore;

import com.oneapm.handler.MessageSendHandler;
import com.oneapm.handler.MessageSendInitializeTask;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;

import java.net.InetSocketAddress;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.regex.Pattern;

/**
 * used to
 * Created by tianjin on 12/8/16.
 */
public class RpcServerLoader {
    private volatile RpcServerLoader rpcServerLoader;

    private final String DELIMITER = ":";

    private final Pattern PATTERN = Pattern.compile(":");

    private RpcSerializeProtocol serializeProtocol = RpcSerializeProtocol.JDKSERIALIZE;
    private final static int parallel = Runtime.getRuntime().availableProcessors() * 2;

    private EventLoopGroup eventLoopGroup = new NioEventLoopGroup(parallel);

    private static ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) RpcThreadPool.getExecutor(16, -1);

    private MessageSendHandler messageSendHandler;

    private Lock lock = new ReentrantLock();
    private Condition signal = lock.newCondition();

    public static final RpcServerLoader getInstance(){
        return RpcServerLazyHolder.INSTANCE;
    }

    private  RpcServerLoader() {
        super();
    }

    /**
     * 单例模式   静态内部内
     */
    private static class RpcServerLazyHolder{
        private static final RpcServerLoader INSTANCE = new RpcServerLoader();
    }

    public void load(String serverAddress,RpcSerializeProtocol protocol){
        String[] ipAddr = PATTERN.split(serverAddress);
        if(ipAddr.length == 2){
            String host = ipAddr[0];
            int port = Integer.parseInt(ipAddr[1]);
            final InetSocketAddress remoteAddr = new InetSocketAddress(host,port);
            threadPoolExecutor.submit(new MessageSendInitializeTask(eventLoopGroup, remoteAddr, this));
        }
    }

    public void setMessageSendHandler(MessageSendHandler messageSendHandler){
        try {
            lock.lock();
            this.messageSendHandler = messageSendHandler;
            signal.signalAll();
        } finally {
            lock.unlock();
        }
    }


    public void setSerializeProtocol(RpcSerializeProtocol serializeProtocol) {
        this.serializeProtocol = serializeProtocol;
    }

    public MessageSendHandler getMessageSendHandler() throws InterruptedException {
        try {
            lock.lock();
            if(messageSendHandler == null) signal.await();
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
