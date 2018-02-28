package com.effective.hermes.rpc.support;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * filename: RpcSerialize
 * Description:
 * Author: ubuntu
 * Date: 2/27/18 11:11 AM
 */
public interface RpcSerialize {
    void serialize(OutputStream output, Object object) throws IOException;

    Object deserialize(InputStream input) throws IOException;
}
