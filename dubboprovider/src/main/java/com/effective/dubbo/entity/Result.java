package com.effective.dubbo.entity;

import java.util.Date;

/**
 * @description: result
 * @author: tianjin
 * @email: eternity_bliss@sina.cn
 * @create: 2018-06-01 上午9:44
 */
public class Result{
    private String city;
    private Date date;

    public Result(String city, Date date) {
        this.city = city;
        this.date = date;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
