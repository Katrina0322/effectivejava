package com.study.es.configuration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * used to
 * Created by tianjin on 12/5/16.
 */
public class LogSetting {
    public static void setWarningLogLevel(String name) {

        Logger val = LoggerFactory.getLogger(name);

        if (val instanceof ch.qos.logback.classic.Logger) {
            ((ch.qos.logback.classic.Logger) val).setLevel(ch.qos.logback.classic.Level.WARN);
        }

        org.apache.log4j.Logger.getLogger(name).setLevel(org.apache.log4j.Level.WARN);

        java.util.logging.Logger.getLogger(name).setLevel(java.util.logging.Level.SEVERE);
    }
}
