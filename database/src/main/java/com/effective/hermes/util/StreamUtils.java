package com.effective.hermes.util;

import java.io.IOException;
import java.io.OutputStream;

/**
 * filename: StreamUtils
 * Description:
 * Author: ubuntu
 * Date: 1/8/18 2:55 PM
 */
public class StreamUtils {
    public static void writeInt(OutputStream out, int v) throws IOException {
        out.write((byte) (0xff & (v >> 24)));
        out.write((byte) (0xff & (v >> 16)));
        out.write((byte) (0xff & (v >> 8)));
        out.write((byte) (0xff & v));
    }
}
