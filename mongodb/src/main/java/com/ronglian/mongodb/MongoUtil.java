package com.ronglian.mongodb;

import com.google.common.base.Preconditions;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.ronglian.config.Constant;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by spark on 4/19/18.
 */
public class MongoUtil {
    private static final Logger logger = LoggerFactory.getLogger(MongoUtil.class);

    private static volatile MongoClient mongoClient;
    private static MongoUtil instance = new MongoUtil();

    private MongoUtil(){
        List<ServerAddress> addrs = new ArrayList<>();
        String[] servers = Constant.COMMA.split(Constant.MONGO_SERVER);

        for(String server:servers){
            String[] ss = Constant.COLON.split(server);
            Preconditions.checkArgument(ss.length == 2);
            ServerAddress serverAddress = new ServerAddress(ss[0],Integer.parseInt(ss[1]));
            addrs.add(serverAddress);
        }

//        List<MongoCredential> credentials = new ArrayList<>();
//        MongoCredential credential = MongoCredential.createScramSha1Credential(Constant.USER, Constant.DATABASE, Constant.PASSWORD.toCharArray());
//
//        credentials.add(credential);
//        mongoClient = new MongoClient(addrs, credentials);
        mongoClient = new MongoClient(addrs);
        logger.info("Connect to database successfully!");
    }

    public static MongoUtil getInstance(){
        return instance;
    }

    public void destory() {
        if (mongoClient != null)
            mongoClient.close();
    }

    public void insert(String database, String collection, List<Document> documents){
        MongoDatabase mgdb = mongoClient.getDatabase(database);
        MongoCollection<Document> dbCollection = mgdb.getCollection(collection);
        dbCollection.insertMany(documents);
        logger.info("write data " + dbCollection.count());
    }

}
