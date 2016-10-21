package com.oneapm.performance;

import com.google.common.collect.ImmutableMap;
import com.oneapm.javabean.DBData;
import com.oneapm.javabean.DBKey;
import org.apache.commons.net.util.Base64;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.storage.StorageLevel;
import org.apache.spark.streaming.api.java.JavaPairReceiverInputDStream;
import org.elasticsearch.hadoop.cfg.ConfigurationOptions;
import org.elasticsearch.spark.rdd.api.java.JavaEsSpark;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import scala.Tuple2;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * used to
 * Created by tianjin on 10/18/16.
 */
public class SSDPerformanceTest extends Analysis{

    public static final Logger LOG = LoggerFactory.getLogger(SSDPerformanceTest.class);

    private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);

    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd", Locale.CHINA);

    public static void main(String[] args) throws IOException {

        String configfile = "database.cnf";

        Properties config = Config.getConfig(configfile);


        JavaPairReceiverInputDStream<String, byte[]> rawStream = setupRawStreamFromKafka(
                config, config.getProperty("group.id"));

        LOG.info("database config:" + config.toString());


        rawStream.foreachRDD(new Function<JavaPairRDD<String, byte[]>, Void>() {
            @Override
            public Void call(JavaPairRDD<String, byte[]> stringJavaPairRDD) throws Exception {
                JavaRDD<Map<String, ?>> es = stringJavaPairRDD.mapToPair(new PairFunction<Tuple2<String, byte[]>, DBKey, DBData>() {
                    public Tuple2<DBKey, DBData> call(Tuple2<String, byte[]> stringTuple2) throws Exception {
                        String[] database = TAB.split(new String(stringTuple2._2));

                        DBKey dbKey = new DBKey();
                        DBData dbData = new DBData();
                        String sqlString = new String(Base64.decodeBase64(database[10].trim()));
                        String storageSql;
                        if(sqlString.length() > 1000){
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
                }).persist(StorageLevel.MEMORY_AND_DISK_2() );


                if (es != null) {
                    JavaEsSpark.saveToEs(es, "ni-database-{index_name}/database", ImmutableMap.of
                            (ConfigurationOptions.ES_MAPPING_EXCLUDE, "index_name"));
                }
                return null;
            }
        });

        rawStream.context().start();
        rawStream.context().awaitTermination();

    }


}
