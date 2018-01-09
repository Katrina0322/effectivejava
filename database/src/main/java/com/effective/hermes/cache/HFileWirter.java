package com.effective.hermes.cache;

import java.io.IOException;

/**
 * filename: HFileWirter
 * Description:
 * Author: ubuntu
 * Date: 1/9/18 2:28 PM
 */
public class HFileWirter {
    public void append(final Cell cell) throws IOException {
        byte[] value = cell.getValueArray();
        int offset = cell.getValueOffset();
        int length = cell.getValueLength();
    }
}
