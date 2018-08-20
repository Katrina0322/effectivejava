package com.study.es.dto.curd;


import com.study.es.core.IQueryString;

/**
 * @description:
 * @author: tianjin
 * @email: eternity_bliss@sina.cn
 * @create: 2018-07-17 上午10:13
 */
public class TimeRangeBean implements IQueryString {
    private static final long serialVersionUID = -5998762294844366827L;
    private String fieldName;
    private long startTime;
    private long endTime;

    public TimeRangeBean(String fieldName, long startTime, long endTime) {
        this.fieldName = fieldName;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getFieldName() {
        return fieldName;
    }

    public long getStartTime() {
        return startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    @Override
    public String getQueryString() {
        return "{\"range\": {\"" + fieldName + "\":" +
                "{\"gte\": " + startTime + "," +
                "\"lte\": " + endTime + "," +
                "\"format\": \"epoch_millis\"}}}";
    }
}
