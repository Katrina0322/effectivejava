package com.effective.gossip.util;

import java.util.concurrent.atomic.AtomicLong;

/**
 * filename: VersionHelper
 * Description:
 * Author: ubuntu
 * Date: 3/26/18 4:42 PM
 */
public class VersionHelper {
    private static AtomicLong v = new AtomicLong(0);
    private static VersionHelper ourInstance = new VersionHelper();

    public static VersionHelper getInstance() {
        return ourInstance;
    }

    private VersionHelper() {
    }

    public long nextVersion() {
        return v.incrementAndGet();
    }
}
