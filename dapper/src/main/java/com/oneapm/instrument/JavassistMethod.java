package com.oneapm.instrument;

import javassist.CtBehavior;

import java.beans.MethodDescriptor;

/**
 * filename: JavassistMethod
 * Description:
 * Author: ubuntu
 * Date: 12/6/17 2:56 PM
 */
public class JavassistMethod implements InstrumentMethod {

    private final CtBehavior behavior;
    private final InstrumentClass declaringClass;

//    private final MethodDescriptor descriptor;

    public JavassistMethod(CtBehavior behavior, InstrumentClass declaringClass) {
        this.behavior = behavior;
        this.declaringClass = declaringClass;
        String[] parameterVariableNames = JavaAssistUtils.getParameterVariableName(behavior);
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String[] getParameterTypes() {
        return new String[0];
    }

    @Override
    public String getReturnType() {
        return null;
    }

    @Override
    public int getModifiers() {
        return 0;
    }

    @Override
    public boolean isConstructor() {
        return false;
    }

    @Override
    public MethodDescriptor getDescriptor() {
        return null;
    }
}
