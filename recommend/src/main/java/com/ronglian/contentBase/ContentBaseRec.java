package com.ronglian.contentBase;

import com.ronglian.configuration.SparkConfig;
import com.ronglian.core.RecommendModel;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import scala.Function1;

/**
 * Created by spark on 18-5-2.
 */
public class ContentBaseRec implements RecommendModel {
    private static SparkSession session = SparkConfig.getInstance().getSparkSession();

    @Override
    public <T> void train(JavaRDD<T> train, Class<T> clazz) {
        Dataset<Row> ratings = session.createDataFrame(train, clazz);

    }

    @Override
    public Dataset<Row> predict(Dataset<Row> test) {
        return null;
    }
}
