package com.effective.hermes.rpc;

import java.io.Serializable;

/**
 * filename: MessageResponse
 * Description:
 * Author: ubuntu
 * Date: 2/27/18 10:09 AM
 */
public class MessageResponse implements Serializable {
    private static final long serialVersionUID = -4628239730293658445L;
    private String messageId;   //唯一ID
    private String error;       //错误消息
    private Object resultDesc;  //方法调用结果

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Object getResult() {
        return resultDesc;
    }

    public void setResult(Object resultDesc) {
        this.resultDesc = resultDesc;
    }

    @Override
    public String toString() {
        return "MessageResponse{" +
                "messageId='" + messageId + '\'' +
                ", error='" + error + '\'' +
                ", resultDesc=" + resultDesc +
                '}';
    }
}
