package com.ronglian.core;

/**
 * Created by spark on 18-5-3.
 */
public enum RecModelType {
    HOT_ISSUE(0, "hotIssue"),
    USER_ITEM_CF(1, "userItemCF"),
    CONTENT_BASE(2, "contentBase"),
    USER_FEATURE(3, "userFeature");
    private int index;
    private String name;

    RecModelType(int index, String name) {
        this.index = index;
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public String getName() {
        return name;
    }
}
