package com.oneapm.bootstrap;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * filename: LoadState
 * Description:
 * Author: ubuntu
 * Date: 12/4/17 2:41 PM
 */
public class LoadState {
    private static final boolean STATE_NONE = false;
    private static final boolean STATE_STARTED = true;
    private final AtomicBoolean state = new AtomicBoolean(STATE_NONE);

    public AtomicBoolean getState() {
        return state;
    }

    public boolean start(){
        return state.compareAndSet(STATE_NONE, STATE_STARTED);
    }
}
