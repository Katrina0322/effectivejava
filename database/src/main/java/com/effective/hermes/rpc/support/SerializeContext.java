package com.effective.hermes.rpc.support;

import io.netty.channel.ChannelPipeline;

import java.util.Map;

/**
 * filename: SerializeContext
 * Description:
 * Author: ubuntu
 * Date: 2/27/18 11:14 AM
 */
public class SerializeContext {
    private SerializeFrame serializeFrame;

    public SerializeContext(SerializeFrame serializeFrame) {
        this.serializeFrame = serializeFrame;
    }

    public void setSerializeFrame(SerializeFrame serializeFrame) {
        this.serializeFrame = serializeFrame;
    }

    public void select(ChannelPipeline pipeline, Map<String,Object> handlerMap) {
        serializeFrame.select(pipeline,handlerMap);
    }
}
