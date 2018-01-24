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
        write();
        read();
    }

    public static void write(){
        File file = new File("aa.txt");
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(file, true);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        assert fileOutputStream != null;
        FileChannel channel = fileOutputStream.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(100);
        buffer.putInt(100);
        buffer.putChar('A');
        buffer.putChar('C');

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

    public static void read(){
        File file = new File("aa.txt");
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
//        file.deleteOnExit();
        assert inputStream != null;
        FileChannel channel = inputStream.getChannel();
        MappedByteBuffer mappedByteBuffer = null;
        try {
//            System.out.println(file.length());
            mappedByteBuffer = channel.map(FileChannel.MapMode.READ_ONLY, 0, file.length());
            System.out.println(mappedByteBuffer.position());
            System.out.println(mappedByteBuffer.capacity());
            System.out.println(mappedByteBuffer.remaining());
            System.out.println(mappedByteBuffer.limit());
            System.out.println();
//            mappedByteBuffer.flip();
//            System.out.println(mappedByteBuffer.position());
//            System.out.println(mappedByteBuffer.capacity());
//            System.out.println(mappedByteBuffer.remaining());
//            System.out.println(mappedByteBuffer.limit());


            byte[] buffer = new byte[mappedByteBuffer.capacity()];
            mappedByteBuffer.get(buffer, 0 , mappedByteBuffer.capacity());
            mappedByteBuffer.clear();
            String s = new String(buffer);
            System.out.println(s);
//
            channel.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


//        if (file.delete())
//            System.out.println("Temporary file deleted: " + file);
//        else
//            System.err.println("Not yet deleted: " + file);


    }
}
