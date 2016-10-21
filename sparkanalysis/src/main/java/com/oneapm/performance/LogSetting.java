package com.oneapm.performance;

public class LogSetting {
    public static void setWarningLogLevel(String name) {

        org.slf4j.Logger val = org.slf4j.LoggerFactory.getLogger(name);
        if (val instanceof ch.qos.logback.classic.Logger) {
            ((ch.qos.logback.classic.Logger) val).setLevel(ch.qos.logback.classic.Level.WARN);
        }

        org.apache.log4j.Logger.getLogger(name).setLevel(org.apache.log4j.Level.WARN);

        java.util.logging.Logger.getLogger(name).setLevel(java.util.logging.Level.SEVERE);
    }
}
