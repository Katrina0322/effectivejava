package com.ronglian.userBaseCF;

import com.ronglian.beans.UserPrefer;
import com.ronglian.core.DataPrepare;
import com.ronglian.core.DataTransFunction;
import com.ronglian.core.OpType;
import com.ronglian.util.MongoUtils;
import org.apache.spark.api.java.JavaRDD;
import org.bson.Document;

/**
 * Created by spark on 18-5-2.
 */
public class UserItemDataPrepare implements DataPrepare<UserPrefer> {

    @Override
    public JavaRDD<UserPrefer> prepareData() {

        return MongoUtils.loadData("rcmd", "user_action", "{$match: {code: { $exists: true }}},{$project: { userId: 1, code: 1, opType: 1, opDatetime: 1,  _id: 0 }}", (DataTransFunction<Document, UserPrefer>) from -> {
            UserPrefer userPrefer = new UserPrefer();
            userPrefer.setUserId(from.getString("userId"));
            userPrefer.setItemId(from.getString("code"));
            userPrefer.setOpTime(from.getDate("opDatetime"));
            userPrefer.setRating(OpType.valueOf(from.getString("opType").toUpperCase()).getRating());
            return userPrefer;
        });
    }

}
