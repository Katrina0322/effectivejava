package com.study.es.core;

/**
 * description:
 * Created by spark on 18-5-15.
 */
public interface LifeCycle {
    public static final String START_TRAINING = "START_TRAINING";
    public static final String TRAIN_COMPLETE = "TRAIN_COMPLETE";
    public static final String TRAIN_FAILED = "TRAIN_FAILED";
    public static final String CAN_PREDICT = "CAN_PREDICT";
    public static final String DISABLED = "DISABLED";
    String getStatus();

    default boolean startTraining(){
        return START_TRAINING.equals(getStatus());
    }

    default boolean trainFailed(){
        return TRAIN_FAILED.equals(getStatus());
    }
    default boolean trainComplete(){
        return TRAIN_COMPLETE.equals(getStatus());
    }

    default boolean canPredict(){
        return CAN_PREDICT.equals(getStatus());
    }

    default boolean disabled(){
        return DISABLED.equals(getStatus());
    }
}
