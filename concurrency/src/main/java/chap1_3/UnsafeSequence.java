package chap1_3;

import javax.annotation.concurrent.NotThreadSafe;

/**
 * Created by spark on 8/29/16.
 */
@NotThreadSafe
public class UnsafeSequence {
    private int value;

    public int getValue() {
        return value++;
    }
}
