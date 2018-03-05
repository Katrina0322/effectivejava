package com.effective.hermes.constant;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * filename: Constant
 * Description:
 * Author: ubuntu
 * Date: 1/23/18 6:23 PM
 */
public final class Constant {
    public static final String DATA_PATH = "";
    public static final int TRUNK_SIZE = 64 * 1024;
    public static final ExecutorService SERVICE = Executors.newFixedThreadPool(10);
}
