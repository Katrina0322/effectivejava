package com.effective.dubbo;

import com.effective.dubbo.other.DubboConsumerServiceTest;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @description: starter
 * @author: tianjin
 * @email: eternity_bliss@sina.cn
 * @create: 2018-05-31 下午12:53
 */
@SpringBootApplication
public class ConsumerApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(ConsumerApplication.class,args);
//        DubboConsumerService service = run.getBean(DubboConsumerService.class);
        DubboConsumerServiceTest service = run.getBean(DubboConsumerServiceTest.class);
        service.printCity();
    }
}
