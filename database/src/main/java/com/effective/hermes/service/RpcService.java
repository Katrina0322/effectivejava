package com.effective.hermes.service;

import com.effective.hermes.rpc.server.MessageRecvExecutor;
import com.effective.hermes.rpc.support.SerializeFrame;

/**
 * filename: RpcService
 * Description:
 * Author: ubuntu
 * Date: 2/28/18 3:48 PM
 */
public class RpcService {
    public static void start(String addr, SerializeFrame serializeFrame, String packageName){
        MessageRecvExecutor executor = new MessageRecvExecutor(addr, serializeFrame);
        executor.init(packageName);
        executor.afterPropertiesSet();
    }
}
