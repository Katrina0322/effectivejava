package com.effective.hermes.io;

import com.effective.hermes.filesystem.IFile;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

/**
 * filename: DefaultFileWriter
 * Description:
 * Author: ubuntu
 * Date: 3/5/18 10:47 AM
 */
public class DefaultFileWriter implements IFileWriter {
    private IFile<FileChannel> iFile;

    public DefaultFileWriter(IFile<FileChannel> iFile) {
        this.iFile = iFile;
    }

    @Override
    public void write(ByteBuffer buffer) throws IOException {
        FileChannel fileChannel = iFile.open(IFile.Mode.WRITE);
        buffer.flip();
        FileLock lock = fileChannel.tryLock();
        try {
            fileChannel.write(buffer);
            buffer.clear();
        } finally {
            lock.release();
            fileChannel.close();
        }
    }
}
