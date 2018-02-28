package com.effective.hermes.rpc;

import java.io.Serializable;
import java.util.Arrays;

/**
 * filename: MessageRequest
 * Description:
 * Author: ubuntu
 * Date: 2/27/18 10:08 AM
 */
public class MessageRequest implements Serializable {

    private static final long serialVersionUID = 779639215038924077L;
    private String messageId;   //唯一ID
    private String className;   //接口名
    private String methodName;  //方法名
    private Class<?>[] typeParameters;  //参数类型
    private Object[] parametersVal;     //参数

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Class<?>[] getTypeParameters() {
        return typeParameters;
    }

    public void setTypeParameters(Class<?>[] typeParameters) {
        this.typeParameters = typeParameters;
    }

    public Object[] getParameters() {
        return parametersVal;
    }

    public void setParameters(Object[] parametersVal) {
        this.parametersVal = parametersVal;
    }

    @Override
    public String toString() {
        return "MessageRequest{" +
                "messageId='" + messageId + '\'' +
                ", className='" + className + '\'' +
                ", methodName='" + methodName + '\'' +
                ", typeParameters=" + Arrays.toString(typeParameters) +
                ", parametersVal=" + Arrays.toString(parametersVal) +
                '}';
    }
}
