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
    private ConcurrentSkipListMap<Rowkey, IndexTuple> indexRange;

    public IndexBlock() {
        indexRange = new ConcurrentSkipListMap<>();
    }

    public void addIndex(Rowkey key, long offset, long length){
        indexRange.put(key, new IndexTuple(offset, length));
    }

    public IndexTuple getIndexTuple(Rowkey key){
        return indexRange.floorEntry(key).getValue();
    }

    public boolean checkExist(Rowkey key){
        Rowkey low = indexRange.firstKey();
        Rowkey high = indexRange.lastKey();
        return key.compareTo(low) >= 0 && key.compareTo(high) <= 0;
    }

}
