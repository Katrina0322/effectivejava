package com.effective.hermes.io;

import com.effective.hermes.core.IColumnFamily;


/**
 * filename: DefaultSSTableReader
 * Description:
 * Author: ubuntu
 * Date: 1/24/18 4:39 PM
 */
public class DefaultSSTableReader implements SSTableReader<IColumnFamily> {

    private IFileReader iFileReader;

    public DefaultSSTableReader(IFileReader iFileReader) {
        this.iFileReader = iFileReader;
    }

    @Override
    public IColumnFamily read(long start, long size) {
        return null;
    }
}
