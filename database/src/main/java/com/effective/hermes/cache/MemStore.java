package com.effective.hermes.cache;

/**
 * filename: MemStore
 * Description:
 * Author: ubuntu
 * Date: 1/5/18 6:11 PM
 */
public interface MemStore {
    /**
     * flush时,清除的内存
     * @return
     */
    MemStoreSize getFlushableSize();

    /**
     * 写入或更新cell到内存
     * @param cell
     * @param memstoreSizing
     */
    void add(final Cell cell, MemStoreSizing memstoreSizing);
    void add(Iterable<Cell> cells, MemStoreSizing memstoreSizing);

    long timeOfOldestEdit();

    /**
     * 更新或者插入
     * @param cells
     * @param memstoreSizing
     */
    void upsert(Iterable<Cell> cells,  MemStoreSizing memstoreSizing);

}
