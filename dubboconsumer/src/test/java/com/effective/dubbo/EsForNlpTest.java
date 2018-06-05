package com.effective.dubbo;

import com.effective.dubbo.service.DubboConsumerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @description: test
 * @author: tianjin
 * @email: eternity_bliss@sina.cn
 * @create: 2018-05-31 下午12:57
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ConsumerApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EsForNlpTest {

    @Autowired
    private DubboConsumerService service;

    @Test
    public void dubboTest(){
        service.printCity();
    }
}
