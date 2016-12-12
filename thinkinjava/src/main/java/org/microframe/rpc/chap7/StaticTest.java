package org.microframe.rpc.chap7;

/**
 * used to
 * Created by tianjin on 7/29/16.
 */
public class StaticTest {
    private static int i;

    public StaticTest() {
        super();
    }

    public static int getI() {
        return i;
    }

    public static void setI(int i) {
        StaticTest.i = i;
    }
}
