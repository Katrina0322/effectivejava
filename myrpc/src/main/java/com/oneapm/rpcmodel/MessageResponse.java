package com.oneapm.rpcmodel;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * rpc response
 * Created by tianjin on 12/8/16.
 */
public class MessageResponse implements Serializable {
    private String messageId;
    private String error;
    private Object resultDesc;

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

    public Object getResultDesc() {
        return resultDesc;
    }

    public void setResultDesc(Object resultDesc) {
        this.resultDesc = resultDesc;
    }

    @Override
    public String toString() {
//        return "MessageResponse{" +
//                "messageId='" + messageId + '\'' +
//                ", error='" + error + '\'' +
//                ", resultDesc=" + resultDesc +
//                '}';
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("messageId", messageId)
                .append("error", error)
                .toString();
    }
}
