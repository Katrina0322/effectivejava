package com.study.es.dto;

import com.study.es.dto.curd.SortBean;
import com.study.es.dto.curd.TimeRangeBean;

import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: tianjin
 * @email: eternity_bliss@sina.cn
 * @create: 2018-07-18 下午4:23
 */
public abstract class AbstractQueryBeanBuilder implements QueryBeanBuilder {

    private static final long serialVersionUID = -3935210807443483430L;

    protected abstract QueryGenerater getQueryGenerater();

    protected abstract StringBuilder getSb();

    @Override
    public QueryBeanBuilder addExists(String exists) {
        getQueryGenerater().addExists(exists);
        return this;
    }

    @Override
    public QueryBeanBuilder addTimeRange(TimeRangeBean timeRangeBean) {
        getQueryGenerater().addTimeRange(timeRangeBean);
        return this;
    }

    @Override
    public QueryBeanBuilder addSort(SortBean sortBean) {
        getSb().append(",").append(sortBean.getQueryString());
        return this;
    }

    @Override
    public QueryBeanBuilder addMust(Map<String, List<String>> must) {
        getQueryGenerater().addMust(must);
        return this;
    }

    @Override
    public QueryBeanBuilder addMustNot(Map<String, List<String>> mustNot) {
        getQueryGenerater().addMustNot(mustNot);
        return this;
    }

    @Override
    public QueryBeanBuilder addMatch(Map<String, String> match) {
        getQueryGenerater().addMatch(match);
        return this;
    }
}
