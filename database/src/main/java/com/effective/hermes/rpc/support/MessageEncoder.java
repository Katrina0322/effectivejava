package com.effective.hermes.rpc.support;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * filename: MessageEncoder
 * Description:
 * Author: ubuntu
 * Date: 2/27/18 11:48 AM
 */
public class MessageEncoder extends MessageToByteEncoder<Object> {
    private MessageCodecUtil util = null;

    public MessageEncoder(final MessageCodecUtil util) {
        this.util = util;
    }

    @Override
    protected void encode(ChannelHandlerContext ctx, Object msg, ByteBuf out) throws Exception {
        util.encode(out, msg);
    }
}
