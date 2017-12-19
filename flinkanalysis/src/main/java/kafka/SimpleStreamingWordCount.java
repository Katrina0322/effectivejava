package kafka;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.tuple.Tuple3;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

import java.util.Arrays;

/**
 * filename: SimpleStreamingWordCount
 * Description:
 * Author: ubuntu
 * Date: 12/19/17 10:49 AM
 */
public class SimpleStreamingWordCount {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment environment = StreamExecutionEnvironment.createLocalEnvironment();
        // ProcessingTime: 根据Task所在节点的本地时钟切分时间窗口(流当前时间)
        // EventTime: 根据消息自带的时间戳切分时间窗口(需定义time watermarks;缓存一定时间窗口的所有消息,直至确认所有消息均被处理,才可以释放该窗口;若乱序的消息延迟较高,等待时间被延长,会影响分布式系统整体的吞吐量和延迟)
        // IngestionTime: 根据消息而不是节点时钟切分时间窗口,时间戳是在消息流入Flink流处理系统时自动生成的增量时间戳(是前两种方式的折衷)
        environment.setStreamTimeCharacteristic(TimeCharacteristic.ProcessingTime);
        String[] lines = {
                "201603011201,#DCFlinkMeetup",
                "201603011201,#DcFlinkMeetup",
                "201603011201,#Flink",
                "201603011201,#Flink",
                "201603011201,#DCFlinkMeetup"};
        DataStream<String> source = environment.fromCollection(Arrays.asList(lines));
        DataStream<Tuple3<String, String, Integer>> counts = source.map(new Tokenizer()).keyBy(0,1).sum(2);
        counts.printToErr();

        environment.execute();
    }

    static final class Tokenizer implements MapFunction<String, Tuple3<String, String, Integer>>{

        private static final long serialVersionUID = 134142063794142313L;

        @Override
        public Tuple3<String, String, Integer> map(String s) throws Exception {
            String[] tokens = s.toLowerCase().split(",");
            String timestamp = tokens[0].substring(0, 10);
            String word = tokens[1].toLowerCase();
            return new Tuple3<>(timestamp, word, 1);
        }
    }
}
