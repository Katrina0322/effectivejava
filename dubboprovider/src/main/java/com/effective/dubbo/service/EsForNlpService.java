package com.effective.dubbo.service;

import com.effective.dubbo.entity.Result;

/**
 * @description: elastic interface for nlp
 * @author: tianjin
 * @email: eternity_bliss@sina.cn
 * @create: 2018-05-31 下午12:41
 */
public interface EsForNlpService {
    String getDataFromEs(Result query);
}
