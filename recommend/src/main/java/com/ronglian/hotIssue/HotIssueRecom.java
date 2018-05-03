package com.ronglian.hotIssue;

import com.ronglian.core.RecommendModel;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;

/**
 * Created by spark on 18-5-2.
 */
public class HotIssueRecom implements RecommendModel{


    @Override
    public <T> void train(JavaRDD<T> train, Class<T> clazz) {

    }

    @Override
    public Dataset<Row> predict(Dataset<Row> test) {
        return null;
    }
}
