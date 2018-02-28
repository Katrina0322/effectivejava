package com.effective.hermes.rpc.server;

import com.effective.hermes.rpc.MessageRequest;
import com.effective.hermes.rpc.MessageResponse;
import org.apache.commons.lang3.reflect.MethodUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * filename: MessageRecvInitializeTask
 * Description:
 * Author: ubuntu
 * Date: 2/27/18 2:00 PM
 */
public class MessageRecvInitializeTask implements Callable<Boolean> {
    private MessageRequest request = null;
    private MessageResponse response = null;
    private Map<String, Object> handlerMap = null;

    public MessageResponse getResponse() {
        return response;
    }

    public MessageRequest getRequest() {
        return request;
    }

    public void setRequest(MessageRequest request) {
        this.request = request;
    }

    MessageRecvInitializeTask(MessageRequest request, MessageResponse response, Map<String, Object> handlerMap) {
        this.request = request;
        this.response = response;
        this.handlerMap = handlerMap;
    }

    @Override
    public Boolean call() throws Exception {
        response.setMessageId(request.getMessageId());
        try {
            Object result = reflect(request);
            response.setResult(result);
            return Boolean.TRUE;
        } catch (Throwable t) {
            response.setError(t.toString());
            t.printStackTrace();
//            System.err.printf("RPC Server invoke error!\n");
            return Boolean.FALSE;
        }
    }

    private Object reflect(MessageRequest request) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        String className = request.getClassName();
        Object serviceBean = handlerMap.get(className);
        String methodName = request.getMethodName();
        Object[] parameters = request.getParameters();
        return MethodUtils.invokeMethod(serviceBean, methodName, parameters);
    }
}
