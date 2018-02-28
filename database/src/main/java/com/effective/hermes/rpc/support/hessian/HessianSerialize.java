package com.effective.hermes.rpc.support.hessian;

import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.Hessian2Output;
import com.effective.hermes.rpc.support.RpcSerialize;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * filename: HessianSerialize
 * Description:
 * Author: ubuntu
 * Date: 2/27/18 2:14 PM
 */
public class HessianSerialize implements RpcSerialize {
    @Override
    public void serialize(OutputStream output, Object object) throws IOException {
        Hessian2Output ho = new Hessian2Output(output);
        ho.startMessage();
        ho.writeObject(object);
        ho.completeMessage();
        ho.close();
        output.close();
    }

    @Override
    public Object deserialize(InputStream input) throws IOException {
        Hessian2Input hi = new Hessian2Input(input);
        hi.startMessage();
        Object result = hi.readObject();
        hi.completeMessage();
        hi.close();
        input.close();
        return result;
    }
}
