package com.effective.hermes.rpc.server;

import com.effective.hermes.rpc.MessageRequest;
import com.effective.hermes.rpc.MessageResponse;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Map;

/**
 * filename: MessageRecvHandler
 * Description:
 * Author: ubuntu
 * Date: 2/27/18 1:57 PM
 */
public class MessageRecvHandler extends ChannelInboundHandlerAdapter {
    private final Map<String, Object> handlerMap;
    public MessageRecvHandler(Map<String, Object> handlerMap) {
        this.handlerMap = handlerMap;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        MessageRequest request = (MessageRequest) msg;
        MessageResponse response = new MessageResponse();
        MessageRecvInitializeTask recvTask = new MessageRecvInitializeTask(request, response, handlerMap);
        MessageRecvExecutor.submit(recvTask, ctx, request, response);
    }
}
