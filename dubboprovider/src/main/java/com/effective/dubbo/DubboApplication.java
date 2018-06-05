package com.effective.dubbo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @description: starter
 * @author: tianjin
 * @email: eternity_bliss@sina.cn
 * @create: 2018-05-31 下午12:03
 */
@SpringBootApplication
public class DubboApplication {
    public static void main(String[] args) {
        // 程序启动入口
        // 启动嵌入式的 Tomcat 并初始化 Spring 环境及其各 Spring 组件
        SpringApplication.run(DubboApplication.class,args);
    }
}
