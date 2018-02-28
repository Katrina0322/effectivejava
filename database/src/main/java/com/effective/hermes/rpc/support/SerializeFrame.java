package com.effective.hermes.rpc.support;

import io.netty.channel.ChannelPipeline;

import java.util.Map;

/**
 * filename: SerializeFrame
 * Description:
 * Author: ubuntu
 * Date: 2/27/18 10:45 AM
 */
public interface SerializeFrame {
    void select(ChannelPipeline pipeline, Map<String, Object> handlerMap);
}
