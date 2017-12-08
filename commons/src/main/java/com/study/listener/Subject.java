package com.study.listener;

/**
 * filename: Subject
 * Description:
 * Author: ubuntu
 * Date: 12/8/17 4:31 PM
 */
public interface Subject {
    void addListener(Listener listener);
    void removeListener(Listener listener);
    void notifyListeners();
    void setChanged();
    void doSomeThing();
}
