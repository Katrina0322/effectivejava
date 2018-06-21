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
import java.util.concurrent.Executors;
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

    public static void createTopic(String zooServer, String topicName, int partitions, int replicationFactor) {
        ZkUtils zkUtils = ZkUtils.apply(zooServer, 30000, 30000, JaasUtils.isZkSecurityEnabled());
        if (!AdminUtils.topicExists(zkUtils, topicName)) {
            AdminUtils.createTopic(zkUtils, topicName, partitions, replicationFactor, new Properties());
        }

        zkUtils.close();
    }

    public static void deleteTopic(String zooServer, String topicName) {
        ZkUtils zkUtils = ZkUtils.apply(zooServer, 30000, 30000, JaasUtils.isZkSecurityEnabled());
        if (AdminUtils.topicExists(zkUtils, topicName)) {
            AdminUtils.deleteTopic(zkUtils, topicName);
        }
        zkUtils.close();
    }

    public static int toPositive(int number) {
        return number & 2147483647;
    }

    public static void sendMessage(final String topicName, final String message) {
//        final CountDownLatch start = new CountDownLatch(1);
        Properties props = new Properties();
        props.put("bootstrap.servers", "10.50.1.197:19092;10.50.1.198:19092;10.50.1.202:19092");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("request.timeout.ms", 6000);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        final Producer<String, String> producer = new KafkaProducer<>(props);
        long startTime = System.nanoTime();

        for (int i = 0; i < 10000; i++) {
            producer.send(new ProducerRecord<>(topicName, Integer.toString(i),message), new Callback() {
                @Override
                public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                    if (e != null) e.printStackTrace();
                    System.out.println("The offset of the record we just sent is: " + recordMetadata.offset());
                }
            });
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("发送结束");
        System.out.println("花费时间" + (System.nanoTime() - startTime));
//        producer.close();
//        executor.shutdown();
    }

    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(10);
//        deleteTopic("10.128.5.14:2181", "tttest1");
//        createTopic("10.128.5.14:2181", "tttest1", 1, 2);
//        for (int i = 0; i < 1000000000; i++) {
            service.submit(new Runnable() {
                @Override
                public void run() {
//                    for (int i = 0; i < 100; i++) {
                    sendMessage("imageTest1", "{\"cache_server\": \"inews05.novalocal\", \"status\": 1, \"crawl_datetime\": \"2018-06-08 18:20:44\", \"images\": [{\"status\": 0, \"width\": 690, \"new_src\": \"${inewsImageServer}/default_image/2018/06/08/news.163.com/latest/DJPRO6SS00AN0001NOS.jpg\", \"size\": 142, \"image_form\": \"JPEG\", \"old_src\": \"http://pic-bucket.nosdn.127.net/photo/0001/2018-06-08/DJPRO6SS00AN0001NOS.jpg\", \"ranking_num\": 1, \"height\": 510}, {\"status\": 0, \"width\": 540, \"new_src\": \"${inewsImageServer}/default_image/2018/06/08/news.163.com/latest/DJPRQQRK00AN0001NOS.jpg\", \"size\": 29, \"image_form\": \"JPEG\", \"old_src\": \"http://pic-bucket.nosdn.127.net/photo/0001/2018-06-08/DJPRQQRK00AN0001NOS.jpg\", \"ranking_num\": 2, \"height\": 369}, {\"status\": 0, \"width\": 690, \"new_src\": \"${inewsImageServer}/default_image/2018/06/08/news.163.com/latest/DJPRO6SU00AN0001NOS.jpg\", \"size\": 146, \"image_form\": \"JPEG\", \"old_src\": \"http://pic-bucket.nosdn.127.net/photo/0001/2018-06-08/DJPRO6SU00AN0001NOS.jpg\", \"ranking_num\": 3, \"height\": 518}, {\"status\": 0, \"width\": 540, \"new_src\": \"${inewsImageServer}/default_image/2018/06/08/news.163.com/latest/DJPRQQRN00AN0001NOS.jpg\", \"size\": 34, \"image_form\": \"JPEG\", \"old_src\": \"http://pic-bucket.nosdn.127.net/photo/0001/2018-06-08/DJPRQQRN00AN0001NOS.jpg\", \"ranking_num\": 4, \"height\": 360}, {\"status\": 0, \"width\": 540, \"new_src\": \"${inewsImageServer}/default_image/2018/06/08/news.163.com/latest/DJPRQQRO00AN0001NOS.jpg\", \"size\": 25, \"image_form\": \"JPEG\", \"old_src\": \"http://pic-bucket.nosdn.127.net/photo/0001/2018-06-08/DJPRQQRO00AN0001NOS.jpg\", \"ranking_num\": 5, \"height\": 359}, {\"status\": 0, \"width\": 540, \"new_src\": \"${inewsImageServer}/default_image/2018/06/08/news.163.com/latest/DJPRQQRM00AN0001NOS.jpg\", \"size\": 44, \"image_form\": \"JPEG\", \"old_src\": \"http://pic-bucket.nosdn.127.net/photo/0001/2018-06-08/DJPRQQRM00AN0001NOS.jpg\", \"ranking_num\": 6, \"height\": 360}, {\"status\": 0, \"width\": 540, \"new_src\": \"${inewsImageServer}/default_image/2018/06/08/news.163.com/latest/DJPRQQRL00AN0001NOS.jpg\", \"size\": 42, \"image_form\": \"JPEG\", \"old_src\": \"http://pic-bucket.nosdn.127.net/photo/0001/2018-06-08/DJPRQQRL00AN0001NOS.jpg\", \"ranking_num\": 7, \"height\": 444}, {\"status\": 0, \"width\": 540, \"new_src\": \"${inewsImageServer}/default_image/2018/06/08/news.163.com/latest/DJPRQQRP00AN0001NOS.jpg\", \"size\": 28, \"image_form\": \"JPEG\", \"old_src\": \"http://pic-bucket.nosdn.127.net/photo/0001/2018-06-08/DJPRQQRP00AN0001NOS.jpg\", \"ranking_num\": 8, \"height\": 360}], \"div_id\": \"uec_img_group_0\", \"webpage_code\": \"14b69723dd398745ed7eb8be58ff68b8\"}");
//                    }
                }
            });
//        }
//        sendMessage("tttest1", "测试zookeeper topic节点");
//        System.out.println(Integer.toBinaryString(-1));
//        System.out.println(Integer.toBinaryString(Integer.MAX_VALUE).length());
    }
}
