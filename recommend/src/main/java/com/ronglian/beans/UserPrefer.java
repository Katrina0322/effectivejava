package com.ronglian.beans;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by spark on 18-5-2.
 */
public class UserPrefer implements Serializable{
    private static final long serialVersionUID = 3675354981458588838L;
    private String userId;
    private String itemId;
    private double rating;
    private Date opTime;

    public UserPrefer() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public Date getOpTime() {
        return opTime;
    }

    public void setOpTime(Date opTime) {
        this.opTime = opTime;
    }
}
