package com.effective.hermes.cache;

import java.util.NavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * filename: MutableSegment
 * Description:
 * Author: ubuntu
 * Date: 1/8/18 4:34 PM
 */
public class MutableSegment {
    private NavigableMap<Cell, Cell> delegatee;

    public MutableSegment() {
        this.delegatee = new ConcurrentSkipListMap<>();
    }

    public MutableSegment(NavigableMap<Cell, Cell> delegatee) {
        this.delegatee = delegatee;
    }

    public boolean add(Cell cell, MemStoreSizing memstoreSizing){
        return delegatee.put(cell, cell) == null;
    }
    public void upsert(Cell cell, MemStoreSizing memStoreSizing){
        delegatee.put(cell, cell);
    }
}
