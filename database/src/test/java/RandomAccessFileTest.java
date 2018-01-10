import java.io.*;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

/**
 * filename: RandomAccessFileTest
 * Description:
 * Author: ubuntu
 * Date: 1/10/18 9:31 AM
 */
public class RandomAccessFileTest {

    public static void main(String[] args) {

//            long length = 100000;
//            long position = 10000;
//            RandomAccessFile randomAccessFile = new RandomAccessFile("", "r");
//            FileChannel fileChannel = randomAccessFile.getChannel();
//            MappedByteBuffer mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, position, length);
//            FileLock fileLock = fileChannel.tryLock(position, length, false);
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream("aa.txt", true);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        assert fileOutputStream != null;
        FileChannel channel = fileOutputStream.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(100);
        buffer.putInt(100);
        try {
            buffer.flip();
            channel.write(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            buffer.clear();
            try {
                channel.close();
                fileOutputStream.flush();
                fileOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
