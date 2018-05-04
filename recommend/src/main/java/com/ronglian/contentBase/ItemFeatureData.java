package com.ronglian.contentBase;

import com.ronglian.beans.ItemFeature;
import com.ronglian.beans.UserPrefer;
import com.ronglian.core.DataPrepare;
import com.ronglian.core.DataTransFunction;
import com.ronglian.util.MongoUtils;
import org.apache.spark.api.java.JavaRDD;
import org.bson.Document;

import java.util.Arrays;
import java.util.List;

/**
 * Created by spark on 18-5-2.
 */
public class ItemFeatureData implements DataPrepare<ItemFeature> {
    private List<String> codeList;

    public ItemFeatureData(List<String> codeList) {
        this.codeList = codeList;
    }

    @Override
    public JavaRDD<ItemFeature> prepareData() {
        String in = codeList.toString();
        String query = "{$match: {\"ainfo.aid\": { $in: " + in + "}}}, {$project: {_id: 0}}";
        return MongoUtils.loadData("rcmd","nlp_art", query , new DataTransFunction<Document, ItemFeature>() {
            @Override
            public ItemFeature transform(Document from) {
                return null;
            }
        });

    }

    public static void main(String[] args) {
        List<String> a = Arrays.asList("aaa", "bbb", "ccc");
        System.out.print(a.toString());
    }
}
