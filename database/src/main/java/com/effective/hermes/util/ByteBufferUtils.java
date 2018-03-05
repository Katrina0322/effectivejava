package com.effective.hermes.util;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.security.AccessController;
import java.security.PrivilegedAction;

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

    public static byte[] readBuffer(ByteBuffer byteBuffer){
        byte[] bytes = new byte[byteBuffer.limit()];
        byteBuffer.get(bytes);
        return bytes;
    }

    public static void writeInt(ByteBuffer buffer, final int value) throws IOException {
        buffer.put((byte) (0xff & (value >> 24)));
        buffer.put((byte) (0xff & (value >> 16)));
        buffer.put((byte) (0xff & (value >> 8)));
        buffer.put((byte) (0xff & value));
    }

    public static void release(ByteBuffer byteBuffer){
        AccessController.doPrivileged(new PrivilegedAction<Object>() {
            @Override
            public Object run() {

                Method getCleanerMethod = null;
                try {
                    getCleanerMethod = byteBuffer.getClass().getMethod("cleaner");

                sun.misc.Cleaner cleaner =(sun.misc.Cleaner)getCleanerMethod.invoke(byteBuffer);
                    cleaner.clean();
                } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                    e.printStackTrace();
                }
                return null;
            }
        });
    }

    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.remaining());
        System.out.println(byteBuffer.capacity());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.mark());
        byteBuffer.putChar('A');
        byteBuffer.putChar('B');
        System.out.println();
        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.remaining());
        System.out.println(byteBuffer.capacity());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.mark());
        byteBuffer.flip();
        System.out.println();
        System.out.println(byteBuffer.position());
        System.out.println(byteBuffer.remaining());
        System.out.println(byteBuffer.capacity());
        System.out.println(byteBuffer.limit());
        System.out.println(byteBuffer.mark());
        byte[] bytes = new byte[byteBuffer.remaining()];
        byteBuffer.get(bytes);
        System.out.println(new String(bytes));
    }
}
