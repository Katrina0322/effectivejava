package com.effective.hermes.rpc.support.serialize;

import com.effective.hermes.rpc.server.MessageRecvHandler;
import com.effective.hermes.rpc.support.MessageDecoder;
import com.effective.hermes.rpc.support.MessageEncoder;
import com.effective.hermes.rpc.support.SerializeFrame;
import com.effective.hermes.rpc.support.hessian.HessianCodecUtil;
import io.netty.channel.ChannelPipeline;

import java.util.Map;

/**
 * filename: HessianRecvSerialize
 * Description:
 * Author: ubuntu
 * Date: 2/27/18 2:59 PM
 */
public class HessianRecvSerialize implements SerializeFrame {
    @Override
    public void select(ChannelPipeline pipeline, Map<String, Object> handlerMap) {
        HessianCodecUtil util = new HessianCodecUtil();
        pipeline.addLast(new MessageEncoder(util));
        pipeline.addLast(new MessageDecoder(util));
        pipeline.addLast(new MessageRecvHandler(handlerMap));
    }
}
