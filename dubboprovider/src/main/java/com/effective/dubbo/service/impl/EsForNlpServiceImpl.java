package com.effective.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.effective.dubbo.entity.Result;
import com.effective.dubbo.service.EsForNlpService;

/**
 * @description: impl
 * @author: tianjin
 * @email: eternity_bliss@sina.cn
 * @create: 2018-05-31 下午12:43
 */
@Service(version = "1.0.0")
public class EsForNlpServiceImpl implements EsForNlpService{

    @Override
    public String getDataFromEs(Result query) {
        System.out.println(query.getCity() + " dubbo");
        return query.getCity() + " dubbo";
    }
}
