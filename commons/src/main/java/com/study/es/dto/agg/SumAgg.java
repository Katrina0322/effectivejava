package com.study.es.dto.agg;


import com.google.common.base.Preconditions;
import com.study.es.dto.AggQuery;

import java.util.List;

/**
 * @description:
 * @author: tianjin
 * @email: eternity_bliss@sina.cn
 * @create: 2018-07-17 下午4:12
 */
public class SumAgg implements AggQuery {
    private static final long serialVersionUID = 1025214407539750512L;
    private String query;
//    private List<String> sumList;

    public SumAgg(List<String> sumList) {
        Preconditions.checkArgument(sumList != null && sumList.size() > 0, "sumList不能是empty");
        query = "\"aggs\":{";
        for(String s:sumList){
            query += "\"" + s + "\":{ \"sum\": { \"field\": \"" + s + "\"}},";
        }
    }

    @Override
    public String getQueryString() {
        return query.substring(0, query.length() - 1) + "}";
    }

}
