package org.microframe.rpc.chap21concurrency.chap21_2;

import java.io.IOException;

/**
 * used to
 * Created by tianjin on 8/3/16.
 */
public class ResponsiveUI extends Thread {
    private static volatile double d = 1;

    public ResponsiveUI() {
        setDaemon(true);
        start();
    }

    public static void main(String[] args) throws IOException {
        new ResponsiveUI();
        System.in.read();
        System.out.println(d);
    }

    public void run() {
        while (true) {
            d = d + (Math.PI + Math.E) / d;
        }
    }
}
