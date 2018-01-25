package com.effective.hermes.factory;

import com.effective.hermes.constant.Constant;
import com.effective.hermes.core.DefaultStoreFile;
import com.effective.hermes.core.IndexBlock;
import com.effective.hermes.core.StoreFile;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * filename: StoreFileFactory
 * Description:
 * Author: ubuntu
 * Date: 1/23/18 6:17 PM
 */
public class StoreFileFactory {
    public StoreFile createStoreFile(String tableName){
        String fileName = createName();
        IndexBlock index = new IndexBlock();
        String location = Constant.DATA_PATH + "/" + tableName + "/" + fileName;
        StoreFile storeFile = new DefaultStoreFile(location, index);
        return storeFile;
    }

    private String createName() {
        long time = System.currentTimeMillis();
        String server;
        try {
             server = InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            server = "localhost";
            e.printStackTrace();
        }
        return server + "_" + time;
    }


}
