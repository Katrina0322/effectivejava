package com.oneapm.performance;


import my.util.LogSetting;
import org.apache.commons.logging.LogFactory;
import org.apache.spark.SparkConf;
import org.apache.spark.storage.StorageLevel;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaPairReceiverInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.kafka.KafkaUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;
import java.util.regex.Pattern;

public abstract class Analysis {
    protected final static int LOGHEADER_FIELD_COUNT = 12;
    protected final static Pattern UNDERLINE = Pattern.compile("_");
    protected final static Pattern TAB = Pattern.compile("\t");
    protected static int TIME_WINDOW_SEC;
    private static JavaStreamingContext jsc;

    static {
        LogSetting.setWarningLogLevel("org");
        LogSetting.setWarningLogLevel("akka");
        LogSetting.setWarningLogLevel("io");
        LogSetting.setWarningLogLevel("httpclient.wire");
    }

    protected static Properties getConfig(String filename) throws FileNotFoundException, IOException {
        File file = new File(filename);
        if (!file.exists()) {
            file = new File("resources/" + filename);
            if (!file.exists()) {
                file = new File("/etc/analysis/" + filename);
            }
        }
        Properties prop = null;

        if (file.exists()) {
            prop = new Properties();


            prop.load(new FileReader(file));

        }
        return prop;
    }

    protected static JavaStreamingContext getJsc() {
        return jsc;
    }

    private static String getSelfFilePath() {

        String path = Analysis.class.getProtectionDomain().getCodeSource()
                .getLocation().getFile();
        try {
            path = java.net.URLDecoder.decode(path, "UTF-8");
        } catch (java.io.UnsupportedEncodingException ex) {
            LogFactory.getLog(Analysis.class).error(ex);
        }
        File jarFile = new File(path);
        if (jarFile.isDirectory()) {
            return null;
        } else
            return jarFile.getAbsolutePath();
    }

    protected static JavaPairReceiverInputDStream<String, byte[]> setupRawStreamFromKafka(Properties config, String appId) {
        String master = config.getProperty("spark.master");
        String es_node = config.getProperty("es.node");
        String checkpoint_dir = config.getProperty("spark.checkpoint.dir");
        TIME_WINDOW_SEC = Integer.parseInt(config
                .getProperty("time.window"));
        String[] topic_list = config.getProperty("topic.list").split(",");
        String zoo = config.getProperty("kafaka.zoo");
        String maxCores = config.getProperty("spark.cores", "2");
        String memory = config.getProperty("spark.memory", "1g");

        HashMap<String, Integer> topicSetting = new HashMap<String, Integer>();
        for (String topic : topic_list)
            topicSetting.put(topic, 1);

        HashMap<String, String> kafkaSetting = new HashMap<String, String>();
        kafkaSetting.put("zookeeper.connect", zoo);
        kafkaSetting.put("group.id", appId);
        kafkaSetting.put("zookeeper.connection.timeout.ms", "10000");

        SparkConf sparkConf = new SparkConf().setAppName(appId)
                .set("spark.executor.memory", memory)
                .set("spark.cores.max", String.valueOf(maxCores))
                .set("es.nodes", es_node)
                .set("spark.executor.extraJavaOptions", "-Djava.net.preferIPv4Stack=true -XX:+UseConcMarkSweepGC -XX:+UseParNewGC -XX:CMSInitiatingOccupancyFraction=75 -XX:+UseCMSInitiatingOccupancyOnly")
                //				.set("es.nodes.discovery", "false")
                //				.set("es.nodes.client.only", "true")
                //				.set("spark.task.maxFailures", "5")
                //				.set("spark.rpc.numRetries", "5")
                .setMaster(master);

        String self = getSelfFilePath();

        if (self != null) {
            sparkConf.setJars(new String[]{self});
        }
        jsc = new JavaStreamingContext(sparkConf,
                Durations.seconds(TIME_WINDOW_SEC));
        if (checkpoint_dir != null) {
            jsc.checkpoint(checkpoint_dir);
        }

        // 接收
        return KafkaUtils
                .createStream(jsc, String.class, byte[].class,
                        kafka.serializer.StringDecoder.class,
                        kafka.serializer.DefaultDecoder.class, kafkaSetting,
                        topicSetting, StorageLevel.MEMORY_AND_DISK_SER());
    }

    protected static double justKeepMs(double val) {

        return ((long) (val * 1000000)) / 1000000.0;
    }

}
