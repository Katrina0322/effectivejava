package com.ronglian.functions;

import com.ronglian.config.Constant;
import com.ronglian.mongodb.MongoUtil;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.bson.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by spark on 4/20/18.
 */
public class MongodbInsertFunction<K, V> implements ConsumerFunction<K, V> {
    private MongoUtil mongoUtil;

    public MongodbInsertFunction() {
        this.mongoUtil = MongoUtil.getInstance();
    }


    @Override
    public void batchConsumer(List<ConsumerRecord<K, V>> recordList) throws IOException {
        List<Document> documents = new ArrayList<>(recordList.size());
        for(ConsumerRecord<K, V> record : recordList){
            Document document = Document.parse(record.value().toString());
            documents.add(document);
        }
        mongoUtil.insert(Constant.DATABASE, Constant.COLLECTION, documents);
    }

}
