package com.ronglian;

import com.ronglian.configuration.LogSetting;

/**
 * Created by spark on 18-5-2.
 */
public class RecommendBaggingStarter {

    static {
        LogSetting.setWarningLogLevel("org");
        LogSetting.setWarningLogLevel("akka");
        LogSetting.setWarningLogLevel("io");
        LogSetting.setWarningLogLevel("httpclient.wire");
    }

    public static void main(String[] args) {

    }
}
