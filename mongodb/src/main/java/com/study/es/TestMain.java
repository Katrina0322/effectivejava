package com.study.es;

import com.ronglian.config.Constant;
import com.ronglian.functions.ConsumerFunction;
import com.ronglian.functions.MongodbInsertFunction;
import com.ronglian.kafka.KafkaConsumerUtil;
import com.ronglian.mongodb.MongoUtil;
import com.study.es.config.Constant;
import com.study.es.mongodb.MongoUtil;

import java.io.IOException;

/**
 * Created by spark on 4/20/18.
 */
public class TestMain {
    public static void main(String[] args) throws IOException {
//        ConsumerFunction<String, String> function = new MongodbInsertFunction<>();
//        KafkaConsumerUtil.consumer("rbt_webpage", "tianjin", function);
        MongoUtil.getInstance().read(Constant.DATABASE, Constant.COLLECTION, "{}, { userId: 1, innerid: 1, _id: 0 }");
    }
}
