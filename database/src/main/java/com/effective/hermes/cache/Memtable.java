package com.effective.hermes.cache;

import com.effective.hermes.io.StoreFileWriter;

import java.util.NavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * filename: Memtable
 * Description:
 * Author: ubuntu
 * Date: 1/22/18 2:46 PM
 */
public class Memtable implements HeapSize, Snapshot<Memtable> {
    private NavigableMap<RowKey, ColumnFamily> memtable = new ConcurrentSkipListMap<>();
    private StoreFileWriter<Memtable> storeFileWriter;
    private transient long heapSize;

    public void add(RowKey rowKey, Column column){
        ColumnFamily old = memtable.get(rowKey);
        if(old == null){
            ColumnFamily newFamily = new ColumnFamily();
            newFamily.add(new String(column.getColumName()), column);
            memtable.put(rowKey, newFamily);
            heapSize += newFamily.heapSize();
        }else {
            old.add(new String(column.getColumName()), column);
        }
    }

    public ColumnFamily getFamily(RowKey rowKey){
        return memtable.get(rowKey);
    }

    public NavigableMap<RowKey, ColumnFamily> getMemtable() {
        return memtable;
    }

    private boolean checkFlush() {
        return heapSize > 111;
    }

    private StoreFile flushToDisk(){
        Memtable snapshot = snapshot();
        return storeFileWriter.write(snapshot);
    }

    @Override
    public long heapSize() {
        return heapSize;
    }


    @Override
    public Memtable snapshot() {
        return null;
    }
}
