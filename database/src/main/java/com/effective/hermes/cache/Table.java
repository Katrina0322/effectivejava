package com.effective.hermes.cache;

import java.util.NavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * filename: Table
 * Description:
 * Author: ubuntu
 * Date: 1/22/18 11:24 AM
 */
public class Table {
    private String tableName;
    private NavigableMap<String, ColumnFamily> family = new ConcurrentSkipListMap<>();
}
