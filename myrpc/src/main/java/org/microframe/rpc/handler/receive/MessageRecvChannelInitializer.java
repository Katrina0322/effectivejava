package org.microframe.rpc.handler.receive;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

import java.util.Map;

/**
 * used to
 * Created by tianjin on 12/9/16.
 */
public class MessageRecvChannelInitializer extends ChannelInitializer<SocketChannel> {
    final public static int MESSAGE_LENGTH = 4;
    private Map<String, Object> handlerMap;

    public MessageRecvChannelInitializer(Map<String, Object> handlerMap) {
        this.handlerMap = handlerMap;
    }


    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, MESSAGE_LENGTH, 0, MESSAGE_LENGTH));
        pipeline.addLast(new LengthFieldPrepender(MESSAGE_LENGTH));
        pipeline.addLast(new ObjectEncoder());
        pipeline.addLast(new ObjectDecoder(Integer.MAX_VALUE, ClassResolvers.weakCachingConcurrentResolver(this.getClass().getClassLoader())));
        pipeline.addLast(new MessageRecvHandler(handlerMap));
    }
}
