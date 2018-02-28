package com.effective.hermes.rpc.client;

import com.effective.hermes.rpc.support.SerializeContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

/**
 * filename: MessageSendChannelInitializer
 * Description:
 * Author: ubuntu
 * Date: 2/27/18 3:06 PM
 */
public class MessageSendChannelInitializer extends ChannelInitializer<SocketChannel> {
    private SerializeContext serializeContext;

    MessageSendChannelInitializer buildRpcSerializeProtocol(SerializeContext serializeContext) {
        this.serializeContext = serializeContext;
        return this;
    }

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        serializeContext.select(pipeline,null);
    }
}
