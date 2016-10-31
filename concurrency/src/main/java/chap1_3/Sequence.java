package chap1_3;

import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

/**
 * Created by spark on 8/29/16.
 */
@ThreadSafe
public class Sequence {
    @GuardedBy("this")
    private int value;

    public synchronized int getValue() {
        return value++;
    }
}
