package com.ronglian.contentBase;

import com.google.common.collect.Lists;
import com.ronglian.beans.ItemFeature;
import com.ronglian.configuration.SparkConfig;
import com.ronglian.core.RecommendModel;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.linalg.Vectors;
import org.apache.spark.mllib.optimization.*;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.storage.StorageLevel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import scala.Tuple2;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by spark on 18-5-2.
 */
public class ContentBaseRec implements RecommendModel {
    private static final Logger logger = LoggerFactory.getLogger(ContentBaseRec.class);
//    private static SparkSession session = SparkConfig.getInstance().getSparkSession();
    private String path;
    private JavaRDD<ItemFeature> itemFeatures;
    private Map<String, Integer> featuresIndex;

    private JavaPairRDD<String, Vector> model;
    
    private Optimizer optimizer = new GradientDescent( new LeastSquaresGradient(), new L1Updater());

    public ContentBaseRec(JavaRDD<ItemFeature> itemFeatures, String path) {
        this.itemFeatures = itemFeatures;
        this.path = path;
    }

    @Override
    public <T> void train(JavaRDD<T> train, Class<T> clazz) {
        itemFeatures.persist(StorageLevel.MEMORY_AND_DISK());

        JavaPairRDD<String, Tuple2<String, Double>> itemUserRatings = getItemUserRatings(train, clazz);

        int featureNums = getFeatureNums();
        
        JavaPairRDD<String, Tuple2<Integer, Double>> itemFeaturesPair = itemFeatures.mapToPair((PairFunction<ItemFeature, String, Tuple2<Integer, Double>>) itemFeature -> {
            int index = featuresIndex.get(itemFeature.getFeature());
            return new Tuple2<>(itemFeature.getItemId(), new Tuple2<>(index, itemFeature.getWeight()));
        });

        trainModel(itemUserRatings, featureNums, itemFeaturesPair);

    }


    @Override
    public Dataset<Row> predict(Dataset<Row> test) {
        return null;
    }


    /**
     * create Map<feature,id>
     * @return features number
     */
    private int getFeatureNums() {
        List<String> features = itemFeatures.map((Function<ItemFeature, String>) ItemFeature::getFeature).distinct().collect();


        int featureNums = features.size();

        featuresIndex = new ConcurrentHashMap<>(features.size());

        for(int i = 0; i < features.size(); i++){
            featuresIndex.put(features.get(i), i);
        }
        return featureNums;
    }

    /**
     * use GradientDescent to train the model
     * @param itemUserRatings
     * @param featureNums
     * @param itemFeaturesPair
     */
    private void trainModel(JavaPairRDD<String, Tuple2<String, Double>> itemUserRatings, final int featureNums, JavaPairRDD<String, Tuple2<Integer, Double>> itemFeaturesPair) {
        model = itemFeaturesPair.cogroup(itemUserRatings).mapToPair((PairFunction<Tuple2<String, Tuple2<Iterable<Tuple2<Integer, Double>>, Iterable<Tuple2<String, Double>>>>, Iterable<Tuple2<Integer, Double>>, Iterable<Tuple2<String, Double>>>) stringTuple2Tuple2 -> new Tuple2<>(stringTuple2Tuple2._2._1, stringTuple2Tuple2._2._2)).flatMapValues((Function<Iterable<Tuple2<String, Double>>, Iterable<Tuple2<String, Double>>>) tuple2s -> tuple2s).mapToPair(new PairFunction<Tuple2<Iterable<Tuple2<Integer, Double>>, Tuple2<String, Double>>, String, Tuple2<Object, Vector>>() {
            @Override
            public Tuple2<String, Tuple2<Object, Vector>> call(Tuple2<Iterable<Tuple2<Integer, Double>>, Tuple2<String, Double>> iterableTuple2Tuple2) throws Exception {
                String userId = iterableTuple2Tuple2._2._1;
                Object rating = iterableTuple2Tuple2._2._2;
                Vector vector = Vectors.sparse(featureNums, iterableTuple2Tuple2._1);
                return new Tuple2<>(userId, new Tuple2<>(rating, vector));
            }
        }).groupByKey().mapValues((Function<Iterable<Tuple2<Object, Vector>>, JavaRDD<Tuple2<Object, Vector>>>) tuple2s -> SparkConfig.getInstance().getJavaSparkContext().parallelize(Lists.newArrayList(tuple2s))).mapToPair(new PairFunction<Tuple2<String, JavaRDD<Tuple2<Object, Vector>>>, String, Vector>() {
            @Override
            public Tuple2<String, Vector> call(Tuple2<String, JavaRDD<Tuple2<Object, Vector>>> stringJavaRDDTuple2) throws Exception {
                Vector optimize = optimizer.optimize(stringJavaRDDTuple2._2.rdd(), Vectors.zeros(featureNums).toSparse());
                return new Tuple2<>(stringJavaRDDTuple2._1, optimize);
            }
        });
        model.saveAsNewAPIHadoopDataset(SparkConfig.getInstance().getJavaSparkContext().hadoopConfiguration());
    }

    /**
     * transform T to UserPrefer
     * @param train UserItemRatings
     * @param clazz UserPrefer.class
     * @param <T>
     * @return the RDD of Tuple2<ItemId,<UserId, Ratings>>
     */
    private <T> JavaPairRDD<String, Tuple2<String, Double>> getItemUserRatings(JavaRDD<T> train, Class<T> clazz) {
        return train.mapToPair((PairFunction<T, String, Tuple2<String, Double>>) t -> {
                String userId = null;
                String itemId = null;
                double rating = 0;
                try {
                    Field fieldUserId = clazz.getDeclaredField("userId");
                    fieldUserId.setAccessible(true);
                    userId = (String) fieldUserId.get(t);
                    Field fieldItemId = clazz.getDeclaredField("itemId");
                    fieldItemId.setAccessible(true);
                    itemId = (String) fieldItemId.get(t);
                    Field fieldRating = clazz.getDeclaredField("rating");
                    fieldRating.setAccessible(true);
                    rating = (double) fieldRating.get(t);
                } catch (NoSuchFieldException | IllegalAccessException e) {
                        logger.error("NoSuchFieldException", e);
                }
                return new Tuple2<>(itemId, new Tuple2<>(userId, rating));
            }).filter((Function<Tuple2<String, Tuple2<String, Double>>, Boolean>) stringTuple2Tuple2 -> stringTuple2Tuple2._1 != null);
    }
   
}
