package com.effective.hermes.io;

import com.effective.hermes.core.IStoreFile;

/**
 * filename: StoreFileWriter
 * Description:
 * Author: ubuntu
 * Date: 1/23/18 9:35 AM
 */
public interface StoreFileWriter<T> {
    IStoreFile write(T t);
}
