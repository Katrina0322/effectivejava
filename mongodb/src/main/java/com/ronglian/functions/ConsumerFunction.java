package com.ronglian.functions;

import org.apache.kafka.clients.consumer.ConsumerRecord;

import java.io.IOException;
import java.util.List;

/**
 * Created by spark on 4/20/18.
 */
public interface ConsumerFunction<K, V> {
    void batchConsumer(List<ConsumerRecord<K, V>> recordList) throws IOException;
}
