package com.effective.hermes.cache;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * filename: DefaultMemStore
 * Description:
 * Author: ubuntu
 * Date: 1/8/18 5:08 PM
 */
public class DefaultMemStore extends AbstractMemStore {
    private final AtomicBoolean inMemoryFlushInProgress = new AtomicBoolean(false);
    @Override
    protected void checkActiveSize() {
        if(shouldFlushInMemory()){
            flushInMemory();
        }
    }

    private void flushInMemory() {

    }

    private boolean shouldFlushInMemory() {
        return inMemoryFlushInProgress.compareAndSet(false, true);
    }

    @Override
    public MemStoreSize getFlushableSize() {
        return null;
    }
}
