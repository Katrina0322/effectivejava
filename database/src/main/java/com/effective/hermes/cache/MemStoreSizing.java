package com.effective.hermes.cache;

/**
 * filename: MemStoreSizing
 * Description:
 * Author: ubuntu
 * Date: 1/5/18 6:14 PM
 */
public class MemStoreSizing extends MemStoreSize {
    public MemStoreSizing() {
        super();
    }

    public MemStoreSizing(long dataSize, long heapSize) {
        super(dataSize, heapSize);
    }

    public void incMemStoreSize(long dataSizeDelta, long heapSizeDelta) {
        this.dataSize += dataSizeDelta;
        this.heapSize += heapSizeDelta;
    }

    public void incMemStoreSize(MemStoreSize delta) {
        incMemStoreSize(delta.getDataSize(), delta.getHeapSize());
    }

    public void decMemStoreSize(long dataSizeDelta, long heapSizeDelta) {
        this.dataSize -= dataSizeDelta;
        this.heapSize -= heapSizeDelta;
    }

    public void decMemStoreSize(MemStoreSize delta) {
        decMemStoreSize(delta.getDataSize(), delta.getHeapSize());
    }

    public void empty() {
        this.dataSize = 0L;
        this.heapSize = 0L;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || (getClass() != obj.getClass())) {
            return false;
        }
        MemStoreSizing other = (MemStoreSizing) obj;
        return this.dataSize == other.dataSize && this.heapSize == other.heapSize;
    }

    @Override
    public int hashCode() {
        long h = 13 * this.dataSize;
        h = h + 14 * this.heapSize;
        return (int) h;
    }

    @Override
    public String toString() {
        return "dataSize=" + this.dataSize + " , heapSize=" + this.heapSize;
    }
}
