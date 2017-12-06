package com.oneapm.instrument;

import java.beans.MethodDescriptor;

/**
 * filename: InstrumentMethod
 * Description:
 * Author: ubuntu
 * Date: 12/5/17 2:45 PM
 */
public interface InstrumentMethod {
    String getName();

    String[] getParameterTypes();

    String getReturnType();

    int getModifiers();

    boolean isConstructor();

    MethodDescriptor getDescriptor();
}
