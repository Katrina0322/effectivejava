package base;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.spark.SparkConf;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.kafka010.ConsumerStrategies;
import org.apache.spark.streaming.kafka010.KafkaUtils;
import org.apache.spark.streaming.kafka010.LocationStrategies;

import java.util.*;

/**
 * filename: SparkEnv
 * Description:
 * Author: ubuntu
 * Date: 4/16/18 10:50 AM
 */
public class SparkEnv {
    private static volatile JavaStreamingContext jsc;

    public static JavaInputDStream<ConsumerRecord<String, String>> setupDiretcStreamFromKafka(Properties config, final String appId){
        String master = config.getProperty("spark.master");
        String checkpoint_dir = config.getProperty("spark.checkpoint.dir");
        int timeWindows = Integer.parseInt(config.getProperty("time.window"));

        String maxCores = config.getProperty("spark.cores", "2");
        String memory = config.getProperty("spark.memory", "1g");

        /* kafka settings */
        Map<String, Object> kafkaParams = new HashMap<>();
        kafkaParams.put("bootstrap.servers", "localhost:9092");
        kafkaParams.put("key.deserializer", StringDeserializer.class);
        kafkaParams.put("value.deserializer", StringDeserializer.class);
        kafkaParams.put("group.id", appId);
        kafkaParams.put("auto.offset.reset", "latest");
        kafkaParams.put("enable.auto.commit", false);

        String[] topic_list = config.getProperty("topic.list").split(",");
        HashSet<String> topics = new HashSet<>();
        Collections.addAll(topics, topic_list);
//        String zoo = config.getProperty("kafaka.zoo");
//        kafkaParams.put("metadata.broker.list", broker);
//        String broker = config.getProperty("metadata.broker.list", "localhost:9092");
//        kafkaParams.put("zookeeper.connection.timeout.ms", "10000");
        /**
         * spark.master            spark://5.6.7.8:7077
         * spark.submit.deployMode  "client" or "cluster"
         * spark.logConf
         * spark.executor.memory   1g
         * spark.executor.memoryOverhead
         * spark.eventLog.enabled  true
         * spark.serializer        org.apache.spark.serializer.KryoSerializer
         * spark.app.name
         * spark.driver.cores
         * spark.driver.maxResultSize
         * spark.driver.memory    In client mode, this config must not be set through the SparkConf directly in your application, because the driver JVM has already started at that point. Instead, please set this through the --driver-memory command line option or in your default properties file.
         * spark.driver.memoryOverhead
         *
         * spark.driver.extraJavaOptions
         * spark.executor.extraJavaOptions
         */
        SparkConf sparkConf = (new SparkConf()).setAppName(appId).set("spark.executor.memory", memory).set("spark.cores.max", String.valueOf(maxCores)).set("spark.executor.extraJavaOptions", "-Djava.net.preferIPv4Stack=true -XX:+UseConcMarkSweepGC -XX:+UseParNewGC -XX:CMSInitiatingOccupancyFraction=75 -XX:+UseCMSInitiatingOccupancyOnly").setMaster(master);
        if(jsc == null) {
            synchronized (SparkEnv.class) {
                if (jsc == null) jsc = new JavaStreamingContext(sparkConf, Durations.seconds(timeWindows));
            }
        }
        if(checkpoint_dir != null) {
            jsc.checkpoint(checkpoint_dir);
        }
        JavaInputDStream<ConsumerRecord<String, String>> stream =
                KafkaUtils.createDirectStream(
                        jsc,
                        LocationStrategies.PreferConsistent(),
                        ConsumerStrategies.<String, String>Subscribe(topics, kafkaParams)
                );
        return  stream;
    }


    public static JavaStreamingContext getJsc() {
        return jsc;
    }
}
