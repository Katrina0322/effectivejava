package com.effective.hermes.io;

import java.io.IOException;

/**
 * filename: IFileReader
 * Description:
 * Author: ubuntu
 * Date: 3/5/18 10:35 AM
 */
public interface IFileReader {
    byte[] read(long start, long size) throws IOException;
}
