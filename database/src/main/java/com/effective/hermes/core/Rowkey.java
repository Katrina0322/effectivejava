package com.effective.hermes.core;


/**
 * filename: Rowkey
 * Description:
 * Author: ubuntu
 * Date: 1/23/18 11:33 AM
 */
public interface Rowkey extends Comparable<Rowkey>{
    byte[] getKey();
}
