package com.effective.hermes.io;

/**
 * filename: SSTableReader
 * Description:
 * Author: ubuntu
 * Date: 1/24/18 4:37 PM
 */
public interface SSTableReader<T> {
    T read(long start, long size);
}
