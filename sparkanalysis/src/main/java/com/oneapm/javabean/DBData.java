package com.oneapm.javabean;

import java.io.Serializable;

/**
 * used to
 * Created by tianjin on 9/2/16.
 */
public class DBData implements Serializable {
    private double operateTime;
    private double reqTransTime;
    private double respTransTime;
    private long respPayload;
    private long count;
    private long slowCount;

    public DBData() {
        super();
    }

    public double getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(double operateTime) {
        this.operateTime = operateTime;
    }

    public double getReqTransTime() {
        return reqTransTime;
    }

    public void setReqTransTime(double reqTransTime) {
        this.reqTransTime = reqTransTime;
    }

    public double getRespTransTime() {
        return respTransTime;
    }

    public void setRespTransTime(double respTransTime) {
        this.respTransTime = respTransTime;
    }

    public long getRespPayload() {
        return respPayload;
    }

    public void setRespPayload(long respPayload) {
        this.respPayload = respPayload;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public long getSlowCount() {
        return slowCount;
    }

    public void setSlowCount(long slowCount) {
        this.slowCount = slowCount;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DBData dbData = (DBData) o;

        if (Double.compare(dbData.operateTime, operateTime) != 0) return false;
        if (Double.compare(dbData.reqTransTime, reqTransTime) != 0) return false;
        if (Double.compare(dbData.respTransTime, respTransTime) != 0) return false;
        if (respPayload != dbData.respPayload) return false;
        if (count != dbData.count) return false;
        return slowCount == dbData.slowCount;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(operateTime);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(reqTransTime);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(respTransTime);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (int) (respPayload ^ (respPayload >>> 32));
        result = 31 * result + (int) (count ^ (count >>> 32));
        result = 31 * result + (int) (slowCount ^ (slowCount >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "DBData{" +
                "operateTime=" + operateTime +
                ", reqTransTime=" + reqTransTime +
                ", respTransTime=" + respTransTime +
                ", respPayload=" + respPayload +
                ", count=" + count +
                ", slowCount=" + slowCount +
                '}';
    }
}
