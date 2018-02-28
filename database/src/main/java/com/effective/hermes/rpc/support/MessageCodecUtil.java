package com.effective.hermes.rpc.support;

import io.netty.buffer.ByteBuf;

import java.io.IOException;

/**
 * filename: MessageCodecUtil
 * Description:
 * Author: ubuntu
 * Date: 2/27/18 11:44 AM
 */
public interface MessageCodecUtil {
    int MESSAGE_LENGTH = 4;

    void encode(final ByteBuf out, final Object message) throws IOException;

    Object decode(byte[] body) throws IOException;
}
