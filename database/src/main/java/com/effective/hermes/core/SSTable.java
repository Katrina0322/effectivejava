package com.effective.hermes.core;


import com.effective.hermes.io.SSTableReader;

/**
 * description:
 * Created by spark on 18-2-2.
 */
public class SSTable {
    private IndexBlock indexBlock;
    private SSTableReader<IColumnFamily> tableReader;
    private volatile boolean isWritable;

    private String getTableName(){
        return null;
    }
    public IColumnFamily getFamily(Rowkey rowkey){
        return null;
    }
}
