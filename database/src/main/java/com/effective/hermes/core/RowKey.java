package com.effective.hermes.core;


/**
 * filename: RowKey
 * Description:
 * Author: ubuntu
 * Date: 1/23/18 11:33 AM
 */
public interface RowKey extends Comparable<RowKey>{
    byte[] getKey();
}
