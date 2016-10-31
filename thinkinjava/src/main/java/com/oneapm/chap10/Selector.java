package com.oneapm.chap10;

/**
 * used to
 * Created by tianjin on 8/1/16.
 */
public interface Selector {
    boolean end();

    Object current();

    void next();
}
