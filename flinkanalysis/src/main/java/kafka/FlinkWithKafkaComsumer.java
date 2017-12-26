package kafka;


import com.alibaba.fastjson.JSONObject;
import org.apache.flink.api.common.ExecutionConfig;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.functions.ReduceFunction;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.api.common.typeutils.TypeSerializer;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.api.java.tuple.Tuple;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.KeyedStream;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.datastream.WindowedStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.AssignerWithPeriodicWatermarks;
import org.apache.flink.streaming.api.functions.timestamps.AscendingTimestampExtractor;
import org.apache.flink.streaming.api.watermark.Watermark;
import org.apache.flink.streaming.api.windowing.assigners.WindowAssigner;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.api.windowing.triggers.Trigger;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.streaming.api.windowing.windows.Window;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer09;

import javax.annotation.Nullable;
import java.util.Collection;
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
        properties.setProperty("bootstrap.servers", "10.128.5.6:9092");
        properties.setProperty("group.id", "test");
        FlinkKafkaConsumer09<String> kafkaConsumer09 = new FlinkKafkaConsumer09<>("apm-web-trans-agg-1", new SimpleStringSchema(), properties);
        kafkaConsumer09.setStartFromEarliest();
        env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);
//        kafkaConsumer09.assignTimestampsAndWatermarks(new AssignerWithPeriodicWatermarks<String>() {
//            @Nullable
//            @Override
//            public Watermark getCurrentWatermark() {
//                return null;
//            }
//
//            @Override
//            public long extractTimestamp(String s, long l) {
//                return 0;
//            }
//        });
        DataStream<String> stream = env.addSource(kafkaConsumer09);
//       stream.addSink(new SinkFunction<String>() {
//           @Override
//           public void invoke(String value, Context context) throws Exception {
//               System.out.println(value);
//           }
//       });
        DataStream<ApmTrans> apm = stream.map(new MapFunction<String, ApmTrans>() {
            private static final long serialVersionUID = 5410801009568806675L;

            @Override
            public ApmTrans map(String s) throws Exception {
                return JSONObject.parseObject(s, ApmTrans.class);
            }
        });

        KeyedStream<ApmTrans, Long> key = apm.keyBy(new KeySelector<ApmTrans, Long>() {
            private static final long serialVersionUID = 1495012476835593818L;

            @Override
            public Long getKey(ApmTrans apmTrans) throws Exception {
                return apmTrans.getStartTime() / 1000;
            }
        });

        WindowedStream<ApmTrans, Long, TimeWindow> windows = key.timeWindow(Time.seconds(30));
        windows.reduce(new ReduceFunction<ApmTrans>() {
            @Override
            public ApmTrans reduce(ApmTrans apmTrans, ApmTrans t1) throws Exception {
                ApmTrans apmTrans1 = new ApmTrans();

                return null;
            }
        });

//        key.window(new WindowAssigner<ApmTrans, Window>() {
//            private static final long serialVersionUID = -8854510666154522903L;
//
//            @Override
//            public Collection<Window> assignWindows(ApmTrans apmTrans, long l, WindowAssignerContext windowAssignerContext) {
//                return null;
//            }
//
//            @Override
//            public Trigger<ApmTrans, Window> getDefaultTrigger(StreamExecutionEnvironment streamExecutionEnvironment) {
//                return null;
//            }
//
//            @Override
//            public TypeSerializer<Window> getWindowSerializer(ExecutionConfig executionConfig) {
//                return null;
//            }
//
//            @Override
//            public boolean isEventTime() {
//                return false;
//            }
//        });
//        sum.print();
        env.execute();
    }
}
