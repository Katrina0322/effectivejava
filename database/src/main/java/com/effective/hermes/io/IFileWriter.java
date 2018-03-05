package com.effective.hermes.io;


import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * filename: IFileWriter
 * Description:
 * Author: ubuntu
 * Date: 3/5/18 10:31 AM
 */
public interface IFileWriter {
    void write(ByteBuffer buffer) throws IOException;
}
