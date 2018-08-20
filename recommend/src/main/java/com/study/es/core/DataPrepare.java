package com.study.es.core;

import org.apache.spark.api.java.JavaRDD;

/**
 * Created by spark on 18-5-2.
 */
public interface DataPrepare<T> {
    JavaRDD<T> prepareData();
}
