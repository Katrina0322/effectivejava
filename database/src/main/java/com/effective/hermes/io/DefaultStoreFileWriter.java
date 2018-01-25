package com.effective.hermes.io;

import com.effective.hermes.common.IndexTuple;
import com.effective.hermes.constant.Constant;
import com.effective.hermes.core.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NavigableMap;

/**
 * filename: DefaultStoreFileWriter
 * Description:
 * Author: ubuntu
 * Date: 1/23/18 6:15 PM
 */
public class DefaultStoreFileWriter implements StoreFileWriter<Memtable>, AutoCloseable {
    private StoreFile storeFile;
    private FileOutputStream fileOutputStream;
    private FileChannel fileChannel;

    public DefaultStoreFileWriter(StoreFile storeFile) {
        this.storeFile = storeFile;
    }

    @Override
    public StoreFile write(Memtable memtable) {

        ByteBuffer byteBuffer = ByteBuffer.allocate(Constant.TRUNK_SIZE);
        byteBuffer.clear();
        NavigableMap<RowKey, ColumnFamily> columns = memtable.getMemtable();
        IndexBlock indexBlock = new IndexBlock();
        for(Map.Entry<RowKey, ColumnFamily> columnFamilyEntry:columns.entrySet()){
            RowKey rowKey = columnFamilyEntry.getKey();
            ColumnFamily columnFamily = columnFamilyEntry.getValue();

        }
        return null;
    }

    private boolean checkEnough(ByteBuffer byteBuffer, long size){
        if(byteBuffer.remaining() > size) return true;
        return false;
    }

    private void init() throws FileNotFoundException {
        File file = new File(storeFile.getLocation());
        fileOutputStream = new FileOutputStream(file);
        fileChannel = fileOutputStream.getChannel();
    }

    @Override
    public void close() throws Exception {

    }
}
