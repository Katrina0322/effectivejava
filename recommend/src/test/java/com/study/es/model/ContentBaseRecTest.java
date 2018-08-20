package com.study.es.model;

import com.ronglian.beans.ItemFeature;
import com.ronglian.beans.UserPrefer;
import com.ronglian.configuration.LogSetting;
import com.ronglian.configuration.SparkConfig;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.mllib.optimization.GradientDescent;
import org.apache.spark.mllib.optimization.L1Updater;
import org.apache.spark.mllib.optimization.LeastSquaresGradient;
import org.apache.spark.mllib.optimization.Optimizer;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.junit.Before;
import org.junit.Test;
import scala.Tuple2;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * description:
 * Created by spark on 18-5-15.
 */
public class ContentBaseRecTest implements Serializable {
    private List<UserPrefer> userPreferList;
    private List<ItemFeature> itemFeatureList;

    @Before
    public void init() {
        UserPrefer userPrefer1 = new UserPrefer("aa", "A", 3.5);
        UserPrefer userPrefer2 = new UserPrefer("aa", "B", 4.5);
        UserPrefer userPrefer3 = new UserPrefer("aa", "C", 3.5);
        UserPrefer userPrefer4 = new UserPrefer("aa", "D", 1.5);

        UserPrefer userPrefer5 = new UserPrefer("bb", "A", 3.5);
        UserPrefer userPrefer6 = new UserPrefer("bb", "B", 1.5);
        UserPrefer userPrefer7 = new UserPrefer("bb", "C", 3.5);
        UserPrefer userPrefer8 = new UserPrefer("bb", "D", 4.5);

        UserPrefer userPrefer9 = new UserPrefer("cc", "A", 1.5);
        UserPrefer userPrefer10 = new UserPrefer("cc", "B", 4.5);
        UserPrefer userPrefer11 = new UserPrefer("cc", "C", 2.5);
        UserPrefer userPrefer12 = new UserPrefer("cc", "D", 5.5);

        userPreferList = Arrays.asList(userPrefer1, userPrefer2, userPrefer3, userPrefer4,
                userPrefer5, userPrefer6, userPrefer7, userPrefer8,
                userPrefer9, userPrefer10, userPrefer11, userPrefer12);


        ItemFeature itemFeature1 = new ItemFeature("A", "ccc", 0.2);
        ItemFeature itemFeature2 = new ItemFeature("A", "bbb", 0.1);
        ItemFeature itemFeature3 = new ItemFeature("A", "ddd", 0.6);
        ItemFeature itemFeature4 = new ItemFeature("A", "fff", 0.5);

        ItemFeature itemFeature5 = new ItemFeature("B", "eee", 0.3);
        ItemFeature itemFeature6 = new ItemFeature("B", "aaa", 0.1);
        ItemFeature itemFeature7 = new ItemFeature("B", "ccc", 0.2);
        ItemFeature itemFeature8 = new ItemFeature("B", "bbb", 0.4);

        ItemFeature itemFeature9 = new ItemFeature("C", "ccc", 0.3);
        ItemFeature itemFeature10 = new ItemFeature("C", "hhh", 0.3);
        ItemFeature itemFeature11 = new ItemFeature("C", "fff", 0.2);
        ItemFeature itemFeature12 = new ItemFeature("C", "bbb", 0.6);

        userPreferList = Arrays.asList(userPrefer1, userPrefer2, userPrefer3, userPrefer4,
                userPrefer5, userPrefer6, userPrefer7, userPrefer8,
                userPrefer9, userPrefer10, userPrefer11, userPrefer12);
        itemFeatureList = Arrays.asList(itemFeature1, itemFeature2, itemFeature3, itemFeature4,
                itemFeature5, itemFeature6, itemFeature7, itemFeature8,
                itemFeature9, itemFeature10, itemFeature11, itemFeature12);
    }

    static {
        LogSetting.setWarningLogLevel("org");
        LogSetting.setWarningLogLevel("akka");
        LogSetting.setWarningLogLevel("io");
        LogSetting.setWarningLogLevel("httpclient.wire");
    }


    @Test
    public void dfTest() {

        JavaRDD<UserPrefer> userPreferJavaRDD = SparkConfig.getInstance().getJavaSparkContext().parallelize(userPreferList);
        Dataset<Row> ratings = SparkConfig.getInstance().getSparkSession().createDataFrame(userPreferJavaRDD, UserPrefer.class);
        ratings.show();
    }

    @Test
    public void unionTest() {
        JavaRDD<UserPrefer> userPreferJavaRDD = SparkConfig.getInstance().getJavaSparkContext().parallelize(userPreferList);
        JavaRDD<ItemFeature> itemFeatureJavaRDD = SparkConfig.getInstance().getJavaSparkContext().parallelize(itemFeatureList);
        JavaPairRDD<String, Tuple2<String, Double>> itemUserRating = userPreferJavaRDD.mapToPair(new PairFunction<UserPrefer, String, Tuple2<String, Double>>() {
            @Override
            public Tuple2<String, Tuple2<String, Double>> call(UserPrefer userPrefer) throws Exception {
                return new Tuple2<>(userPrefer.getItemId(), new Tuple2<>(userPrefer.getUserId(), userPrefer.getRating()));
            }
        });

        JavaPairRDD<String, Tuple2<String, Double>> itemFeatureWeight = itemFeatureJavaRDD.mapToPair(new PairFunction<ItemFeature, String, Tuple2<String, Double>>() {
            @Override
            public Tuple2<String, Tuple2<String, Double>> call(ItemFeature itemFeature) throws Exception {
                return new Tuple2<String, Tuple2<String, Double>>(itemFeature.getItemId(), new Tuple2<String, Double>(itemFeature.getFeature(), itemFeature.getWeight()));
            }
        });


        String s = itemUserRating.cogroup(itemFeatureWeight).mapToPair(new PairFunction<Tuple2<String, Tuple2<Iterable<Tuple2<String, Double>>, Iterable<Tuple2<String, Double>>>>, Iterable<Tuple2<String, Double>>, Iterable<Tuple2<String, Double>>>() {
            @Override
            public Tuple2<Iterable<Tuple2<String, Double>>, Iterable<Tuple2<String, Double>>> call(Tuple2<String, Tuple2<Iterable<Tuple2<String, Double>>, Iterable<Tuple2<String, Double>>>> stringTuple2Tuple2) throws Exception {
                return new Tuple2<Iterable<Tuple2<String, Double>>, Iterable<Tuple2<String, Double>>>(stringTuple2Tuple2._2._2, stringTuple2Tuple2._2._1);
            }
        }).flatMapValues(new Function<Iterable<Tuple2<String, Double>>, Iterable<Tuple2<String, Double>>>() {
            @Override
            public Iterable<Tuple2<String, Double>> call(Iterable<Tuple2<String, Double>> tuple2s) throws Exception {
                return tuple2s;
            }
        }).first().toString();
        System.out.println(s);
    }

}
