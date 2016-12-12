package org.microframe.rpc.chap18;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

/**
 * used to
 * Created by tianjin on 8/2/16.
 */
public class GetChannel {
    private static final int BSIZE = 1024;

    public static void main(String[] args) throws IOException {
        FileChannel fc = new FileOutputStream("data.txt").getChannel();
        fc.write(ByteBuffer.wrap("Some text ".getBytes()));
        fc.close();

        fc = new RandomAccessFile("data.txt", "rw").getChannel();
        fc.position(fc.size());
        fc.write(ByteBuffer.wrap("some more".getBytes()));
        fc.close();

        fc = new FileInputStream("data.txt").getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(BSIZE);
        fc.read(buffer);
        buffer.flip();

        System.out.println(buffer.asCharBuffer());

        buffer.rewind();
        System.out.println(String.format(System.getProperty("file.encoding") + "\t" + Charset.forName(System.getProperty("file.encoding")).decode(buffer)));
        fc = new FileOutputStream("data.txt").getChannel();
        fc.write(ByteBuffer.wrap("some more".getBytes("UTF-16BE")));
        fc.close();

        fc = new FileInputStream("data.txt").getChannel();
        buffer.clear();
        fc.read(buffer);
        buffer.flip();
        System.out.println(buffer.asCharBuffer());

        fc = new FileOutputStream("data.txt").getChannel();
        buffer = ByteBuffer.allocate(BSIZE);
        buffer.asCharBuffer().put("Some text");
        fc.write(buffer);
        fc.close();

        fc = new FileInputStream("data.txt").getChannel();
        buffer.clear();
        fc.read(buffer);
        buffer.flip();
        System.out.println(buffer.asCharBuffer());
    }
}
