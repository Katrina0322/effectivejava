package com.study.es.dto.agg;


import com.google.common.base.Preconditions;
import com.study.es.dto.AggQuery;

/**
 * @description:
 * @author: tianjin
 * @email: eternity_bliss@sina.cn
 * @create: 2018-07-17 下午5:39
 */
public class GroupAgg implements AggQuery {
    private static final long serialVersionUID = -4776852712826745864L;
    private String query;
//    private String groupField;
//    private SumAgg childSumAgg;

    public GroupAgg(String groupField) {
        Preconditions.checkArgument(groupField != null && !groupField.isEmpty(), "groupField不能是empty");
        query = "\"aggs\":{\"" + groupField + "\":{\"terms\":" +
                    "{\"field\":\"" + groupField + "\"," +
                    "\"size\":" + Integer.MAX_VALUE +"," +
                    "\"order\": {\"_count\": \"desc\"}}";
    }

    public GroupAgg addChildGroupAgg(GroupAgg groupAgg){
        query += "," + groupAgg.getQueryString();
        return this;
    }

    public GroupAgg addSumAgg(SumAgg childSumAgg){
        query += "," + childSumAgg.getQueryString();
        return this;
    }

    @Override
    public String getQueryString() {
        return query + "}}";
    }
}
