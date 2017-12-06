package com.oneapm.instrument;


import javassist.CtBehavior;
import javassist.bytecode.AttributeInfo;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.LocalVariableAttribute;
import javassist.bytecode.MethodInfo;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * filename: JavaAssistUtils
 * Description:
 * Author: ubuntu
 * Date: 12/5/17 4:11 PM
 */
public class JavaAssistUtils {

    private static final Map<String, String> PRIMITIVE_JAVA_TO_JVM = createPrimitiveJavaToJvmMap();
    private static final String[] EMPTY_STRING_ARRAY = new String[0];

    private static Map<String,String> createPrimitiveJavaToJvmMap() {
        final Map<String, String> primitiveJavaToJvm = new HashMap<String, String>();
        primitiveJavaToJvm.put("byte", "B");
        primitiveJavaToJvm.put("char", "C");
        primitiveJavaToJvm.put("double", "D");
        primitiveJavaToJvm.put("float", "F");
        primitiveJavaToJvm.put("int", "I");
        primitiveJavaToJvm.put("long", "J");
        primitiveJavaToJvm.put("short", "S");
        primitiveJavaToJvm.put("void", "V");
        primitiveJavaToJvm.put("boolean", "Z");
        return primitiveJavaToJvm;
    }

    /**
     * java类型转化为
     * @param javaTypeArray
     * @return
     */
    public static String javaTypeToJvmSignature(String[] javaTypeArray){
        if (javaTypeArray == null || javaTypeArray.length == 0) {
            return "()";
        }
        StringBuilder buffer = new StringBuilder();
        buffer.append('(');
        for(String javaType : javaTypeArray){
            String jvmSignature = toJvmSignature(javaType);
            buffer.append(jvmSignature);
        }
        buffer.append(')');
        return buffer.toString();
    }

    private static String toJvmSignature(String javaType) {
        if (javaType == null) {
            throw new NullPointerException("javaType must not be null");
        }
        if (javaType.isEmpty()) {
            throw new IllegalArgumentException("invalid javaType. \"\"");
        }
        int javaObjectArraySize = getJavaObjectArraySize(javaType);
        int javaArrayLength = javaObjectArraySize * 2;
        String pureJavaType;
        if (javaObjectArraySize != 0) {
            // pure java
            pureJavaType = javaType.substring(0, javaType.length() - javaArrayLength);
        } else {
            pureJavaType = javaType;
        }
        String signature = PRIMITIVE_JAVA_TO_JVM.get(pureJavaType);
        if (signature != null) {
            // primitive type
            return appendJvmArray(signature, javaObjectArraySize);
        }
        return toJvmObject(javaObjectArraySize, pureJavaType);
    }

    private static String toJvmObject(int javaObjectArraySize, String pureJavaType) {
        //        "java.lang.String[][]"->"[[Ljava.lang.String;"
        final StringBuilder buffer = new StringBuilder(pureJavaType.length() + javaObjectArraySize + 2);
        for (int i = 0; i < javaObjectArraySize; i++) {
            buffer.append('[');
        }
        buffer.append('L');
        buffer.append(javaNameToJvmName(pureJavaType));
        buffer.append(';');
        return buffer.toString();
    }

    private static String javaNameToJvmName(String javaName) {
        if (javaName == null) {
            throw new NullPointerException("javaName must not be null");
        }
        return javaName.replace('.', '/');
    }

    private static String appendJvmArray(String signature, int javaObjectArraySize) {
        if (javaObjectArraySize == 0) {
            return signature;
        }
        StringBuilder sb = new StringBuilder(signature.length() + javaObjectArraySize);
        for (int i = 0; i < javaObjectArraySize; i++) {
            sb.append('[');
        }
        sb.append(signature);
        return sb.toString();
    }

    private static int getJavaObjectArraySize(String javaType) {
        if (javaType == null) {
            throw new NullPointerException("javaType must not be null");
        }
        if (javaType.isEmpty()) {
            return 0;
        }
        int endIndex = javaType.length() - 1;
        char checkEndArrayExist = javaType.charAt(endIndex);
        if (checkEndArrayExist != ']') {
            return 0;
        }
        int arraySize = 0;
        for (int i = endIndex; i > 0; i-=2){
            char arrayEnd = javaType.charAt(i);
            char arrayStart = javaType.charAt(i - 1);
            if (arrayStart == '[' && arrayEnd == ']') {
                arraySize++;
            } else {
                return arraySize;
            }
        }
        return arraySize;
    }

    public static String[] getParameterVariableName(CtBehavior behavior) {
        if(behavior == null) throw new NullPointerException("method must not be null");
        LocalVariableAttribute localVariableAttribute = lookupLocalVariableAttribute(behavior);
        if (localVariableAttribute == null) {
            return getParameterDefaultVariableName(behavior);
        }
        return getParameterVariableName(behavior, localVariableAttribute);
    }

    private static String[] getParameterVariableName(CtBehavior behavior, LocalVariableAttribute localVariableAttribute) {
        return null;
    }

    private static String[] getParameterDefaultVariableName(CtBehavior ctBehavior){
        if (ctBehavior == null) {
            throw new NullPointerException("method must not be null");
        }
        return null;
    }

    public static LocalVariableAttribute lookupLocalVariableAttribute(CtBehavior behavior) {
        if(behavior == null) throw new NullPointerException("method must not be null");
        MethodInfo methodInfo = behavior.getMethodInfo2();
        CodeAttribute codeAttribute = methodInfo.getCodeAttribute();
        if(codeAttribute == null) return null;
        AttributeInfo localVariableTable = codeAttribute.getAttribute(LocalVariableAttribute.tag);
        return (LocalVariableAttribute) localVariableTable;
    }

    public static String[] parseParameterSignature(String signature){
        if (signature == null) {
            throw new NullPointerException("signature must not be null");
        }
        List<String> parameterSignatureList = splitParameterSignature(signature);
        if (parameterSignatureList.isEmpty()) {
            return EMPTY_STRING_ARRAY;
        }
        return null;
    }

    private static List<String> splitParameterSignature(String signature) {
        String parameterSignature = getParameterSignature(signature);
        if (signature.isEmpty()) {
            return Collections.emptyList();
        }
        return null;
    }

    private static String getParameterSignature(String signature) {

        return null;
    }
}
