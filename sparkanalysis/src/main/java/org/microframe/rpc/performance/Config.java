package org.microframe.rpc.performance;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Serializable;
import java.util.Properties;

/**
 * used to
 * Created by tianjin on 4/22/16.
 */
public class Config implements Serializable {


    public static Properties getConfig(String filename) {
        File file = new File(filename);
        if (!file.exists()) {
            file = new File("resources/" + filename);
            if (!file.exists()) {
                file = new File("/etc/analysis/" + filename);
            }
        }

        Properties prop = null;
        if (file.exists()) {
            prop = new Properties();
            try {
                prop.load(new FileReader(file));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return prop;
    }


}
