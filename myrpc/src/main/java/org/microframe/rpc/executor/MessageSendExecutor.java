package org.microframe.rpc.executor;

import org.microframe.rpc.rpccore.MessageSendProxy;
import org.microframe.rpc.rpccore.RpcSerializeProtocol;
import org.microframe.rpc.rpccore.RpcServerLoader;

import java.lang.reflect.Proxy;

/**
 * used to
 * Created by tianjin on 12/8/16.
 */
public class MessageSendExecutor {
    private RpcServerLoader loader = RpcServerLoader.getInstance();

    public MessageSendExecutor(String serverAddress) {
        loader.load(serverAddress, RpcSerializeProtocol.JDKSERIALIZE);
    }

    public void stop(){
        loader.unLoad();
    }

    public static <T> T execute(Class<T> rpcImpl){
        return (T) Proxy.newProxyInstance(rpcImpl.getClassLoader(),new Class[]{rpcImpl},new MessageSendProxy<T>(rpcImpl));
    }
}
