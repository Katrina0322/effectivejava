package com.effective.hermes.core;


import com.effective.hermes.constant.Constant;
import com.effective.hermes.io.SSTableWirter;

import java.util.NavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * filename: Memtable
 * Description:
 * Author: ubuntu
 * Date: 1/22/18 2:46 PM
 */
public class Memtable implements HeapSize, Snapshot<Memtable> {
    private NavigableMap<Rowkey, IColumnFamily> memtable = new ConcurrentSkipListMap<>();

    private volatile boolean flush;
    private transient long heapSize;

    private SSTableWirter<Memtable> ssTableWirter;

    public Memtable(NavigableMap<Rowkey, IColumnFamily> memtable) {
        this.memtable = memtable;
    }

    public void add(Rowkey rowKey, IColumn column){
        if(flush) return;
        IColumnFamily old = memtable.get(rowKey);
        if(old == null) {
            IColumnFamily newFamily = new ColumnFamily();
            newFamily.add(new String(column.getColumnName()), column);
            memtable.put(rowKey, newFamily);
        }else {
            old.add(new String(column.getColumnName()), column);
        }
    }

    public long getHeapSize() {
        return heapSize;
    }

    public IColumnFamily getFamily(Rowkey rowKey){
        return memtable.get(rowKey);
    }

    public NavigableMap<Rowkey, IColumnFamily> getMemtable() {
        return memtable;
    }

    /**
     * if ready to flush to disk
     * @return
     */
    private boolean checkFlush() {
        return heapSize > 111;
    }

    private void flushToDisk(){
        Memtable snapshot = snapshot();
        flush = true;
        Constant.SERVICE.submit(new Runnable() {
            @Override
            public void run() {
                ssTableWirter.write(snapshot);
            }
        });
    }

    @Override
    public long heapSize() {
        return heapSize;
    }


    @Override
    public Memtable snapshot() {
        NavigableMap<Rowkey, IColumnFamily> readOnly = new ConcurrentSkipListMap<>(memtable);
        return new Memtable(readOnly);
    }

}
