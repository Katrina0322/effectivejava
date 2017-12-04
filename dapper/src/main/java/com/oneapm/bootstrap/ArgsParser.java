package com.oneapm.bootstrap;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * filename: ArgsParser
 * Description:
 * Author: ubuntu
 * Date: 12/4/17 2:51 PM
 */
public class ArgsParser {
    public static Map<String, String> parse(String args) {
        if (args == null || args.isEmpty()) {
            return Collections.emptyMap();
        }

        final Map<String, String> map = new HashMap<String, String>();

        Scanner scanner = new Scanner(args);
        scanner.useDelimiter("\\s*,\\s*");

        while (scanner.hasNext()) {
            String token = scanner.next();
            int assign = token.indexOf('=');

            if (assign == -1) {
                map.put(token, "");
            } else {
                String key = token.substring(0, assign);
                String value = token.substring(assign + 1);
                map.put(key, value);
            }
        }
        scanner.close();
        return Collections.unmodifiableMap(map);
    }
}
