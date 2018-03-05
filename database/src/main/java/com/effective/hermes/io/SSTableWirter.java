package com.effective.hermes.io;

/**
 * description:
 * Created by spark on 18-2-2.
 */
public interface SSTableWirter<T> {
    void write(T t);
}
