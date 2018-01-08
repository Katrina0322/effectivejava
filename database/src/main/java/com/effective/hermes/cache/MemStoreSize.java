package com.effective.hermes.cache;

/**
 * filename: MemStoreSize
 * Description:
 * Author: ubuntu
 * Date: 1/5/18 6:12 PM
 */
public class MemStoreSize {
    protected long dataSize;
    protected long heapSize;
    public MemStoreSize() {
        this(0L, 0L);
    }
    public MemStoreSize(long dataSize, long heapSize) {
        this.dataSize = dataSize;
        this.heapSize = heapSize;
    }
    public boolean isEmpty() {
        return this.dataSize == 0 && this.heapSize == 0;
    }

    public long getDataSize() {
        return this.dataSize;
    }

    public long getHeapSize() {
        return this.heapSize;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        MemStoreSize other = (MemStoreSize) obj;
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
