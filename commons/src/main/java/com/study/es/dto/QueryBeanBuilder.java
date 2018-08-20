package com.study.es.dto;

import com.study.es.dto.curd.SortBean;
import com.study.es.dto.curd.TimeRangeBean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: tianjin
 * @email: eternity_bliss@sina.cn
 * @create: 2018-07-18 下午4:21
 */
public interface QueryBeanBuilder extends Serializable {

    QueryBeanBuilder addExists(String exists);

    QueryBeanBuilder addTimeRange(TimeRangeBean timeRangeBean);

    QueryBeanBuilder addSort(SortBean sortBean);

    QueryBeanBuilder addMust(Map<String, List<String>> must);

    QueryBeanBuilder addMustNot(Map<String, List<String>> mustNot);

    QueryBeanBuilder addMatch(Map<String, String> match);

    QueryBean build();
}
