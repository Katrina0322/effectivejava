package com.effective.hermes.cache;

/**
 * filename: MemStoreSize
 * Description:
 * Author: ubuntu
 * Date: 1/5/18 6:12 PM
 */
@Deprecated
public class MemStoreSize implements HeapSize{
    private volatile long heapSize;

    public MemStoreSize() {
        this(0L);
    }
    public MemStoreSize(long heapSize) {
        this.heapSize = heapSize;
    }
    public boolean isEmpty() {
        return this.heapSize == 0;
    }

    public void incMemStoreSize(long heapSizeDelta) {
        this.heapSize += heapSizeDelta;
    }

    public void incMemStoreSize(MemStoreSize delta) {
        incMemStoreSize( delta.heapSize());
    }

    public void decMemStoreSize(long heapSizeDelta) {
        this.heapSize -= heapSizeDelta;
    }

    public void decMemStoreSize(MemStoreSize delta) {
        decMemStoreSize(delta.heapSize());
    }

    @Override
    public long heapSize() {
        return this.heapSize;
    }
}
