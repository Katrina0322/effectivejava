package com.oneapm.handler;

import com.oneapm.rpccore.RpcServerLoader;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

/**
 * used to
 * Created by tianjin on 12/8/16.
 */
public class MessageSendInitializeTask implements Runnable{
    private EventLoopGroup eventLoopGroup;
    private InetSocketAddress inetSocketAddress;
    private RpcServerLoader rpcServerLoader;

    public MessageSendInitializeTask(EventLoopGroup eventLoopGroup, InetSocketAddress inetSocketAddress, RpcServerLoader rpcServerLoader) {
        this.eventLoopGroup = eventLoopGroup;
        this.inetSocketAddress = inetSocketAddress;
        this.rpcServerLoader = rpcServerLoader;
    }

    @Override
    public void run() {
        Bootstrap b = new Bootstrap();
        b.group(eventLoopGroup).channel(NioSocketChannel.class).option(ChannelOption.SO_KEEPALIVE,true);
        b.handler(new MessageSendChannelInitializer());

        final ChannelFuture channelFuture = b.connect(inetSocketAddress);
        channelFuture.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                if(future.isSuccess()){
                    MessageSendHandler handler = channelFuture.channel().pipeline().get(MessageSendHandler.class);
                    MessageSendInitializeTask.this.rpcServerLoader.setMessageSendHandler(handler);
                }
            }
        });
    }
}
