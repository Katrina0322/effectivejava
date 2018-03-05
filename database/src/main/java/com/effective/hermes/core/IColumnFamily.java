package com.effective.hermes.core;

import java.util.Map;

/**
 * filename: IColumnFamily
 * Description:
 * Author: ubuntu
 * Date: 3/5/18 2:31 PM
 */
public interface IColumnFamily extends HeapSize{
    void add(String key, IColumn column);
    IColumn get(String key);
    Map<String, IColumn> getFamily();
}
