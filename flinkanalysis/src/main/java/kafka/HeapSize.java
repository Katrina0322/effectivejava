package kafka;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;

/**
 * filename: HeapSize
 * Description:
 * Author: ubuntu
 * Date: 12/22/17 4:26 PM
 */
public class HeapSize {
    public static void main(String[] args) {
        MemoryMXBean bean = ManagementFactory.getMemoryMXBean();
        MemoryUsage memoryUsage = bean.getHeapMemoryUsage();
        System.out.println(memoryUsage.getUsed() / 1024 / 1024);
        System.out.println(memoryUsage.getInit() / 1024 / 1024);
        System.out.println(memoryUsage.getMax() / 1024 / 1024);
    }

}
