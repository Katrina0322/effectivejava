package com.study.es.contentBase;

import com.ronglian.beans.ItemFeature;
import com.ronglian.core.DataPrepare;
import com.ronglian.core.DataTransFunction;
import com.ronglian.core.LifeCycle;
import com.ronglian.util.MongoUtils;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.bson.Document;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by spark on 18-5-2.
 */
public class ItemFeatureData implements DataPrepare<ItemFeature> {
    private Set<String> codeList;

    public ItemFeatureData(Set<String> codeList) {
        this.codeList = codeList;
    }

    @Override
    public JavaRDD<ItemFeature> prepareData() {
        String in = codeList.toString();
        String query = "{$match: {\"ainfo.aid\": { $in: " + in + "}}}, {$project: {_id: 0}}";
        return MongoUtils.loadData("rcmd","nlp_art", query , new DataTransFunction<Document, List<ItemFeature>>() {
            @Override
            public List<ItemFeature> transform(Document from) {
                return null;
            }
        }).flatMap(new FlatMapFunction<List<ItemFeature>, ItemFeature>() {
            @Override
            public Iterator<ItemFeature> call(List<ItemFeature> itemFeatures) throws Exception {
                return itemFeatures.iterator();
            }
        });

    }

}
