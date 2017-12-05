package com.oneapm.instrument;

import java.util.List;

/**
 * filename: ASMClass
 * Description:
 * Author: ubuntu
 * Date: 12/5/17 3:37 PM
 */
public class ASMClass implements InstrumentClass {

    private final ClassLoader classLoader;

    private final ASMClassNodeAdapter classNode;

    public ASMClass(ClassLoader classLoader, ASMClassNodeAdapter classNode) {
        this.classLoader = classLoader;
        this.classNode = classNode;
    }

    @Override
    public boolean isInterface() {
        return false;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getSuperClass() {
        return null;
    }

    @Override
    public String[] getInterfaces() {
        return new String[0];
    }

    @Override
    public InstrumentMethod getConstructor(String... parameterTypes) {
        return null;
    }

    @Override
    public List<InstrumentMethod> getDeclaredMethods() {
        return null;
    }

    @Override
    public List<InstrumentMethod> getDeclaredMethods(MethodFilter filter) {
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
        return null;
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
