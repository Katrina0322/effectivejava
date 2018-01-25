package com.effective.hermes.core;


import com.effective.hermes.common.IndexTuple;

import java.util.concurrent.ConcurrentSkipListMap;

/**
 * filename: IndexBlock
 * Description:
 * Author: ubuntu
 * Date: 1/23/18 3:46 PM
 */
public class IndexBlock {
    private ConcurrentSkipListMap<RowKey, IndexTuple> indexRange;

    public IndexBlock() {
        indexRange = new ConcurrentSkipListMap<>();
    }

    public void addIndex(RowKey key, long offset,  long length){
        indexRange.put(key, new IndexTuple(offset, length));
    }

    public IndexTuple getIndexTuple(RowKey key){
        return indexRange.floorEntry(key).getValue();
    }

    public boolean checkExist(RowKey key){
        RowKey low = indexRange.firstKey();
        RowKey high = indexRange.lastKey();
        return key.compareTo(low) >= 0 && key.compareTo(high) <= 0;
    }

}
