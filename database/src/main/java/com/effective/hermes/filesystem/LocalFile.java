package com.effective.hermes.filesystem;

import java.io.*;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.List;


/**
 * filename: LocalFile
 * Description:
 * Author: ubuntu
 * Date: 1/31/18 4:12 PM
 */
public class LocalFile implements IFile<FileChannel> {
    private File file;
    private FileInputStream fileInputStream;
    private FileOutputStream fileOutputStream;

    public LocalFile(File file) {
        this.file = file;
    }

    public LocalFile(String path) {
        this.file = new File(path);
    }

    @Override
    public boolean delete() {
        return file.delete();
    }

    @Override
    public boolean exists() {
        return file.exists();
    }

    @Override
    public boolean isDirectory() {
        return file.isDirectory();
    }

    @Override
    public FileChannel open(Mode mode) throws FileNotFoundException {
        FileChannel fileChannel = null;
        synchronized (this) {
            switch (mode) {
                case READ:
                    if (fileInputStream == null) {
                        fileInputStream = new FileInputStream(file);
                    }
                    fileChannel = fileInputStream.getChannel();
                    break;
                case WRITE:
                    if (fileOutputStream == null) {
                        fileOutputStream = new FileOutputStream(file);
                    }
                    fileChannel = fileOutputStream.getChannel();
                    break;
            }
        }
        return fileChannel;
    }

    @Override
    public String getPath() {
        return file.getAbsolutePath();
    }

    @Override
    public List<IFile> listFiles() {
        File[] files = file.listFiles();
        if(files == null) return new ArrayList<>(0);
        List<IFile> result = new ArrayList<>(files.length);
        for(File file:files){
            IFile iFile = new LocalFile(file);
            result.add(iFile);
        }
        return result;
    }

    @Override
    public void close() throws IOException {
        if(fileInputStream != null){
            fileInputStream.close();
        }
        if(fileOutputStream != null){
            fileOutputStream.flush();
            fileOutputStream.close();
        }
    }
}
