package com.effective.hermes.core;

/**
 * filename: DefaultStoreFile
 * Description:
 * Author: ubuntu
 * Date: 1/23/18 3:47 PM
 */
public class DefaultStoreFile implements StoreFile {
    private String location;
    private IndexBlock indexBlock;

    public DefaultStoreFile(String location, IndexBlock indexBlock) {
        this.location = location;
        this.indexBlock = indexBlock;
    }

    public DefaultStoreFile(String location) {
        this.location = location;
    }

    @Override
    public String getLocation() {
        return location;
    }

    @Override
    public IndexBlock getIndexBlock() {
        return indexBlock;
    }

    @Override
    public void setIndexBlock(IndexBlock indexBlock) {
        this.indexBlock = indexBlock;
    }
}
