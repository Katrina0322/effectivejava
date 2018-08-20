package com.study.es.kafka;

import com.ronglian.config.Constant;
import com.ronglian.functions.ConsumerFunction;
import com.study.es.config.Constant;
import com.study.es.functions.ConsumerFunction;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;

/**
 * Created by spark on 4/19/18.
 */
public class KafkaConsumerUtil {

    public static <K, V> void consumer(String topic, String groupId, ConsumerFunction<K, V> consumerFunction) throws IOException {
        Properties props = setProperties( groupId);
        KafkaConsumer<K, V> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Collections.singletonList(topic));
        List<ConsumerRecord<K, V>> buffer = new ArrayList<>();
        final int minBatchSize = Constant.BATCH_SIZE;
        consumer.poll(0);
        consumer.seekToBeginning(consumer.assignment());
        while (true) {
            ConsumerRecords<K, V> records = consumer.poll(Constant.BATCH_SIZE);
            for (ConsumerRecord<K, V> record : records) buffer.add(record);
            if (buffer.size() >= minBatchSize) {
                consumerFunction.batchConsumer(buffer);
                consumer.commitSync();
                buffer.clear();
            }
        }
    }

    public static Properties setProperties(String groupId) {
        Properties props = new Properties();
        props.put("bootstrap.servers", Constant.KAFKA_SERVER);
        props.put("group.id", groupId);
        props.put("enable.auto.commit", "false");
        props.put("auto.commit.interval.ms", "1000");
        props.put("session.timeout.ms", "30000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        return props;
    }

}
