package com.oneapm.chap2;

/**
 * used to
 * Created by tianjin on 7/27/16.
 */
public class ShowProperties {

    public static void main(String[] args) {
        System.getProperties().list(System.out);
        System.out.println(System.getProperty("user.name"));
        System.out.println(System.getProperty("java.library.path"));
    }
}
