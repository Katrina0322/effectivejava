package com.effective.hermes.rpc.client;

import com.effective.hermes.rpc.support.SerializeContext;
import com.effective.hermes.rpc.support.SerializeFrame;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;
import java.util.concurrent.Callable;

/**
 * filename: MessageSendInitializeTask
 * Description:
 * Author: ubuntu
 * Date: 2/27/18 3:08 PM
 */
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
