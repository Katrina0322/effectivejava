package com.ronglian.contentBase;

import com.ronglian.beans.ItemFeature;
import com.ronglian.core.DataPrepare;
import com.ronglian.core.DataTransFunction;
import com.ronglian.util.MongoUtils;
import org.apache.spark.api.java.JavaRDD;
import org.bson.Document;

/**
 * Created by spark on 18-5-2.
 */
public class ItemFeatureData implements DataPrepare<ItemFeature> {
    @Override
    public JavaRDD<ItemFeature> prepareData() {
        return MongoUtils.loadData("rcmd","user_action", "{}, { userId: 1, innerid: 1, _id: 0 }", new DataTransFunction<Document, ItemFeature>() {
            @Override
            public ItemFeature transform(Document from) {
                return null;
            }
        });
    }
}
