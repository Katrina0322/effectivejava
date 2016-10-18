package com.oneapm.performance;

import com.google.common.collect.ImmutableMap;
import com.oneapm.javabean.DBData;
import com.oneapm.javabean.DBKey;
import kafka.serializer.DefaultDecoder;
import kafka.serializer.StringDecoder;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.LogFactory;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.api.java.function.VoidFunction;
import org.apache.spark.storage.StorageLevel;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaPairReceiverInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import org.apache.spark.streaming.kafka.KafkaUtils;
import org.elasticsearch.hadoop.cfg.ConfigurationOptions;
import org.elasticsearch.spark.rdd.api.java.JavaEsSpark;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import scala.Tuple2;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Pattern;

/**
 * used to
 * Created by tianjin on 10/18/16.
 */
public class SSDPerformanceTest {
    public static final Logger LOG = LoggerFactory.getLogger(SSDPerformanceTest.class);

    protected static final Pattern TAB = Pattern.compile("\t");

    private static JavaStreamingContext jsc;

    private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd", Locale.CHINA);

    public static void main(String[] args) throws IOException {

        String configfile = "database.cnf";

        Properties config = Config.getConfig(configfile);


        JavaPairReceiverInputDStream<String, byte[]> rawStream = setupRawStreamFromKafka(
                config, config.getProperty("group.id"));

        LOG.info("database config:" + config.toString());


        rawStream.foreachRDD(new VoidFunction<JavaPairRDD<String, byte[]>>() {
            @Override
            public void call(JavaPairRDD<String, byte[]> stringJavaPairRDD) throws Exception {
                JavaRDD<Map<String, ?>> es = stringJavaPairRDD.mapToPair(new PairFunction<Tuple2<String, byte[]>, DBKey, DBData>() {
                    public Tuple2<DBKey, DBData> call(Tuple2<String, byte[]> stringTuple2) throws Exception {
                        String[] database = TAB.split(new String(stringTuple2._2));

                        DBKey dbKey = new DBKey();
                        DBData dbData = new DBData();
                        String sqlString = new String(Base64.decodeBase64(database[10].trim()));
                        String storageSql;
                        if(sqlString.length() > 500){
                            storageSql = sqlString.substring(0,1000);
                        }else{
                            storageSql = sqlString;
                        }

                        //DBKey
                        dbKey.setProbeName(database[0].trim());
                        dbKey.setCustomService(database[1].trim());
                        dbKey.setIpClient(database[2].trim());
                        dbKey.setIpServer(database[3].trim());
                        dbKey.setPortServer(database[5].trim());
                        dbKey.setTimeStart(format.format(new Date().getTime()));
                        dbKey.setOperateType(storageSql.split(" ")[0]);   //Select, Insert, Update, Drop, Procedure
                        dbKey.setDbType(database[8].trim());

                        dbKey.setResponseCode(database[9].trim());
                        dbKey.setUser(database[2].trim());
                        dbKey.setSqlString(storageSql);

                        if(!database[12].trim().equals("-")) {
                            dbData.setOperateTime(Double.parseDouble(database[12].trim()));
                        }else if(!database[7].trim().equals("-")){
                            dbData.setOperateTime(Double.parseDouble(database[7].trim()) - Double.parseDouble(database[6].trim()));
                        }else{
                            dbData.setOperateTime(0);
                        }

                        if(!database[13].trim().equals("-")) {
                            dbData.setReqTransTime(Double.parseDouble(database[13].trim()));
                        }else{
                            dbData.setReqTransTime(0);
                        }

                        if(!database[14].trim().equals("-")) {
                            dbData.setRespTransTime(Double.parseDouble(database[14].trim()));
                        }else{
                            dbData.setRespTransTime(0);
                        }

                        if(!database[15].trim().equals("-")) {
                            dbData.setRespPayload(Integer.parseInt(database[15].trim()));
                        }else{
                            dbData.setRespPayload(0);
                        }


                        dbData.setCount(1);

                        dbData.setSlowCount(1);


                        return new Tuple2<>(dbKey,dbData);

                    }
                }).filter(new Function<Tuple2<DBKey, DBData>, Boolean>() {
                    @Override
                    public Boolean call(Tuple2<DBKey, DBData> v1) throws Exception {
                        return v1 != null;
                    }
                }).reduceByKey(new Function2<DBData, DBData, DBData>() {
                    public DBData call(DBData v1, DBData v2) throws Exception {
                        DBData result = new DBData();
                        result.setOperateTime(v1.getOperateTime() + v2.getOperateTime());
                        result.setReqTransTime(v1.getReqTransTime() + v1.getReqTransTime());
                        result.setRespTransTime(v1.getRespTransTime() + v2.getRespTransTime());
                        result.setRespPayload(v1.getRespPayload() + v2.getRespPayload());
                        result.setCount(v1.getCount() + v2.getCount());
                        result.setSlowCount(v1.getSlowCount() + v1.getSlowCount());
                        return result;
                    }
                }).map(new Function<Tuple2<DBKey,DBData>, Map<String, ?>>() {
                    public Map<String, ?> call(Tuple2<DBKey, DBData> v1) throws Exception {
                        DBKey dbKey = v1._1;
                        DBData dbData = v1._2;
                        ImmutableMap.Builder<String, Object> builder = ImmutableMap.builder();
                        builder.put("index_name", sdf.format(format.parse(dbKey.getTimeStart())));
                        builder.put("probeName",dbKey.getProbeName());
                        builder.put("customService",dbKey.getCustomService());
                        builder.put("ipClient",dbKey.getIpClient());
                        builder.put("ipServer",dbKey.getIpServer());
                        builder.put("portServer",dbKey.getPortServer());
                        builder.put("operateType",dbKey.getOperateType());
                        builder.put("timeStart",format.parse(dbKey.getTimeStart()));
                        builder.put("dbType",dbKey.getDbType());
                        builder.put("user",dbKey.getUser());
                        builder.put("responseCode",dbKey.getResponseCode());
                        builder.put("sqlString",dbKey.getSqlString());
                        builder.put("operateTime",dbData.getOperateTime());
                        builder.put("reqTransTime",dbData.getReqTransTime());
                        builder.put("respTransTime",dbData.getRespTransTime());
                        builder.put("respPayload",dbData.getRespPayload());
                        builder.put("count",dbData.getCount());
                        builder.put("slowCount",dbData.getSlowCount());
                        return builder.build();
                    }
                }).cache();


                if (es != null) {
                    JavaEsSpark.saveToEs(es, "ni-database-{index_name}/database", ImmutableMap.of
                            (ConfigurationOptions.ES_MAPPING_EXCLUDE, "index_name"));
                }
            }
        });


        rawStream.context().start();
        rawStream.context().awaitTermination();

    }


    protected static JavaPairReceiverInputDStream<String, byte[]> setupRawStreamFromKafka(Properties config, String appId) {
        String master = config.getProperty("spark.master");
        String es_node = config.getProperty("es.node");
        String checkpoint_dir = config.getProperty("spark.checkpoint.dir");
        int TIME_WINDOW_SEC = Integer.parseInt(config.getProperty("time.window"));
        String[] topic_list = config.getProperty("topic.list").split(",");
        String zoo = config.getProperty("kafaka.zoo");
        String maxCores = config.getProperty("spark.cores", "2");
        String memory = config.getProperty("spark.memory", "1g");
        HashMap topicSetting = new HashMap();
        String[] kafkaSetting = topic_list;
        int sparkConf = topic_list.length;

        for (int self = 0; self < sparkConf; ++self) {
            String topic = kafkaSetting[self];
            topicSetting.put(topic, Integer.valueOf(1));
        }

        HashMap var15 = new HashMap();
        var15.put("zookeeper.connect", zoo);
        var15.put("group.id", appId);
        var15.put("zookeeper.connection.timeout.ms", "10000");
        SparkConf var14 = (new SparkConf()).setAppName(appId).set("spark.executor.memory", memory).set("spark.cores.max", String.valueOf(maxCores)).set("es.nodes", es_node).set("spark.executor.extraJavaOptions", "-Djava.net.preferIPv4Stack=true -XX:+UseConcMarkSweepGC -XX:+UseParNewGC -XX:CMSInitiatingOccupancyFraction=75 -XX:+UseCMSInitiatingOccupancyOnly").setMaster(master);
        String var16 = getSelfFilePath();
        jsc = new JavaStreamingContext(var14, Durations.seconds((long)TIME_WINDOW_SEC));
        if(checkpoint_dir != null) {
            jsc.checkpoint(checkpoint_dir);
        }

        return KafkaUtils.createStream(jsc, String.class, byte[].class, StringDecoder.class, DefaultDecoder.class, var15, topicSetting, StorageLevel.MEMORY_AND_DISK_SER());
    }

    private static String getSelfFilePath() {
        String path = SSDPerformanceTest.class.getProtectionDomain().getCodeSource().getLocation().getFile();

        try {
            path = URLDecoder.decode(path, "UTF-8");
        } catch (UnsupportedEncodingException var2) {
            LogFactory.getLog(SSDPerformanceTest.class).error(var2);
        }

        File jarFile = new File(path);
        return jarFile.isDirectory()?null:jarFile.getAbsolutePath();
    }
}
