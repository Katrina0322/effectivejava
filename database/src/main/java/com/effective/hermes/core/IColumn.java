package com.effective.hermes.core;

/**
 * filename: IColumn
 * Description:
 * Author: ubuntu
 * Date: 1/11/18 2:23 PM
 */
public interface IColumn extends HeapSize, Comparable<IColumn>{
     byte[] getColumnName();
     byte[] getColumnValue();
     long getTimestamp();

    Type getType();

    enum Type {
        Put((byte) 4),

        Delete((byte) 8),

//        DeleteFamilyVersion((byte) 10),

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