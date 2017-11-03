package kafka_test;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.util.*;

/**
 * filename: ConsumerTest
 * Description:
 * Author: ubuntu
 * Date: 11/2/17 4:57 PM
 */
public class ConsumerTest {
    public static void consumer(){
        Properties props = new Properties();
        props.put("bootstrap.servers", "10.128.5.14:9092,10.128.5.11:9092,10.128.5.13:9092");
        props.put("group.id", "topictest");
        props.put("enable.auto.commit", "true");
        props.put("auto.commit.interval.ms", "1000");
        props.put("session.timeout.ms", "30000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Arrays.asList("topictest"));
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(100);
            for (ConsumerRecord<String, String> record : records)
                System.out.printf("offset = %d, key = %s, value = %s", record.offset(), record.key(), record.value());
        }
    }

    public static void consumer(String topic, String servers, String groupId){
        Properties props = new Properties();
        props.put("bootstrap.servers", servers);
        props.put("group.id", groupId);
        props.put("enable.auto.commit", "false");
        props.put("auto.commit.interval.ms", "1000");
        props.put("session.timeout.ms", "30000");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(Collections.singletonList(topic));

        List<ConsumerRecord<String, String>> buffer = new ArrayList<>();
        final int minBatchSize = 20;
        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(100);
            for (ConsumerRecord<String, String> record : records) buffer.add(record);
            if (buffer.size() >= minBatchSize) {
//                insertIntoDb(buffer);
                System.out.println(buffer.toString());
                consumer.commitSync();
                buffer.clear();
            }
//            consumer.commitAsync();
        }
    }

    public static void main(String[] args) {
        consumer("tttest1", "10.128.5.14:9092,10.128.5.11:9092,10.128.5.13:9092", "topictest");
    }
}
