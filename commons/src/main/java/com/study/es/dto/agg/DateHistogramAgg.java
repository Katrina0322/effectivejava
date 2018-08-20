package com.study.es.dto.agg;


import com.study.es.dto.AggQuery;

/**
 * @description: date_histogram
 * @author: tianjin
 * @email: eternity_bliss@sina.cn
 * @create: 2018-07-16 下午4:38
 */
public class DateHistogramAgg implements AggQuery {
    private static final long serialVersionUID = -467599025335706559L;
    private String query;
//    private String fieldName;
//    private String interval;
//    private String format;
//    private int minDocCount = 1;
//    private DateHistogramAgg childDateHistogramAgg;
//    private SumAgg sumAgg;

    public DateHistogramAgg(String fieldName, String interval, String format, int minDocCount) {
        query = "\"aggs\": {\""  + fieldName + "\": {\"date_histogram\": " +
                "{\"field\": \"" + fieldName + "\"," +
                "\"interval\": \"" + interval + "\"," +
                "\"time_zone\": \"Asia/Shanghai\"," +
                (format != null ? "\"format\": \" "+ format + "\"," : "") +
                "\"min_doc_count\": " + minDocCount + "}";
    }

    public DateHistogramAgg addChildDateHistogramAgg(DateHistogramAgg childDateHistogramAgg){
        query = query + "," + childDateHistogramAgg.getQueryString();
        return this;
    }

    public DateHistogramAgg addChildGroup(GroupAgg groupAgg){
        query = query + "," + groupAgg.getQueryString();
        return this;
    }

    public DateHistogramAgg addChildSum(SumAgg sumAgg){
        query = query + "," + sumAgg.getQueryString();
        return this;
    }

    @Override
    public String getQueryString() {
        return query + "}}";
    }
}
