package com.effective.dubbo.other;

import com.alibaba.dubbo.config.annotation.Reference;
import com.effective.dubbo.entity.Result;
import com.effective.dubbo.service.EsForNlpService;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @description: test
 * @author: tianjin
 * @email: eternity_bliss@sina.cn
 * @create: 2018-05-31 下午2:11
 */
@Service
public class DubboConsumerServiceTest {
    @Reference(version = "1.0.0")
    private EsForNlpService service;

    public void printCity() {
        String name = "温岭";
        String city = service.getDataFromEs(new Result(name, new Date()));
        System.out.println(city);
    }
}
