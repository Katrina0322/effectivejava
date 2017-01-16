package com.oneapm.test;

import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * @filename: FlinkTest
 * @Description:
 * @Author: ubuntu
 * @Date: 1/16/17 4:36 PM
 */
public class FlinkTest {
    public static void main(String[] args) {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.createLocalEnvironment();
    }
}
