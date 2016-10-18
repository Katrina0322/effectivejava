package com.oneapm.producer;


import com.oneapm.performance.Config;
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
public class KafkaDataProducer {
    private static Logger log = Logger.getLogger(KafkaDataProducer.class);

    private static Producer<String, String> producer;

    public KafkaDataProducer() {
        Properties props = new Properties();
        props.put("metadata.broker.list", Config.getConfig("database.cnf").getProperty("kafaka.zoo"));
        props.put("serializer.class", "kafka.serializer.StringEncoder");
        props.put("key.serializer.class", "kafka.serializer.StringEncoder");
        props.put("request.required.acks", "-1");
        producer = new KafkaProducer<>(props);
    }

    private void produce() throws Exception {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(Config.getConfig("database.cnf").getProperty("datafile")));
            String line;


            while ((line = br.readLine()) != null) {
                if (!"".equals(line.trim())) {
                    producer.send(new ProducerRecord<>("test", "", line));
                }
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

    public static void main(String[] args) throws Exception {
        new KafkaDataProducer().produce();
    }
}
