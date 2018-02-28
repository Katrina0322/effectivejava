package com.effective.hermes.rpc.client;

import com.effective.hermes.rpc.MessageCallBack;
import com.effective.hermes.rpc.MessageRequest;
import com.google.common.reflect.AbstractInvocationHandler;

import java.lang.reflect.Method;
import java.util.UUID;

/**
 * filename: MessageSendProxy
 * Description:
 * Author: ubuntu
 * Date: 2/27/18 3:50 PM
 */
public class MessageSendProxy extends AbstractInvocationHandler{
    @Override
    protected Object handleInvocation(Object o, Method method, Object[] objects) throws Throwable {
        MessageRequest request = new MessageRequest();
        request.setMessageId(UUID.randomUUID().toString());
        request.setClassName(method.getDeclaringClass().getName());
        request.setMethodName(method.getName());
        request.setTypeParameters(method.getParameterTypes());
        request.setParameters(objects);

        MessageSendHandler handler = RpcServerLoader.getInstance().getMessageSendHandler();
        MessageCallBack callBack = handler.sendRequest(request);
        return callBack.start();
    }
}
