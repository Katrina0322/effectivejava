package com.effective.hermes.cache;

/**
 * filename: StoreFileWriter
 * Description:
 * Author: ubuntu
 * Date: 1/23/18 9:35 AM
 */
public interface StoreFileWriter<T> {
    StoreFile write(T t);
}
