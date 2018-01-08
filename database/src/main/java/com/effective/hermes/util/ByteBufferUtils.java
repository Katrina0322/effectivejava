package com.effective.hermes.util;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;

/**
 * filename: ByteBufferUtils
 * Description:
 * Author: ubuntu
 * Date: 1/8/18 1:50 PM
 */
public final class ByteBufferUtils {
    public final static int VALUE_MASK = 0x7f;
    public final static int NEXT_BIT_SHIFT = 7;
    public final static int NEXT_BIT_MASK = 1 << 7;

    private ByteBufferUtils() {
    }

    public static void writeLong(ByteBuffer out, long i){
        if(i <= 127 && i >= -112){
            out.put((byte)i);
            return;
        }
        int len = -112;
        if(i < 0){
            i ^= -1L;
            len = -120;
        }
        long tmp = i;
        while (tmp != 0){
            tmp = tmp >> 8;
            len--;
        }
        out.put((byte)len);
        len = len < -120 ? -(len + 120) : -(len + 112);
        for(int idx = len; idx != 0; idx--){
            int shiftbits = (idx - 1) * 8;
            long mask = 0xFFL << shiftbits;
            out.put((byte) ((i & mask) >> shiftbits));
        }
    }

    public static void putInt(OutputStream out, final int value) throws IOException {
        StreamUtils.writeInt(out, value);
    }
}
