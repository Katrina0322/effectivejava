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
}
