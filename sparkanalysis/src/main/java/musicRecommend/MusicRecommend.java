package musicRecommend;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;

/**
 * music recommend
 * Created by tianjin on 4/7/16.
 */
public class MusicRecommend {
    public static void main(String[] args){
        SparkConf sparkConf = new SparkConf();
        JavaSparkContext jsc = new JavaSparkContext("local[2]","music",sparkConf);
        JavaRDD<String> artist = jsc.textFile("hdfs://10.128.9.201:9000/tianjin/ds/artist_data.txt");
        System.out.println("拿到文件"+artist.toString());



        System.out.println("artist行数为"+artist.count());
    }
}
