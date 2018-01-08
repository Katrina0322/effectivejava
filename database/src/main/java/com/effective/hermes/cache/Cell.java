package com.effective.hermes.cache;

/**
 * filename: Cell
 * Description: 大量的数据冗余,每一个cell都重复存储了row,family,timestaml
 * Author: ubuntu
 * Date: 1/8/18 10:07 AM
 */
public interface Cell {
    byte[] getRowArray();
    int getRowOffset();
    short getRowLength();

    byte[] getFamilyArray();
    int getFamilyOffset();
    byte getFamilyLength();

    byte[] getQualifierArray();
    int getQualifierLength();
    int getQualifierOffset();

    long getTimestamp();

    byte[] getValueArray();
    int getValueOffset();
    int getValueLength();


    Type getType();
    enum Type {
        Put((byte) 4),

        Delete((byte) 8),

        DeleteFamilyVersion((byte) 10),

        DeleteColumn((byte) 12),

        DeleteFamily((byte) 14);

        private final byte code;

        Type(final byte c) {
            this.code = c;
        }

        public byte getCode() {
            return this.code;
        }
    }
}
