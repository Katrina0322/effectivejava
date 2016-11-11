package musicRecommend;


import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.PairFunction;
import scala.Tuple2;

/**
 * music recommend
 * Created by tianjin on 4/7/16.
 */
public class MusicRecommend {
    public static void main(String[] args) {
        SparkConf sparkConf = new SparkConf();
        JavaSparkContext jsc = new JavaSparkContext("local[2]", "music", sparkConf);

        JavaRDD<Double> user_artist = jsc.textFile("hdfs://10.128.9.220:9000/tianjin/musicdata/user_artist_data.txt").map(new Function<String, Double>() {
            @Override
            public Double call(String v1) throws Exception {

                return Double.parseDouble(v1.split(" ")[0]);
            }
        });

        JavaPairRDD<Integer,String> artist = jsc.textFile("hdfs://10.128.9.220:9000/tianjin/musicdata/artist_data.txt").mapToPair(new PairFunction<String, Integer, String>() {
            @Override
            public Tuple2<Integer, String> call(String s) throws Exception {
                Tuple2<Integer,String> tuple = null;
                try {
                    tuple = new Tuple2<Integer, String>(Integer.parseInt(s.split("\t")[0]),s.split("\t")[1].trim());
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }

                if(tuple._2.isEmpty()){
                    return null;
                }
                return tuple;
            }
        }).filter(new Function<Tuple2<Integer, String>, Boolean>() {
            @Override
            public Boolean call(Tuple2<Integer, String> v1) throws Exception {
                return v1 != null;
            }
        });

        JavaPairRDD<Integer,Integer> artistAlias = jsc.textFile("hdfs://10.128.9.220:9000/tianjin/musicdata/user_artist_data.txt").mapToPair(new PairFunction<String, Integer, Integer>() {
            @Override
            public Tuple2<Integer, Integer> call(String s) throws Exception {
                Tuple2<Integer,Integer> tuple = null;
                try {
                    if(s.split("\t")[0].equals("")) return null;
                    tuple = new Tuple2<>(Integer.parseInt(s.split("\t")[0]), Integer.parseInt(s.split("\t")[1].trim()));
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }
                return tuple;
            }
        }).filter(new Function<Tuple2<Integer, Integer>, Boolean>() {
            @Override
            public Boolean call(Tuple2<Integer, Integer> v1) throws Exception {
                return v1 != null;
            }
        });








    }
}
