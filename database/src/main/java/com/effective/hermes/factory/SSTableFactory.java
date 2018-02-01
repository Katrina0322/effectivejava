package com.effective.hermes.factory;

import com.effective.hermes.filesystem.IFile;
import com.effective.hermes.io.SSTableWirter;

/**
 * description:
 * Created by spark on 18-2-2.
 */
public interface SSTableFactory {
    IFile createIFile();
    SSTableWirter createrWirter(IFile file);
}
