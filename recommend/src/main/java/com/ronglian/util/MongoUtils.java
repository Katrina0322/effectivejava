package com.ronglian.util;

import com.google.common.base.Preconditions;
import com.mongodb.spark.MongoSpark;
import com.mongodb.spark.config.ReadConfig;
import com.mongodb.spark.rdd.api.java.JavaMongoRDD;
import com.ronglian.configuration.SparkConfig;
import com.ronglian.core.DataTransFunction;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by spark on 18-5-2.
 */
public class MongoUtils{
    private static final Logger logger = LoggerFactory.getLogger(MongoUtils.class);

    public static <T> JavaRDD<T> loadData(String database, String collection, String query, DataTransFunction<Document, T> trans){
        Preconditions.checkNotNull(database, "database was null");
        Preconditions.checkNotNull(collection, "collection was null");
        JavaSparkContext jsc = SparkConfig.getInstance().getJavaSparkContext();
        Map<String, String> readOverrides = new HashMap<>();
        readOverrides.put("database", database);
        readOverrides.put("collection", collection);
        readOverrides.put("readPreference.name", "secondaryPreferred");
        ReadConfig readConfig = ReadConfig.create(jsc).withOptions(readOverrides);
        JavaMongoRDD<Document> customRdd = MongoSpark.load(jsc, readConfig);
        JavaMongoRDD<Document> aggregatedRdd = customRdd.withPipeline(Collections.singletonList(Document.parse(query)));
        return DataCleanUtils.transform(aggregatedRdd, trans);
    }

}
