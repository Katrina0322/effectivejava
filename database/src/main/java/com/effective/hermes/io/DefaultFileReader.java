package com.effective.hermes.io;

import com.effective.hermes.filesystem.IFile;
import com.effective.hermes.util.ByteBufferUtils;

import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * filename: DefaultFileReader
 * Description:
 * Author: ubuntu
 * Date: 3/5/18 10:36 AM
 */
public class DefaultFileReader implements IFileReader {
    private IFile<FileChannel> file;

    public DefaultFileReader(IFile<FileChannel> file) {
        this.file = file;
    }

    @Override
    public byte[] read(long start, long size) throws IOException {
        FileChannel fileChannel = file.open(IFile.Mode.READ);
//        FileLock lock = fileChannel.tryLock();
        try {
            MappedByteBuffer byteBuffer = fileChannel.map(FileChannel.MapMode.READ_ONLY, start, size);
            byteBuffer.flip();
            byte[] result = ByteBufferUtils.readBuffer(byteBuffer);
            byteBuffer.clear();
            ByteBufferUtils.release(byteBuffer);
            return result;
        } finally {
//            lock.release();
            fileChannel.close();
        }
    }
}
