package com.effective.hermes.cache;

/**
 * filename: Cell
 * Description:
 * Author: ubuntu
 * Date: 1/8/18 10:07 AM
 */
public interface Cell {
    byte[] getRowArray();

    long getTimestamp();

    byte[] getValueArray();

    Type getType();
    enum Type {
        Put((byte) 4),

        Delete((byte) 8),

        DeleteFamilyVersion((byte) 10),

        DeleteColumn((byte) 12);

        private final byte code;

        Type(final byte c) {
            this.code = c;
        }

        public byte getCode() {
            return this.code;
        }
    }
}
