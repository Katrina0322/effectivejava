package com.effective.hermes.util;

import com.effective.hermes.cache.Cell;

/**
 * filename: CellUtils
 * Description:
 * Author: ubuntu
 * Date: 1/10/18 4:29 PM
 */
public class CellUtils {
    private static final int KEY_INFRASTRUCTURE_SIZE = Short.SIZE + Byte.SIZE * 2 + Long.SIZE;

    public static int keyLength(final Cell cell) {
        return KEY_INFRASTRUCTURE_SIZE + cell.getRowLength() + cell.getFamilyLength() + cell.getQualifierLength();
    }

    public static int cellLength(final Cell cell) {
        return keyLength(cell) + cell.getValueLength();
    }
}
