package org.microframe.rpc.producer;


import org.microframe.rpc.performance.Config;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * used to
 * Created by tianjin on 10/18/16.
 */
public class KafkaDataProducer implements Runnable {
    private static Logger log = Logger.getLogger(KafkaDataProducer.class);

    private static Producer<String, String> producer;

    private String topic;

    private String path;

    public KafkaDataProducer() {
        Properties props = new Properties();
        props.put("bootstrap.servers", Config.getConfig("database.cnf").getProperty("bootstrap.server"));
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        producer = new KafkaProducer<>(props);
    }

    public KafkaDataProducer(String topic, String path) {
        this.path = path;
        this.topic = topic;

        Properties props = new Properties();
        props.put("bootstrap.servers", Config.getConfig("database.cnf").getProperty("bootstrap.server"));
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        producer = new KafkaProducer<>(props);
    }


    public static void main(String[] args) throws Exception {
        KafkaDataProducer kafkaDataProducer1 = new KafkaDataProducer("test", "datafile");
        new Thread(kafkaDataProducer1).start();

//        KafkaDataProducer kafkaDataProducer2 = new KafkaDataProducer("tcptest","tcp.file");
//        new Thread(kafkaDataProducer2).start();
//
//        KafkaDataProducer kafkaDataProducer3 = new KafkaDataProducer("httptest","http.file");
//        new Thread(kafkaDataProducer3).start();

    }

    @Override
    public void run() {
        BufferedReader br = null;
        try {
            while (true) {
                br = new BufferedReader(new FileReader(Config.getConfig("database.cnf").getProperty(path)));
                String line;


                while ((line = br.readLine()) != null) {
                    if (!"".equals(line.trim())) {
                        producer.send(new ProducerRecord<>(topic, "", line));
                    }
                }
                Thread.sleep(Long.valueOf(Config.getConfig("database.cnf").getProperty("sleep.time")));
            }
        } catch (Exception e) {
            log.error("The read streaming error: ", e);
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    log.warn("close the read streaming error: ", e);
                }
            }
        }
    }
}
