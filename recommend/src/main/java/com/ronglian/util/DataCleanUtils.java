package com.ronglian.util;

import com.ronglian.core.DataTransFunction;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.function.Function;

/**
 * Created by spark on 18-5-2.
 */
public class DataCleanUtils {
    public static <F, T> JavaRDD<T> transform(JavaRDD<F> from, DataTransFunction<F, T> transFunction){
        return from.map((Function<F, T>) transFunction::transform);
    }
}
