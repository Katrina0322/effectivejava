package com.effective.hermes.cache;

import com.effective.hermes.util.CellUtils;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * filename: HFileWirter
 * Description:
 * Author: ubuntu
 * Date: 1/9/18 2:28 PM
 */
public class HFileWirter implements Closeable{
    private FileChannel channel;
    private FileOutputStream outputStream;

    public HFileWirter() throws FileNotFoundException {
        String path = createPath();
        outputStream = new FileOutputStream(path, true);
        channel = outputStream.getChannel();
    }

    public HFileWirter(FileOutputStream outputStream) {
        this.outputStream = outputStream;
        channel = outputStream.getChannel();
    }

    public void append(final Cell cell) throws IOException {
        int cellLength = CellUtils.cellLength(cell);
        int keyLength = CellUtils.keyLength(cell);
        ByteBuffer buffer = ByteBuffer.allocate(cellLength + 2 * Integer.BYTES);
        buffer.putInt(keyLength);
        buffer.putInt(cell.getValueLength());
        buffer.put(cell.getRowArray());
        buffer.put(cell.getFamilyArray());
        buffer.put(cell.getQualifierArray());
        buffer.putLong(cell.getTimestamp());
        buffer.put(cell.getValueArray());
        buffer.flip();
        channel.write(buffer);
        buffer.clear();
    }

    private String createPath(){
        String path = "";
        return path;
    }


    @Override
    public void close() throws IOException {
        outputStream.flush();
        if(channel.isOpen()){
            channel.close();
        }
        outputStream.close();
        channel = null;
        outputStream = null;
    }
}
