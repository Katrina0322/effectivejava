package org.microframe.rpc.nio;

/**
 * used to
 * Created by tianjin on 10/14/16.
 */
public class TimeClient {
    public static void main(String[] args) {
        int port = 8080;
        if (args != null && args.length > 0) {
            try {
                port = Integer.valueOf(args[0]);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        new Thread(new TimeClientHandler(port, "127.0.0.1"), "TimeClient-001").start();
    }
}
