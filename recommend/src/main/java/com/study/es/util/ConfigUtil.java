package com.study.es.util;

import com.ronglian.configuration.Constant;
import com.study.es.configuration.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by spark on 18-5-2.
 */
public class ConfigUtil {
    private static final Logger logger = LoggerFactory.getLogger(ConfigUtil.class);
    private static volatile Properties properties;

    public static String getProperty(String key){
        return getProperty(key, null);
    }

    public static String getProperty(String key, String defaultValue){
        if (properties == null) {
            try {
                loadProps();
                logger.info(properties.toString());
            } catch (IOException e) {
                logger.error("IOException 配置文件读取失败", e);
                return defaultValue;
            }
        }
        return properties.getProperty(key, defaultValue);
    }

    private static void loadProps() throws IOException {
        synchronized (ConfigUtil.class) {
            if (properties == null) {
                InputStream inputStream;
                try {
                    inputStream = new FileInputStream("resources/" + Constant.CONFIG_FILE);
                } catch (FileNotFoundException e) {
                    logger.info("外部告警配置文件resources/" + Constant.CONFIG_FILE + "不存在,使用默认配置");
                    inputStream = null;
                }
                if(inputStream == null){
                    inputStream = ConfigUtil.class.getClassLoader().getResourceAsStream(Constant.CONFIG_FILE);
                }
                properties = new Properties();
                properties.load(inputStream);
            }
        }
    }
}
