package com.ronglian;

import com.ronglian.functions.ConsumerFunction;
import com.ronglian.functions.MongodbInsertFunction;
import com.ronglian.kafka.KafkaConsumerUtil;

import java.io.IOException;

/**
 * Created by spark on 4/20/18.
 */
public class TestMain {
    public static void main(String[] args) throws IOException {
        ConsumerFunction<String, String> function = new MongodbInsertFunction<>();
        KafkaConsumerUtil.consumer("rbt_webpage", "tianjin", function);
    }
}
