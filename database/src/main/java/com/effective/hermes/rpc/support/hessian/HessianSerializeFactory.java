package com.effective.hermes.rpc.support.hessian;

import org.apache.commons.pool2.BasePooledObjectFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.impl.DefaultPooledObject;

/**
 * filename: HessianSerializeFactory
 * Description:
 * Author: ubuntu
 * Date: 2/27/18 2:19 PM
 */
public class HessianSerializeFactory extends BasePooledObjectFactory<HessianSerialize> {
    @Override
    public HessianSerialize create() throws Exception {
        return new HessianSerialize();
    }

    @Override
    public PooledObject<HessianSerialize> wrap(HessianSerialize hessianSerialize) {
        return new DefaultPooledObject<>(hessianSerialize);
    }
}
