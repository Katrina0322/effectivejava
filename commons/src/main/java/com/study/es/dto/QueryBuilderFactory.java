package com.study.es.dto;

import com.study.es.dto.agg.AggQueryBean;
import com.study.es.dto.curd.SourceQueryBean;

import java.util.List;

/**
 * @description: quert builder factory
 * @author: tianjin
 * @email: eternity_bliss@sina.cn
 * @create: 2018-07-18 上午11:09
 */
public class QueryBuilderFactory {
    public static QueryBeanBuilder getAggBeanBuilder(String index, AggQuery aggQuery){
        return new AggQueryBean.AggBeanBuilder(index, aggQuery);
    }

    public static QueryBeanBuilder getSourceBeanBuilder(String index, int pageSize, int pageNo, List<String> source){
        return new SourceQueryBean.SourceQueryBeanBuilder(index, pageSize, pageNo, source);
    }
}
