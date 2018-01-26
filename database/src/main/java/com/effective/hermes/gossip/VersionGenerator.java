package com.effective.hermes.gossip;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * filename: VersionGenerator
 * Description:
 * Author: ubuntu
 * Date: 1/26/18 11:10 AM
 */
public class VersionGenerator {
    private static AtomicInteger version = new AtomicInteger(0);
    public static int getNextVersion()
    {
        return version.incrementAndGet();
    }
}
