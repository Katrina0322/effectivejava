package org.microframe.rpc.sparktest;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.util.StatCounter;

import java.io.Serializable;
import java.util.Comparator;

/**
 * music recommend
 * Created by tianjin on 4/7/16.
 */
public class MusicRecommend {
    public static void main(String[] args) {
        SparkConf sparkConf = new SparkConf();
        JavaSparkContext jsc = new JavaSparkContext("local[2]", "music", sparkConf);
        JavaRDD<String> artist = jsc.textFile("hdfs://10.128.9.220:9000/tianjin/musicdata/artist_data.txt");

        JavaRDD<String> user_artist = jsc.textFile("hdfs://10.128.9.220:9000/tianjin/musicdata/user_artist_data.txt");

        StatCounter stats = new StatCounter();

        JavaRDD<Double> user =  user_artist.map(new Function<String, Double>() {
            @Override
            public Double call(String v1) throws Exception {

                return Double.parseDouble(v1.split(" ")[0]);
            }
        });

        for(Double a:user.collect()){
            stats.merge(a);
        }

        System.out.println("_______user_artist行数为________" + user.count());

        System.out.print("_______________" + user.max(new MyCompartor()));

        System.out.print("______user_artist状态为_________" + stats.toString());





    }

    static class MyCompartor implements Comparator<Double>,Serializable{

        @Override
        public int compare(Double o1, Double o2) {
            return o1 - o2 > 0 ? 1 : 0;
        }
    }
}
