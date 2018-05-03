package com.ronglian.userBaseCF;

import com.ronglian.configuration.SparkConfig;
import com.ronglian.core.RecommendModel;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.ml.recommendation.ALS;
import org.apache.spark.ml.recommendation.ALSModel;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by spark on 18-5-2.
 */
public class UserBaseCF implements RecommendModel {
    private static final Logger logger = LoggerFactory.getLogger(UserBaseCF.class);
    private static SparkSession session = SparkConfig.getInstance().getSparkSession();
    private String path;
    private ALSModel model;

    public UserBaseCF(String path) {
        this.path = path;
    }

    @Override
    public <T> void train(JavaRDD<T> train, Class<T> clazz) {
        Dataset<Row> ratings = session.createDataFrame(train, clazz);
        ALS als = new ALS().setMaxIter(5)
                .setRegParam(0.01)
                .setUserCol("userId")
                .setItemCol("itemId")
                .setRatingCol("rating");
        model = als.fit(ratings);

        try {
            model.save(path);
        } catch (IOException e) {
            logger.error("ALSModel save failed", e);
        }
    }

    @Override
    public Dataset<Row> predict(Dataset<Row> test) {
        if(model == null) model = ALSModel.load(path);
        return model.transform(test);
    }
}
