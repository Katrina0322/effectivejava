package com.study.es.configuration;

import com.ronglian.util.ConfigUtil;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.mllib.recommendation.ALS;
import org.apache.spark.sql.SparkSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by spark on 18-5-2.
 */
public class SparkConfig {

    private static final Logger logger = LoggerFactory.getLogger(SparkConfig.class);
    private static final SparkConfig ourInstance = new SparkConfig();
    private volatile SparkConf sparkConf;
    private volatile JavaSparkContext javaSparkContext;
    private volatile SparkSession sparkSession;
    public static SparkConfig getInstance() {
        return ourInstance;
    }

    private SparkConfig() {
        String appId = ConfigUtil.getProperty("appId", "recommend");
        String master = ConfigUtil.getProperty("spark.master", "local[2]");
        String maxCores = ConfigUtil.getProperty("spark.cores", "2");
        String memory = ConfigUtil.getProperty("spark.memory", "1g");
        sparkConf = (new SparkConf())
                .setAppName(appId)
                .set("spark.executor.memory", memory)
                .set("spark.cores.max", String.valueOf(maxCores))
                .set("spark.mongodb.input.uri", ConfigUtil.getProperty("mongodb.input", "mongodb://127.0.0.1/test"))
                .set("spark.mongodb.output.uri", ConfigUtil.getProperty("mongodb.output", "mongodb://127.0.0.1/test"))
                .set("spark.executor.extraJavaOptions", "-Djava.net.preferIPv4Stack=true -XX:+UseConcMarkSweepGC -XX:+UseParNewGC -XX:CMSInitiatingOccupancyFraction=75 -XX:+UseCMSInitiatingOccupancyOnly")
                .setMaster(master);
        javaSparkContext = new JavaSparkContext(sparkConf);
        sparkSession = SparkSession.builder().config(sparkConf).getOrCreate();

    }

    public SparkConf getSparkConf() {
        return sparkConf;
    }

    public JavaSparkContext getJavaSparkContext() {
        return javaSparkContext;
    }

    public SparkSession getSparkSession() {
        return sparkSession;
    }
}
