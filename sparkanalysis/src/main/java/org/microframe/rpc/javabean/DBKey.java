package org.microframe.rpc.javabean;

import java.io.Serializable;

/**
 * used to
 * Created by tianjin on 9/2/16.
 */
public class DBKey implements Serializable {
    private String probeName;
    private String customService;
    private String ipClient;
    private String ipServer;
    private String portServer;
    private String operateType;
    private String timeStart;
    private String dbType;
    private String user;   //在没有识别用户的情况下为Client IP
    private String responseCode;
    private String sqlString;

    public DBKey() {
        super();
    }

    public String getProbeName() {
        return probeName;
    }

    public void setProbeName(String probeName) {
        this.probeName = probeName;
    }

    public String getCustomService() {
        return customService;
    }

    public void setCustomService(String customService) {
        this.customService = customService;
    }

    public String getIpClient() {
        return ipClient;
    }

    public void setIpClient(String ipClient) {
        this.ipClient = ipClient;
    }

    public String getIpServer() {
        return ipServer;
    }

    public void setIpServer(String ipServer) {
        this.ipServer = ipServer;
    }

    public String getPortServer() {
        return portServer;
    }

    public void setPortServer(String portServer) {
        this.portServer = portServer;
    }

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public String getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    public String getDbType() {
        return dbType;
    }

    public void setDbType(String dbType) {
        this.dbType = dbType;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getSqlString() {
        return sqlString;
    }

    public void setSqlString(String sqlString) {
        this.sqlString = sqlString;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DBKey dbKey = (DBKey) o;

        if (probeName != null ? !probeName.equals(dbKey.probeName) : dbKey.probeName != null) return false;
        if (customService != null ? !customService.equals(dbKey.customService) : dbKey.customService != null)
            return false;
        if (ipClient != null ? !ipClient.equals(dbKey.ipClient) : dbKey.ipClient != null) return false;
        if (ipServer != null ? !ipServer.equals(dbKey.ipServer) : dbKey.ipServer != null) return false;
        if (portServer != null ? !portServer.equals(dbKey.portServer) : dbKey.portServer != null) return false;
        if (operateType != null ? !operateType.equals(dbKey.operateType) : dbKey.operateType != null) return false;
        if (timeStart != null ? !timeStart.equals(dbKey.timeStart) : dbKey.timeStart != null) return false;
        if (dbType != null ? !dbType.equals(dbKey.dbType) : dbKey.dbType != null) return false;
        if (user != null ? !user.equals(dbKey.user) : dbKey.user != null) return false;
        if (responseCode != null ? !responseCode.equals(dbKey.responseCode) : dbKey.responseCode != null)
            return false;
        return sqlString != null ? sqlString.equals(dbKey.sqlString) : dbKey.sqlString == null;

    }

    @Override
    public int hashCode() {
        int result = probeName != null ? probeName.hashCode() : 0;
        result = 31 * result + (customService != null ? customService.hashCode() : 0);
        result = 31 * result + (ipClient != null ? ipClient.hashCode() : 0);
        result = 31 * result + (ipServer != null ? ipServer.hashCode() : 0);
        result = 31 * result + (portServer != null ? portServer.hashCode() : 0);
        result = 31 * result + (operateType != null ? operateType.hashCode() : 0);
        result = 31 * result + (timeStart != null ? timeStart.hashCode() : 0);
        result = 31 * result + (dbType != null ? dbType.hashCode() : 0);
        result = 31 * result + (user != null ? user.hashCode() : 0);
        result = 31 * result + (responseCode != null ? responseCode.hashCode() : 0);
        result = 31 * result + (sqlString != null ? sqlString.hashCode() : 0);
        return result;
    }


    @Override
    public String toString() {
        return "DBKey{" +
                "probeName='" + probeName + '\'' +
                ", customService='" + customService + '\'' +
                ", ipClient='" + ipClient + '\'' +
                ", ipServer='" + ipServer + '\'' +
                ", portServer='" + portServer + '\'' +
                ", operateType='" + operateType + '\'' +
                ", timeStart='" + timeStart + '\'' +
                ", dbType='" + dbType + '\'' +
                ", user='" + user + '\'' +
                ", responseCode='" + responseCode + '\'' +
                ", sqlString='" + sqlString + '\'' +
                '}';
    }
}
