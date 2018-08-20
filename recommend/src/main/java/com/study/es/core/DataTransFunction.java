package com.study.es.core;

import java.io.Serializable;

/**
 * Created by spark on 18-5-2.
 */
public interface DataTransFunction<F, T> extends Serializable {
    T transform(F from);
}
