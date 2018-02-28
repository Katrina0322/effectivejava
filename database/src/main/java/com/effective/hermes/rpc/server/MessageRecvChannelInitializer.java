package com.effective.hermes.rpc.server;

import com.effective.hermes.rpc.support.SerializeContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

import java.util.Map;

/**
 * filename: MessageRecvChannelInitializer
 * Description:
 * Author: ubuntu
 * Date: 2/27/18 11:30 AM
 */
public class MessageRecvChannelInitializer extends ChannelInitializer<SocketChannel> {
    private Map<String, Object> handlerMap;

    private SerializeContext serializeContext;

    public MessageRecvChannelInitializer buildRpcSerializeProtocol(SerializeContext serializeContext) {
        this.serializeContext = serializeContext;
        return this;
    }

    public MessageRecvChannelInitializer(Map<String, Object> handlerMap) {
        this.handlerMap = handlerMap;
    }

    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        serializeContext.select(pipeline, handlerMap);
    }
}
