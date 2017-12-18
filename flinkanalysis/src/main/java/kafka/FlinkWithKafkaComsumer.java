package kafka;


import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.sink.SinkFunction;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer09;

import java.util.Properties;

/**
 * filename: FlinkWithKafkaComsumer
 * Description:
 * Author: ubuntu
 * Date: 12/18/17 10:33 AM
 */
public class FlinkWithKafkaComsumer {
    private static final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();


    public static void main(String[] args) throws Exception {
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", "10.128.9.207:9092");
        properties.setProperty("group.id", "test");
        FlinkKafkaConsumer09<String> kafkaConsumer09 = new FlinkKafkaConsumer09<>("ni-tcp", new SimpleStringSchema(), properties);
        kafkaConsumer09.setStartFromEarliest();
        DataStream<String> stream = env.addSource(kafkaConsumer09);
//       stream.addSink(new SinkFunction<String>() {
//           @Override
//           public void invoke(String value, Context context) throws Exception {
//               System.out.println(value);
//           }
//       });
        stream.print();
        env.execute();
    }
}
