package com.oneapm.SPI;

/**
 * @filename: DubboHello
 * @Description:
 * @Author: ubuntu
 * @Date: 12/14/16 3:14 PM
 */
public class DubboHello implements HelloService {
    @Override
    public void sayHello() {
        System.out.println("hello：Dubbo");
    }
}
