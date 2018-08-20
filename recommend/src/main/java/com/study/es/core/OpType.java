package com.study.es.core;

/**
 * Created by spark on 18-5-3.
 */
public enum OpType {
    LOGOUT(0,"logout"),         //0登出
    LOGIN(1,"login"),           //1登录
    PAGE_VIEW(3.5,"page_view"), //2访问页面，
    FILTER(3, "filter"),        //3筛选
    SEARCH(4, "search"),        //4搜索
    CUSTOM(5, "custom"),        //5定制
    DRAFT(5, "draft"),          //6建稿
    STORE(4, "store");          //7收藏

    private final double rating;
    private final String value;

    OpType(double rating, String value) {
        this.rating = rating;
        this.value = value;
    }

    public double getRating() {
        return rating;
    }

    public String getValue() {
        return value;
    }


}
