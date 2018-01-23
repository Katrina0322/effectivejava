package com.effective.hermes.cache;

import java.util.NavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * filename: ColumnFamily
 * Description:
 * Author: ubuntu
 * Date: 1/11/18 2:15 PM
 */
public class ColumnFamily implements HeapSize{
    private NavigableMap<String, Column> family = new ConcurrentSkipListMap<>();

    private transient volatile long heapSize;


    public void add(String key, Column column){
        Column old = family.put(key, column);
        if(old == null) heapSize += column.heapSize();
    }

    public Column get(String key){
        return family.get(key);
    }


    @Override
    public long heapSize() {
        return heapSize;
    }
}
