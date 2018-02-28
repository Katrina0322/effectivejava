package com.effective.hermes.rpc.client;

import com.effective.hermes.rpc.support.SerializeFrame;
import com.google.common.reflect.Reflection;

/**
 * filename: MessageSendExecutor
 * Description:
 * Author: ubuntu
 * Date: 2/27/18 3:49 PM
 */
public class MessageSendExecutor {
    private RpcServerLoader loader = RpcServerLoader.getInstance();

    public MessageSendExecutor() {
    }

    public void setRpcServerLoader(String serverAddress, SerializeFrame serializeFrame) {
        loader.load(serverAddress, serializeFrame);
    }

    public void stop() {
        loader.unLoad();
    }

    public static <T> T execute(Class<T> rpcInterface) {
        return Reflection.newProxy(rpcInterface, new MessageSendProxy());
    }
}
