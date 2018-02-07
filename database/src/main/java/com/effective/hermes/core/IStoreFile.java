package com.effective.hermes.core;


/**
 * filename: IStoreFile
 * Description:
 * Author: ubuntu
 * Date: 1/10/18 10:31 AM
 */
public interface IStoreFile {
    String getLocation();
    void setIndexBlock(IndexBlock indexBlock);
    IndexBlock getIndexBlock();
}
