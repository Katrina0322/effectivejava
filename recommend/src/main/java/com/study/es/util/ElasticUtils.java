package com.study.es.util;

import com.ronglian.configuration.SparkConfig;
import com.ronglian.core.DataTransFunction;
import com.study.es.configuration.SparkConfig;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.Function;
import org.elasticsearch.spark.rdd.EsSpark;
import scala.Tuple2;
import scala.collection.Map;

/**
 * Created by spark on 18-5-3.
 */
public class ElasticUtils {
    public static <T> JavaRDD<T> query(String source, String query, DataTransFunction<Tuple2<String, Map<String, Object>>, T> trans){
        return EsSpark.esRDD(SparkConfig.getInstance().getJavaSparkContext().sc(), source, query)
                .toJavaRDD().map((Function<Tuple2<String, Map<String, Object>>, T>) trans::transform);

    }
}
