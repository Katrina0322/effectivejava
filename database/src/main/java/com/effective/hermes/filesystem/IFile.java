package com.effective.hermes.filesystem;

import java.io.Closeable;
import java.io.FileNotFoundException;
import java.util.List;

/**
 * filename: IFile
 * Description:
 * Author: ubuntu
 * Date: 1/31/18 3:36 PM
 */
public interface IFile<T> extends Closeable{
    boolean delete();
    boolean exists();
    boolean isDirectory();
    T open(Mode mode) throws FileNotFoundException;
    String getPath();
    List<IFile> listFiles();
}
