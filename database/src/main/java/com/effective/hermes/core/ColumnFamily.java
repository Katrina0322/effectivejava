package com.effective.hermes.core;

import java.util.NavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * filename: ColumnFamily
 * Description:
 * Author: ubuntu
 * Date: 1/11/18 2:15 PM
 */
public class ColumnFamily implements HeapSize{
    private NavigableMap<String, IColumn> family = new ConcurrentSkipListMap<>();

    private transient volatile long heapSize;

    public NavigableMap<String, IColumn> getFamily() {
        return family;
    }

    public void add(String key, IColumn column){
        IColumn old = family.put(key, column);
        if(old == null) heapSize += column.heapSize();
    }

    public IColumn get(String key){
        return family.get(key);
    }


    @Override
    public long heapSize() {
        return heapSize;
    }
}
