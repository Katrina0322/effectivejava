package com.ronglian.beans;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by spark on 18-5-2.
 */
public class ItemFeature implements Serializable {
    private static final long serialVersionUID = -9081127946018480508L;
    private String itemId;
    private String feature;
    private double weight;
    private Date opTime;

    public ItemFeature(String itemId, String feature, double weight) {
        this.itemId = itemId;
        this.feature = feature;
        this.weight = weight;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public Date getOpTime() {
        return opTime;
    }

    public void setOpTime(Date opTime) {
        this.opTime = opTime;
    }
}
