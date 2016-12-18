package com.oneapm.jvmerror;

import java.util.ArrayList;
import java.util.List;

/**
 * @Filename: HeapOOM
 * @Description: -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 * @Author: spark
 * @Time: 12/18/16
 */
public class HeapOOM {
    static class OOMObject{

    }

    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<>();
        while(true){
            list.add(new OOMObject());
        }
    }
}
