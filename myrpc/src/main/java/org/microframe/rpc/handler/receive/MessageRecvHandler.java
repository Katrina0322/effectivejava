package org.microframe.rpc.handler.receive;

import org.microframe.rpc.rpcmodel.MessageResponse;
import org.microframe.rpc.executor.MessageRecvExecutor;
import org.microframe.rpc.rpcmodel.MessageRequest;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.Map;

/**
 * used to
 * Created by tianjin on 12/8/16.
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
        MessageRecvInitializeTask recvTask = new MessageRecvInitializeTask(request, response, handlerMap, ctx);
        //不要阻塞nio线程，复杂的业务逻辑丢给专门的线程池
        MessageRecvExecutor.submit(recvTask);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
