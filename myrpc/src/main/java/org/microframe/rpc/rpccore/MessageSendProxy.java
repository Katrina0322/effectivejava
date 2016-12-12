package org.microframe.rpc.rpccore;

import org.microframe.rpc.rpcmodel.MessageRequest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.UUID;

/**
 * used to
 * Created by tianjin on 12/9/16.
 */
public class MessageSendProxy<T> implements InvocationHandler {
    private Class<T> clazz;

    public MessageSendProxy(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        MessageRequest request = new MessageRequest();
        request.setMessageId(UUID.randomUUID().toString());
        request.setClassName(method.getDeclaringClass().getName());
        request.setMethodName(method.getName());
        request.setTypeParameters(method.getParameterTypes());
        request.setParametersVal(args);

        MessageSendHandler sendHandler = RpcServerLoader.getInstance().getMessageSendHandler();
        MessageCallBack callBack = sendHandler.sendRequest(request);
        return callBack.start();
    }
}
