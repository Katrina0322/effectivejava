package com.study.es.core;

import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;

/**
 * Created by spark on 18-5-2.
 */
public interface RecommendModel {
    <T> void train(JavaRDD<T> train, Class<T> clazz);
    Dataset<Row> predict(Dataset<Row> test);
}
