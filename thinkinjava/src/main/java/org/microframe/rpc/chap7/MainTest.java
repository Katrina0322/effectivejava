package org.microframe.rpc.chap7;

/**
 * used to
 * Created by tianjin on 7/29/16.
 */
public class MainTest {
    public static void main(String[] args) {
        StaticTest test1 = new StaticTest();
        StaticTest test2 = new StaticTest();
        test1.setI(6);
        test2.setI(8);
        System.out.println(test1.getI());

    }
}
