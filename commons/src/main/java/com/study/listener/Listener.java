package com.study.listener;

/**
 * filename: Listener
 * Description:
 * Author: ubuntu
 * Date: 12/8/17 4:25 PM
 */
public interface Listener<T extends Subject> {
    void update(T t);
}
