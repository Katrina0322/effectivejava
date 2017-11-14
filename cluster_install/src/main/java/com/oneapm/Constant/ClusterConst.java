package com.oneapm.Constant;

import com.sun.management.OperatingSystemMXBean;

import java.lang.management.ManagementFactory;

/**
 * filename: ClusterConst
 * Description:
 * Author: ubuntu
 * Date: 11/13/17 10:54 AM
 */
public class ClusterConst {
    public static final long ES_HEAP_SIZE;  // 1/4 total


    static {
        OperatingSystemMXBean osmb = (OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
        long gb = 1024L * 1024 * 1024;
        ES_HEAP_SIZE = osmb.getTotalPhysicalMemorySize() / gb / 4;
    }
}
