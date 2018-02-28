package com.effective.hermes.rpc.server;

import com.effective.hermes.rpc.MessageRequest;
import com.effective.hermes.rpc.MessageResponse;
import com.effective.hermes.rpc.support.SerializeContext;
import com.effective.hermes.rpc.support.SerializeFrame;
import com.effective.hermes.rpc.thread.NamedThreadFactory;
import com.effective.hermes.rpc.thread.RpcThreadPool;
import com.google.common.util.concurrent.*;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.nio.NioEventLoop;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.apache.commons.collections.MapUtils;
import org.apache.log4j.Logger;

import javax.annotation.Nullable;
import java.lang.annotation.Annotation;
import java.nio.channels.spi.SelectorProvider;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * filename: MessageRecvExecutor
 * Description:
 * Author: ubuntu
 * Date: 2/27/18 10:25 AM
 */
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
