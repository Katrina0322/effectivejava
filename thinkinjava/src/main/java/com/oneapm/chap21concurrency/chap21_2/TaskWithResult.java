package com.oneapm.chap21concurrency.chap21_2;

import java.util.concurrent.Callable;

/**
 * used to
 * Created by tianjin on 8/3/16.
 */
public class TaskWithResult implements Callable<String> {
    private int id;

    public TaskWithResult(int id) {
        this.id = id;
    }

    @Override
    public String call() throws Exception {
        return "result of task" + id;
    }
}
