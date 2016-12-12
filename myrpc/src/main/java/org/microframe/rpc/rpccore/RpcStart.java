package org.microframe.rpc.rpccore;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * used to
 * Created by tianjin on 12/12/16.
 */
public class RpcStart {
    public static void main(String[] args) {
        new ClassPathXmlApplicationContext("rpc-jdk.xml");
    }
}
