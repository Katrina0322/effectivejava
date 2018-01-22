package com.effective.hermes.cache;

import java.util.NavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * filename: Memtable
 * Description:
 * Author: ubuntu
 * Date: 1/22/18 2:46 PM
 */
public class Memtable implements HeapSize{
    private NavigableMap<String, ColumnFamily> memtable = new ConcurrentSkipListMap<>();
    private transient long heapSize;

    @Override
    public long heapSize() {
        return heapSize;
    }


}
