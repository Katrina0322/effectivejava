package commonuse;

import org.apache.commons.io.IOUtils;

import java.io.IOException;

/**
 * used to
 * Created by tianjin on 6/20/16.
 */
public class FileStream {

    public static void main(String[] args) {
        FileStream fileStream = new FileStream();
        System.out.println(fileStream.getFile("abc.txt"));
    }


    private String getFile(String fileName) {
        String result = "";

        ClassLoader classLoader = getClass().getClassLoader();

        try {
            result = IOUtils.toString(classLoader.getResourceAsStream(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }


        return result;
    }
}
