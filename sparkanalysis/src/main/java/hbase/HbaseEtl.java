package hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.spark.JavaHBaseContext;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaReceiverInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;

/**
 * used to
 * Created by tianjin on 11/30/16.
 */
public class HbaseEtl {
    private HbaseEtl() {
    }

    public static void dataEtl(String host,int port,String tableName){

        SparkConf sparkConf =
                new SparkConf().setAppName("JavaHBaseStreamingBulkPutExample " +
                        tableName + ":" + port + ":" + tableName);
        JavaSparkContext jsc = new JavaSparkContext(sparkConf);

        try {

            JavaStreamingContext jssc = new JavaStreamingContext(jsc, Durations.seconds(1));

            JavaReceiverInputDStream<String> javaDstream =
                    jssc.socketTextStream(host, port);

            Configuration conf = HBaseConfiguration.create();

            JavaHBaseContext hbaseContext = new JavaHBaseContext(jsc, conf);

            hbaseContext.streamBulkPut(javaDstream, TableName.valueOf(tableName),new PutFunction());
        } finally {
            jsc.stop();
        }

    }

    static class PutFunction implements Function<String, Put> {

        @Override
        public Put call(String v1) throws Exception {
            String[] part = v1.split("\t");
            Put put = new Put(Bytes.toBytes(part[0]));

            put.addColumn(Bytes.toBytes(part[1]),
                    Bytes.toBytes(part[2]),
                    Bytes.toBytes(part[3]));
            return put;
        }
    }
}
