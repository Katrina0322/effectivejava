package com.ronglian.util;

import com.ronglian.configuration.LogSetting;
import com.ronglian.core.DataTransFunction;
import org.apache.spark.api.java.JavaRDD;
import org.bson.Document;
import org.junit.Before;
import org.junit.Test;

import java.io.Serializable;

/**
 * Created by spark on 18-5-3.
 */
public class MongoUtilTest implements Serializable{

    private static final long serialVersionUID = 4691810860262442380L;

    @Before
    public void logSetting(){
        LogSetting.setWarningLogLevel("org");
        LogSetting.setWarningLogLevel("akka");
        LogSetting.setWarningLogLevel("io");
        LogSetting.setWarningLogLevel("httpclient.wire");
    }

    @Test
    public void findTest(){
        JavaRDD<String> find = MongoUtils.loadData("rcmd", "user_action", "{ $project: { userId: 1, innerid: 1, opType: 1, opDatetime: 1,  _id: 0 }}", new DataTransFunction<Document, String>() {
            private static final long serialVersionUID = 5127020361517114402L;
            @Override
            public String transform(Document from) {
                return from.toString();
            }
        });
        System.out.println(find.take(5) + "---" + find.count());

    }
}
