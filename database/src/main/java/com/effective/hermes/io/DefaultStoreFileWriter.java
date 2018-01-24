package com.effective.hermes.io;

import com.effective.hermes.cache.Memtable;
import com.effective.hermes.cache.StoreFile;

/**
 * filename: DefaultStoreFileWriter
 * Description:
 * Author: ubuntu
 * Date: 1/23/18 6:15 PM
 */
public class DefaultStoreFileWriter implements StoreFileWriter<Memtable> {

    @Override
    public StoreFile write(Memtable memtable) {
        return null;
    }
}
