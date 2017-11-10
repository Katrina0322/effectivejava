package kafka_test;


import ResourceProvider.SharedPool;
import kafka.admin.AdminUtils;
import kafka.utils.ZkUtils;
import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.security.JaasUtils;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.HashMap;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * filename: TopicTest
 * Description:
 * Author: ubuntu
 * Date: 10/25/17 11:34 AM
 */
public class TopicTest {
//    private static final ExecutorService executor = SharedPool.EXECUTOR;

    public static void createTopic(String zooServer, String topicName, int partitions, int replicationFactor){
        ZkUtils zkUtils = ZkUtils.apply(zooServer, 30000, 30000, JaasUtils.isZkSecurityEnabled());
        if(!AdminUtils.topicExists(zkUtils, topicName)) {
            AdminUtils.createTopic(zkUtils, topicName, partitions, replicationFactor, new Properties());
        }

        zkUtils.close();
    }

    public static void deleteTopic(String zooServer, String topicName){
        ZkUtils zkUtils = ZkUtils.apply(zooServer, 30000, 30000, JaasUtils.isZkSecurityEnabled());
        if(AdminUtils.topicExists(zkUtils, topicName)) {
            AdminUtils.deleteTopic(zkUtils, topicName);
        }
        zkUtils.close();
    }

    public static int toPositive(int number) {
        return number & 2147483647;
    }

    public static void sendMessage(final String topicName, final String message){
//        final CountDownLatch start = new CountDownLatch(1);
        Properties props = new Properties();
        props.put("bootstrap.servers", "10.128.5.14:9092,10.128.5.11:9092,10.128.5.13:9092");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("request.timeout.ms",6000);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        final Producer<String, String> producer = new KafkaProducer<>(props);
        long startTime = System.nanoTime();

                    for (int i = 0; i < 10; i++) {
                        producer.send(new ProducerRecord<>(topicName, Integer.toString(i), Integer.toString(i) + message), new Callback() {
                            @Override
                            public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                                if(e != null)   e.printStackTrace();
                                System.out.println("The offset of the record we just sent is: " + recordMetadata.offset());
                            }
                        });
                    }
        System.out.println("发送结束");
        System.out.println("花费时间" + (System.nanoTime() - startTime));
        producer.close();
//        executor.shutdown();
    }

    public static void main(String[] args) {
//        deleteTopic("10.128.5.14:2181", "tttest1");
//        createTopic("10.128.5.14:2181", "tttest1", 1, 2);
//        sendMessage("tttest1", "测试zookeeper topic节点");
        System.out.println(Integer.toBinaryString(-1));
        System.out.println(Integer.toBinaryString(Integer.MAX_VALUE).length());
    }
}
