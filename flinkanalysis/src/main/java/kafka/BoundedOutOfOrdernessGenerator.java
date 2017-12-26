package kafka;

import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.functions.AssignerWithPeriodicWatermarks;
import org.apache.flink.streaming.api.watermark.Watermark;

import javax.annotation.Nullable;
import java.text.SimpleDateFormat;

/**
 * filename: BoundedOutOfOrdernessGenerator
 * Description:
 * Author: ubuntu
 * Date: 12/26/17 2:44 PM
 */
public class BoundedOutOfOrdernessGenerator implements AssignerWithPeriodicWatermarks<Tuple2<String, Long>> {
    private static final long serialVersionUID = -969265059222879500L;
    private final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    private long currentMaxTimestamp;
    @Nullable
    @Override
    public Watermark getCurrentWatermark() {
        long maxOutOfOrderness = 5000;
        return new Watermark(currentMaxTimestamp - maxOutOfOrderness);
    }

    @Override
    public long extractTimestamp(Tuple2<String, Long> stringLongTuple2, long l) {
        long timestamp = stringLongTuple2.getField(1);
        System.out.println("currentMaxTimestamp:" + currentMaxTimestamp + "=" + format.format(currentMaxTimestamp));
        currentMaxTimestamp = Math.max(timestamp, currentMaxTimestamp);
        System.out.println("key:" + stringLongTuple2.getField(0) + "\t timestamp:" + format.format(timestamp) + "\t currentMaxTimestamp:" + currentMaxTimestamp + "=" + format.format(currentMaxTimestamp) + "\t water" + getCurrentWatermark().toString());
        return timestamp;
    }
}
