package com.effective.dubbo.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.effective.dubbo.entity.Result;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @description: consumer
 * @author: tianjin
 * @email: eternity_bliss@sina.cn
 * @create: 2018-05-31 下午1:12
 */
@Component
public class DubboConsumerService {

    @Reference(version = "1.0.0")
    private EsForNlpService service;

    public void printCity() {
        String name = "温岭";
        String city = service.getDataFromEs(new Result(name, new Date()));
        System.out.println(city);
    }
}
