package com.effective.hermes.filesystem;

import java.util.List;

/**
 * description:
 * Created by spark on 18-1-30.
 */
public interface IFileSystem<T> {
    T create(String path);
    boolean delete(String path);
    boolean mkdirs(String path);
    boolean exists(String path);
    T open(String path);
    boolean isDirectory(String path);
    List<String> getFiles(String path);
}
