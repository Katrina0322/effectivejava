package com.oneapm.instrument;

import javassist.CtClass;
import javassist.CtConstructor;
import javassist.CtMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * filename: JavassistClass
 * Description:
 * Author: ubuntu
 * Date: 12/5/17 3:04 PM
 */
public class JavassistClass implements InstrumentClass {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final boolean isDebug = logger.isDebugEnabled();

    private final ClassLoader classLoader;

    private final CtClass ctClass;

    public JavassistClass(ClassLoader classLoader, CtClass ctClass) {
        this.classLoader = classLoader;
        this.ctClass = ctClass;
    }

    @Override
    public boolean isInterface() {
        return ctClass.isInterface();
    }

    @Override
    public String getName() {
        return ctClass.getName();
    }

    @Override
    public String getSuperClass() {
        return ctClass.getClassFile2().getSuperclass();
    }

    @Override
    public String[] getInterfaces() {
        return ctClass.getClassFile2().getInterfaces();
    }

    @Override
    public InstrumentMethod getConstructor(String... parameterTypes) {
        CtConstructor constructor = getConstructor0(parameterTypes);
        return null;
    }

    private CtConstructor getConstructor0(String[] parameterTypes) {
        String jvmSignature = JavaAssistUtils.javaTypeToJvmSignature(parameterTypes);
        for (CtConstructor constructor:ctClass.getDeclaredConstructors()){
            String descriptor = constructor.getMethodInfo2().getDescriptor();
            if(descriptor.startsWith(jvmSignature) && constructor.isConstructor()){
                return constructor;
            }
        }
        return null;
    }

    @Override
    public List<InstrumentMethod> getDeclaredMethods() {
        return getDeclaredMethods(MethodFilters.ACCEPT_ALL);
    }

    @Override
    public List<InstrumentMethod> getDeclaredMethods(MethodFilter filter) {
        if (filter == null) {
            throw new NullPointerException("methodFilter must not be null");
        }
        CtMethod[] declaredMethod = ctClass.getDeclaredMethods();
        List<InstrumentMethod> candidateList = new ArrayList<>(declaredMethod.length);
        for(CtMethod ctMethod:declaredMethod){
            InstrumentMethod method = new JavassistMethod(ctMethod, this);
        }

        return null;
    }

    @Override
    public InstrumentMethod getDeclaredMethods(String name, String... parameterTypes) {
        return null;
    }

    @Override
    public List<InstrumentClass> getNestedClasses(ClassFilter filter) {
        return null;
    }

    @Override
    public ClassLoader getClassLoader() {
        return classLoader;
    }

    @Override
    public boolean isInterceptable() {
        return false;
    }

    @Override
    public boolean hasConstructor(String... parameterTypes) {
        return false;
    }

    @Override
    public boolean hasDeclaredMethod(String methodName, String... parameterTypes) {
        return false;
    }

    @Override
    public boolean hasMethod(String methodName, String... parameterTypes) {
        return false;
    }

    @Override
    public boolean hasEnclosingMethod(String methodName, String... parameterTypes) {
        return false;
    }

    @Override
    public boolean hasField(String name, String type) {
        return false;
    }

    @Override
    public boolean hasField(String name) {
        return false;
    }

    @Override
    public void weave(String adviceClassName) throws InstrumentException {

    }

    @Override
    public void addField(String accessorTypeName) throws InstrumentException {

    }

    @Override
    public void addGetter(String getterTypeName, String fieldName) throws InstrumentException {

    }

    @Override
    public void addSetter(String setterTypeName, String fieldName) throws InstrumentException {

    }

    @Override
    public void addSetter(String setterTypeName, String fieldName, boolean removeFinal) throws InstrumentException {

    }

    @Override
    public byte[] toBytecode() throws InstrumentException {
        return new byte[0];
    }
}
