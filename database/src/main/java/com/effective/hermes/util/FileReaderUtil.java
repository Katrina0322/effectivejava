package com.effective.hermes.util;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * filename: FileReaderUtil
 * Description:
 * Author: ubuntu
 * Date: 1/24/18 4:43 PM
 */
public class FileReaderUtil {

    public static ByteBuffer read(FileChannel fileChannel, long offset, long length) throws IOException {
        MappedByteBuffer buffer = null;
        try {
            buffer = fileChannel.map(FileChannel.MapMode.READ_ONLY, offset, length);
            return buffer.asReadOnlyBuffer();
        } finally {
            if(buffer != null) ByteBufferUtils.release(buffer);
        }
    }

 }
