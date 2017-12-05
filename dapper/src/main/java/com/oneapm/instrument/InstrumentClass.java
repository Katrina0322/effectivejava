package com.oneapm.instrument;



import java.util.List;

/**
 * filename: InstrumentClass
 * Description:
 * Author: ubuntu
 * Date: 12/5/17 2:42 PM
 */
public interface InstrumentClass {
    boolean isInterface();
    String getName();
    String getSuperClass();
    String[] getInterfaces();
    InstrumentMethod getConstructor(String... parameterTypes);
    List<InstrumentMethod> getDeclaredMethods();
    List<InstrumentMethod> getDeclaredMethods(MethodFilter filter);
    InstrumentMethod getDeclaredMethods(String name, String... parameterTypes);
    List<InstrumentClass> getNestedClasses(ClassFilter filter);
    ClassLoader getClassLoader();

    boolean isInterceptable();

    boolean hasConstructor(String... parameterTypes);

    boolean hasDeclaredMethod(String methodName, String... parameterTypes);

    boolean hasMethod(String methodName, String... parameterTypes);

    boolean hasEnclosingMethod(String methodName, String... parameterTypes);

    boolean hasField(String name, String type);

    boolean hasField(String name);

    void weave(String adviceClassName) throws InstrumentException;

    void addField(String accessorTypeName) throws InstrumentException;

    void addGetter(String getterTypeName, String fieldName) throws InstrumentException;

    void addSetter(String setterTypeName, String fieldName) throws InstrumentException;

    void addSetter(String setterTypeName, String fieldName, boolean removeFinal) throws InstrumentException;

    byte[] toBytecode() throws InstrumentException;
}
