package com.effective.hermes.common;

/**
 * filename: IndexTuple
 * Description:
 * Author: ubuntu
 * Date: 1/25/18 3:52 PM
 */
public class IndexTuple {
    private long offset;
    private long length;

    public IndexTuple(long offset, long length) {
        this.offset = offset;
        this.length = length;
    }

    public long getLength() {
        return length;
    }

    public long getOffset() {
        return offset;
    }
}
