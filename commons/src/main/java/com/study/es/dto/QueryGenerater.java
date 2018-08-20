package com.study.es.dto;

import com.google.common.base.Preconditions;
import com.study.es.core.IQueryString;
import com.study.es.dto.curd.TimeRangeBean;

import java.util.List;
import java.util.Map;

/**
 * @description: generate query
 * @author: tianjin
 * @email: eternity_bliss@sina.cn
 * @create: 2018-07-18 下午3:58
 */
public class QueryGenerater implements IQueryString {
    private static final long serialVersionUID = -5718019132477309844L;
    private String query;
    private String filter;

    public QueryGenerater() {
        query = "\"query\":{\"bool\":{ ";
        filter = "\"filter\":[ ";
    }

    public void addExists(String exists){
        Preconditions.checkNotNull(exists);
        filter += "{\"exists\" : { \"field\" : \"" + exists + "\" }},";
    }

    public void addMatch(Map<String, String> match){
        Preconditions.checkArgument(match != null && match.size() > 0, "match不能爲empty!");
        query += "\"must\": [";
        for(Map.Entry<String, String> entry:match.entrySet()){
            query = query +  "{\"match\":{\"" + entry.getKey() + "\":\"" + entry.getValue() + "\"}},";
        }
        query = query.substring(0, query.length() - 1) + "], ";
    }

    public void addTimeRange(TimeRangeBean timeRangeBean){
        filter += timeRangeBean.getQueryString() + ",";
    }



    public void addMust(Map<String, List<String>> must){
        Preconditions.checkArgument(must != null && must.size() > 0, "must不能爲empty!");
        for(Map.Entry<String, List<String>> entry:must.entrySet()){
            filter += "{\"terms\":{\"" + entry.getKey() +"\":[\"" + String.join("\", \"", entry.getValue()) + "\"]}},";
        }
    }

    public void addMustNot(Map<String, List<String>> mustNot){
        Preconditions.checkArgument(mustNot != null && mustNot.size() > 0, "mustNot不能爲empty!");
        query += "\"must_not\": [";
        for(Map.Entry<String, List<String>> entry:mustNot.entrySet()){
            for(String s:entry.getValue()){
                query += "{\"match\":{\"" + entry.getKey() +"\":\"" + s + "\"}},";
            }
        }
        query = query.substring(0, query.length() - 1) + "], ";
    }


    @Override
    public String getQueryString() {
        return query.substring(0, query.length() - 1) + filter.substring(0, filter.length() - 1) + "]}}";
    }
}
