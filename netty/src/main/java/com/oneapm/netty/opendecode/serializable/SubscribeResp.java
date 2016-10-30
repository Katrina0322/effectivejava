package com.oneapm.netty.opendecode.serializable;

import java.io.Serializable;

/**
 * Created by spark on 10/27/16.
 */
public class SubscribeResp implements Serializable {
    private static final long serialVersionUID = 1L;
    private int subReqId;
    private int respCode;
    private String desc;

    public int getSubReqId() {
        return subReqId;
    }

    public void setSubReqId(int subReqId) {
        this.subReqId = subReqId;
    }

    public int getRespCode() {
        return respCode;
    }

    public void setRespCode(int respCode) {
        this.respCode = respCode;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SubscribeResp that = (SubscribeResp) o;

        if (subReqId != that.subReqId) return false;
        if (respCode != that.respCode) return false;
        return desc != null ? desc.equals(that.desc) : that.desc == null;

    }

    @Override
    public int hashCode() {
        int result = subReqId;
        result = 31 * result + respCode;
        result = 31 * result + (desc != null ? desc.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "SubscribeResp{" +
                "subReqId=" + subReqId +
                ", respCode=" + respCode +
                ", desc='" + desc + '\'' +
                '}';
    }
}
