package com.study.es.kafka;


import com.ronglian.beans.WebpageExBean;
import com.ronglian.functions.ConsumerFunction;
import com.ronglian.functions.MongodbInsertFunction;
import com.study.es.functions.ConsumerFunction;
import com.study.es.functions.MongodbInsertFunction;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collections;
import java.util.Properties;

/**
 * Created by spark on 4/19/18.
 */
public class KakfaConsumerTest {

    @Test
    public void fromKafka(){
        ConsumerFunction<String, String> function = new MongodbInsertFunction<>();
//        KafkaConsumerUtil.consumer("rbt_webpage", "10.50.1.197:19092;10.50.1.198:19092;10.50.1.202:19092", "tianjin", function);
    }

    @Test
    public void fromKafkaPrint(){
        Properties props = KafkaConsumerUtil.setProperties("tianjin");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Collections.singletonList("rbt_webpage"));
//        consumer.(consumer.assignment());
        int count = 0;
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(15);
            for (ConsumerRecord<String, String> record : records)
                System.out.println("offset = " + record.offset() + "record : " +  record.toString());
            consumer.commitSync();
            if(records.isEmpty()) {
                count++;
                if(count > 100) break;
            }
        }
        Assert.assertEquals(101, count);

    }
}
