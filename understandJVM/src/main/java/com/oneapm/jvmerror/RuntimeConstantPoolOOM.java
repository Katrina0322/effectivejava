package com.oneapm.jvmerror;

import java.util.ArrayList;
import java.util.List;

/**
 * @Filename: RuntimeConstantPoolOOM
 * @Description: -XX:PermSize=10m -XX:MaxPermSize=10m
 * @Author: spark
 * @Time: 12/18/16
 */
public class RuntimeConstantPoolOOM {
    public static void main(String[] args) {
//        List<String> list = new ArrayList<>();
//        int i = 0;
//        while(true){
//            list.add(String.valueOf(i++).intern());
//        }
        String str1 = new StringBuilder("计算机").append("软件").toString();
        System.out.println(str1.intern() == str1);

        String str2 = new StringBuilder("ja").append("va").toString();
        System.out.println(str2.intern() == str2);
    }
}
