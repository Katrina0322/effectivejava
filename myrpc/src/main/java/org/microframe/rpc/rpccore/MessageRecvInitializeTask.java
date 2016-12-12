package org.microframe.rpc.rpccore;

import org.microframe.rpc.rpcmodel.MessageResponse;
import org.microframe.rpc.rpcmodel.MessageRequest;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import org.apache.commons.lang3.reflect.MethodUtils;

import java.util.Map;

/**
 * used to
 * Created by tianjin on 12/8/16.
 */
public class MessageRecvInitializeTask implements Runnable {
    private MessageRequest messageRequest;
    private MessageResponse messageResponse;
    private Map<String,Object> handlerMap;
    private ChannelHandlerContext handlerContext;

    public MessageRequest getMessageRequest() {
        return messageRequest;
    }

    public MessageResponse getMessageResponse() {
        return messageResponse;
    }

    public void setMessageRequest(MessageRequest messageRequest) {
        this.messageRequest = messageRequest;
    }

    public MessageRecvInitializeTask(MessageRequest messageRequest, MessageResponse messageResponse, Map<String, Object> handlerMap, ChannelHandlerContext handlerContext) {
        this.messageRequest = messageRequest;
        this.messageResponse = messageResponse;
        this.handlerMap = handlerMap;
        this.handlerContext = handlerContext;
    }

    @Override
    public void run() {
        messageResponse.setMessageId(messageRequest.getMessageId());
        try {
            Object result = reflect(messageRequest);
            messageResponse.setResultDesc(result);
        } catch (Exception e) {
            messageResponse.setError(e.toString());
            e.printStackTrace();
            System.err.printf("RPC Server invoke error!\n");
        }

        handlerContext.writeAndFlush(messageResponse).addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                System.out.println("RPC Server Send message-id respone:" + messageRequest.getMessageId());
            }
        });
    }


    private Object reflect(MessageRequest messageRequest) throws Exception {
        String className = messageRequest.getClassName();
        Object serviceBean = handlerMap.get(className);
        String methodName = messageRequest.getMethodName();
        Object[] parameters = messageRequest.getParametersVal();
        return MethodUtils.invokeMethod(serviceBean, methodName, parameters);
    }
}
