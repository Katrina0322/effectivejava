package org.microframe.rpc.chap18;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by spark on 8/1/16.
 */
public class BufferedInputFile {
    public static String read(String filename) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader(filename));
        String s;
        StringBuilder sb = new StringBuilder();
        while ((s = in.readLine()) != null) {
            sb.append(s + "\n");
        }
        in.close();

        return sb.toString();
    }


    public static void main(String[] args) throws IOException {
        System.out.println(System.getProperty("user.dir"));

        String className = BufferedInputFile.class.getName();
        String classNamePath = className.replace(".", "/") + ".class";
        System.out.println(classNamePath);
        System.out.println(BufferedInputFile.class.getClassLoader().getResource("."));
//        File directory = new File("");
//        System.out.println(directory.getAbsolutePath());
//
//        System.out.println(read(Class.class.getResource("/").getPath() + "BufferedInputFile.class"));
    }
}
