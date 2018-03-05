package com.effective.hermes.io;

import com.effective.hermes.constant.Constant;
import com.effective.hermes.core.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Map;
import java.util.NavigableMap;

/**
 * filename: DefaultSSTableWriter
 * Description:
 * Author: ubuntu
 * Date: 1/23/18 6:15 PM
 */
public class DefaultSSTableWriter implements SSTableWirter<Memtable>, AutoCloseable {
    private IFileWriter iFileWriter;

    public DefaultSSTableWriter(IFileWriter iFileWriter) {
        this.iFileWriter = iFileWriter;
    }

    @Override
    public void write(Memtable memtable) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(Constant.TRUNK_SIZE);
        byteBuffer.clear();
        NavigableMap<Rowkey, IColumnFamily> columns = memtable.getMemtable();
        IndexBlock indexBlock = new IndexBlock();
        long blockLength = 0;
        int keySize = 0;
        for(Map.Entry<Rowkey, IColumnFamily> columnFamilyEntry:columns.entrySet()){
            Rowkey rowKey = columnFamilyEntry.getKey();
            IColumnFamily columnFamily = columnFamilyEntry.getValue();
            Map<String, IColumn> columnNavigableMap = columnFamily.getFamily();
            for(Map.Entry<String, IColumn> columnEntry:columnNavigableMap.entrySet()){
                byte[] name = columnEntry.getKey().getBytes();
                byte[] value = columnEntry.getValue().getColumnValue();
                long timeStamp = columnEntry.getValue().getTimestamp();
                byteBuffer.putInt(name.length);
                byteBuffer.putInt(value.length);
                byteBuffer.put(name);
                byteBuffer.put(value);
                byteBuffer.putLong(timeStamp);
            }
            keySize++;
        }
    }

    private boolean checkEnough(ByteBuffer byteBuffer, long size){
        if(byteBuffer.remaining() > size) return true;
        return false;
    }


    @Override
    public void close() throws Exception {

    }
}
