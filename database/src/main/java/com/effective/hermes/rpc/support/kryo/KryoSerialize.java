package com.effective.hermes.rpc.support.kryo;

import com.effective.hermes.rpc.support.RpcSerialize;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.pool.KryoPool;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * filename: KryoSerialize
 * Description:
 * Author: ubuntu
 * Date: 2/27/18 2:33 PM
 */
public class KryoSerialize implements RpcSerialize {
    private KryoPool pool;

    public KryoSerialize(final KryoPool pool) {
        this.pool = pool;
    }
    @Override
    public void serialize(OutputStream output, Object object) throws IOException {
        Kryo kryo = pool.borrow();
        Output out = new Output(output);
        kryo.writeClassAndObject(out, object);
        out.close();
        output.close();
        pool.release(kryo);
    }

    @Override
    public Object deserialize(InputStream input) throws IOException {
        Kryo kryo = pool.borrow();
        Input in = new Input(input);
        Object result = kryo.readClassAndObject(in);
        in.close();
        input.close();
        pool.release(kryo);
        return result;
    }
}
