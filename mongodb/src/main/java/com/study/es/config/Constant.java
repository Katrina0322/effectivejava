package com.study.es.config;


import java.util.regex.Pattern;

/**
 * Created by spark on 4/19/18.
 */
public class Constant {
    public static final Pattern COMMA = Pattern.compile(",");
    public static final Pattern COLON = Pattern.compile(":");
//    public static final String MONGO_SERVER = "114.116.2.150:20000,114.116.3.96:20000,114.116.9.150:20000";
public static final String MONGO_SERVER = "10.43.4.207:27017";
    public static final String KAFKA_SERVER = "10.50.1.197:19092;10.50.1.198:19092;10.50.1.202:19092";
    public static final String USER = "zhh";
    public static final String PASSWORD = "zhh";
    public static final String DATABASE = "rcmd";
    public static final String COLLECTION = "user_action";
    public static final int BATCH_SIZE = 200;
}
