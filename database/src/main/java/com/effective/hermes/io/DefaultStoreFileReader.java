package com.effective.hermes.io;

import com.effective.hermes.core.IndexBlock;
import com.effective.hermes.core.StoreFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * filename: DefaultStoreFileReader
 * Description:
 * Author: ubuntu
 * Date: 1/24/18 4:39 PM
 */
public class DefaultStoreFileReader {
    private StoreFile storeFile;
    private FileChannel fileChannel;
    private FileInputStream fileInputStream;

    public DefaultStoreFileReader(StoreFile storeFile) throws IOException {
        this.storeFile = storeFile;
        String location = storeFile.getLocation();
        File file = new File(location);
        FileInputStream fileInputStream = new FileInputStream(file);
        fileChannel = fileInputStream.getChannel();
//        ByteBuffer buffer = FileUtils.read(fileChannel, 0, 30);
//        IndexBlock indexBlock = bufferTransfer(buffer);
//        this.storeFile.setIndexBlock(indexBlock);
    }

    private IndexBlock bufferTransfer(ByteBuffer byteBuffer){
        return new IndexBlock();
    }


}
