package com.study.listener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * filename: AbstractSubject
 * Description:
 * Author: ubuntu
 * Date: 12/8/17 4:40 PM
 */
public abstract class AbstractSubject implements Subject {

    private final List<Listener> listeners;

    private AtomicBoolean changed = new AtomicBoolean(false);

    public AbstractSubject() {
        this.listeners = new ArrayList<>();
    }

    @Override
    public void addListener(Listener listener) {
        synchronized (listeners){
            if(!listeners.contains(listener)) listeners.add(listener);
        }
    }

    @Override
    public void removeListener(Listener listener) {
        synchronized (listeners){
            listeners.remove(listener);
        }
    }

    @Override
    public void notifyListeners() {
        List<Listener> local;
        synchronized (listeners){
            local = Collections.unmodifiableList(listeners);
        }
        for(Listener listener:local){
            listener.update(this);
        }
    }

    @Override
    public void setChanged() {
        changed.compareAndSet(false, true);
        notifyListeners();
        changed.compareAndSet(true, false);
    }

    public abstract void doSomeThing();

}
