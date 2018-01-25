package com.effective.hermes.core;


/**
 * filename: StoreFile
 * Description:
 * Author: ubuntu
 * Date: 1/10/18 10:31 AM
 */
public interface StoreFile {
    String getLocation();
    IndexBlock getIndexBlock();
    void setIndexBlock(IndexBlock indexBlock);
}
