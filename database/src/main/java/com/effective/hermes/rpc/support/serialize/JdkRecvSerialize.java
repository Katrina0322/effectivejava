package com.effective.hermes.rpc.support.serialize;

import com.effective.hermes.rpc.server.MessageRecvHandler;
import com.effective.hermes.rpc.support.MessageCodecUtil;
import com.effective.hermes.rpc.support.SerializeFrame;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;

import java.util.Map;

/**
 * filename: JdkRecvSerialize
 * Description:
 * Author: ubuntu
 * Date: 2/27/18 3:02 PM
 */
public class JdkRecvSerialize implements SerializeFrame {
    @Override
    public void select(ChannelPipeline pipeline, Map<String, Object> handlerMap) {
        pipeline.addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, MessageCodecUtil.MESSAGE_LENGTH, 0, MessageCodecUtil.MESSAGE_LENGTH));
        pipeline.addLast(new LengthFieldPrepender(MessageCodecUtil.MESSAGE_LENGTH));
        pipeline.addLast(new ObjectEncoder());
        pipeline.addLast(new ObjectDecoder(Integer.MAX_VALUE, ClassResolvers.weakCachingConcurrentResolver(this.getClass().getClassLoader())));
        pipeline.addLast(new MessageRecvHandler(handlerMap));
    }
}
