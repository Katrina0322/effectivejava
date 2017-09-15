package concurrent;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

/**
 * filename: ConcurrentHashMapTest
 * Description:
 * Author: ubuntu
 * Date: 8/30/17 11:06 AM
 */
public class ConcurrentHashMapTest {
    private ConcurrentHashMap<String, String> test = new ConcurrentHashMap<>();
    private Map<String, String> map = new HashMap<>();
    ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {

    }

}
