package com.ronglian.util;

import com.ronglian.configuration.SparkConfig;
import org.apache.spark.api.java.JavaRDD;
import org.elasticsearch.spark.rdd.EsSpark;
import scala.Tuple2;
import scala.collection.Map;

/**
 * Created by spark on 18-5-3.
 */
public class ElasticUtils {
    public static JavaRDD<Tuple2<String, Map<String, Object>>> query(String source, String query){
        return EsSpark.esRDD(SparkConfig.getInstance().getJavaSparkContext().sc(), source, query)
                .toJavaRDD();

    }
}
