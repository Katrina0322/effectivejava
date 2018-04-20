package com.ronglian.config;


import java.util.regex.Pattern;

/**
 * Created by spark on 4/19/18.
 */
public class Constant {
    public static final Pattern COMMA = Pattern.compile(",");
    public static final Pattern COLON = Pattern.compile(":");
    public static final String MONGO_SERVER = "localhost:27017";
    public static final String KAFKA_SERVER = "10.50.1.197:19092;10.50.1.198:19092;10.50.1.202:19092";
    public static final String USER = "TIANJIN";
    public static final String PASSWORD = "";
    public static final String DATABASE = "tianjin";
    public static final String COLLECTION = "webpage";
    public static final int BATCH_SIZE = 200;
}
