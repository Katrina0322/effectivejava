package com.effective.hermes.cache;

/**
 * filename: AbstractMemStore
 * Description:
 * Author: ubuntu
 * Date: 1/8/18 4:05 PM
 */
public abstract class AbstractMemStore implements MemStore {
    private volatile MutableSegment active;
    private volatile long timeOfOldestEdit;

    @Override
    public void add(Cell cell, MemStoreSizing memstoreSizing) {
        boolean succ = active.add(cell, memstoreSizing);
    }

    @Override
    public void add(Iterable<Cell> cells, MemStoreSizing memstoreSizing) {
        for(Cell cell:cells){
            add(cell, memstoreSizing);
        }
    }

    @Override
    public long timeOfOldestEdit() {
        return timeOfOldestEdit;
    }

    @Override
    public void upsert(Iterable<Cell> cells, MemStoreSizing memstoreSizing) {
        for(Cell cell:cells){
            upsert(cell, memstoreSizing);
        }
    }

    private void upsert(Cell cell, MemStoreSizing memStoreSizing){
        active.upsert(cell, memStoreSizing);
    }

    protected abstract void checkActiveSize();
}
