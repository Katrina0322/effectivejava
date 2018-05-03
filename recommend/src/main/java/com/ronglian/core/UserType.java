package com.ronglian.core;

/**
 * Created by spark on 18-5-2.
 */
public enum UserType {
    NEW_USER_WITHNONE(0),
    NEW_USER_WITHFEATURE(1),
    LITTLE_FEEDBACK_WITHNONE(2),
    LITTLE_FEEDBACK_WITHFEATURE(3),
    MORE_FEEDBACK_WITHNONE(4),
    MORE_FEEDBACK_WITHFEATUREURE(5);

    private int id;

    UserType(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

}
