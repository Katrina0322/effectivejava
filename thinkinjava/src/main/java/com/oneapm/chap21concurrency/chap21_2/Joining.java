package com.oneapm.chap21concurrency.chap21_2;



/**
 * used to
 * Created by tianjin on 8/3/16.
 */
public class Joining {
    public static void main(String[] args) {
        Sleeper
                sleepy = new Sleeper("Sleepy",1500),
                grumpy = new Sleeper("Grumpy",1500);
        Joiner
                depoy = new Joiner("Depoy",sleepy),
                doc = new Joiner("Doc",grumpy);
        grumpy.interrupt();
    }
}
