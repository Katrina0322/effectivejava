package com.effective.hermes.rpc.support.kryo;

import com.effective.hermes.rpc.MessageRequest;
import com.effective.hermes.rpc.MessageResponse;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.pool.KryoFactory;
import com.esotericsoftware.kryo.pool.KryoPool;
import org.objenesis.strategy.StdInstantiatorStrategy;

/**
 * filename: KryoPoolFactory
 * Description:
 * Author: ubuntu
 * Date: 2/27/18 2:36 PM
 */
public class KryoPoolFactory {
    private static KryoPoolFactory poolFactory;
    private KryoFactory kryoFactory = new KryoFactory() {
        @Override
        public Kryo create() {
            Kryo kryo = new Kryo();
            kryo.setReferences(false);
            kryo.register(MessageRequest.class);
            kryo.register(MessageResponse.class);
            kryo.setInstantiatorStrategy(new StdInstantiatorStrategy());
            return kryo;
        }
    };

    private KryoPool pool = new KryoPool.Builder(kryoFactory).build();
    private KryoPoolFactory() {
    }

    public static KryoPool getKryoPoolInstance() {
        if (poolFactory == null) {
            synchronized (KryoPoolFactory.class) {
                if (poolFactory == null) {
                    poolFactory = new KryoPoolFactory();
                }
            }
        }
        return poolFactory.getPool();
    }

    private KryoPool getPool() {
        return pool;
    }
}
