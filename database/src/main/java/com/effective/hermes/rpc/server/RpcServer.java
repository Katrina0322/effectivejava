package com.effective.hermes.rpc.server;

import java.lang.annotation.*;

/**
 * @filename: RpcServer
 * @Description:
 * @Author: ubuntu
 * @Date: 12/15/16 2:38 PM
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface RpcServer {
    Class<?> value();
}
