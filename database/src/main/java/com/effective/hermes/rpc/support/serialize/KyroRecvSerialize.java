package com.effective.hermes.rpc.support.serialize;

import com.effective.hermes.rpc.server.MessageRecvHandler;
import com.effective.hermes.rpc.support.MessageDecoder;
import com.effective.hermes.rpc.support.MessageEncoder;
import com.effective.hermes.rpc.support.SerializeFrame;
import com.effective.hermes.rpc.support.kryo.KryoCodecUtil;
import com.effective.hermes.rpc.support.kryo.KryoPoolFactory;
import io.netty.channel.ChannelPipeline;

import java.util.Map;

/**
 * filename: KyroRecvSerialize
 * Description:
 * Author: ubuntu
 * Date: 2/27/18 3:04 PM
 */
public class KyroRecvSerialize implements SerializeFrame {
    @Override
    public void select(ChannelPipeline pipeline, Map<String, Object> handlerMap) {
        KryoCodecUtil util = new KryoCodecUtil(KryoPoolFactory.getKryoPoolInstance());
        pipeline.addLast(new MessageEncoder(util));
        pipeline.addLast(new MessageDecoder(util));
        pipeline.addLast(new MessageRecvHandler(handlerMap));
    }
}
