package com.oneapm.rpcmodel;

import java.util.Map;

/**
 * rpc 服务映射容器
 * Created by tianjin on 12/8/16.
 */
public class MessageKeyVal {
    private Map<String , Object> messageKeyVal;

    public Map<String, Object> getMessageKeyVal() {
        return messageKeyVal;
    }

    public void setMessageKeyVal(Map<String, Object> messageKeyVal) {
        this.messageKeyVal = messageKeyVal;
    }
}
