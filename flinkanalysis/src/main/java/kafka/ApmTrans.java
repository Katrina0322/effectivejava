package kafka;

/**
 * filename: ApmTrans
 * Description:
 * Author: ubuntu
 * Date: 12/21/17 4:28 PM
 */
public class ApmTrans {
    private String Agent;
    private String Tier;        //接收方集群
    private String entranceService;     //入口事物
    private String language;
    private String webService;      //web事物
    private long startTime;
    private String componentName;

    private double responseTime;    //总响应时间
    private double maxRespTime;     //每颗粒度最大响应时间
    private double minRespTime;     //每颗粒度最小响应时间
    private int trans_count;        //调用次数
    private int is_errorResponse;   //错误次数
    private int is_fastResponse;    //满意次数
    private int is_tolerableResponse;   //容忍次数
    private int is_slowResponse;    //缓慢次数

    public String getAgent() {
        return Agent;
    }

    public void setAgent(String agent) {
        Agent = agent;
    }

    public String getTier() {
        return Tier;
    }

    public void setTier(String tier) {
        Tier = tier;
    }

    public String getEntranceService() {
        return entranceService;
    }

    public void setEntranceService(String entranceService) {
        this.entranceService = entranceService;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getWebService() {
        return webService;
    }

    public void setWebService(String webService) {
        this.webService = webService;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public String getComponentName() {
        return componentName;
    }

    public void setComponentName(String componentName) {
        this.componentName = componentName;
    }

    public double getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(double responseTime) {
        this.responseTime = responseTime;
    }

    public double getMaxRespTime() {
        return maxRespTime;
    }

    public void setMaxRespTime(double maxRespTime) {
        this.maxRespTime = maxRespTime;
    }

    public double getMinRespTime() {
        return minRespTime;
    }

    public void setMinRespTime(double minRespTime) {
        this.minRespTime = minRespTime;
    }

    public int getTrans_count() {
        return trans_count;
    }

    public void setTrans_count(int trans_count) {
        this.trans_count = trans_count;
    }

    public int getIs_errorResponse() {
        return is_errorResponse;
    }

    public void setIs_errorResponse(int is_errorResponse) {
        this.is_errorResponse = is_errorResponse;
    }

    public int getIs_fastResponse() {
        return is_fastResponse;
    }

    public void setIs_fastResponse(int is_fastResponse) {
        this.is_fastResponse = is_fastResponse;
    }

    public int getIs_tolerableResponse() {
        return is_tolerableResponse;
    }

    public void setIs_tolerableResponse(int is_tolerableResponse) {
        this.is_tolerableResponse = is_tolerableResponse;
    }

    public int getIs_slowResponse() {
        return is_slowResponse;
    }

    public void setIs_slowResponse(int is_slowResponse) {
        this.is_slowResponse = is_slowResponse;
    }

    @Override
    public String toString() {
        return "ApmTrans{" +
                "Agent='" + Agent + '\'' +
                ", Tier='" + Tier + '\'' +
                ", entranceService='" + entranceService + '\'' +
                ", language='" + language + '\'' +
                ", webService='" + webService + '\'' +
                ", startTime=" + startTime +
                ", componentName='" + componentName + '\'' +
                ", responseTime=" + responseTime +
                ", maxRespTime=" + maxRespTime +
                ", minRespTime=" + minRespTime +
                ", trans_count=" + trans_count +
                ", is_errorResponse=" + is_errorResponse +
                ", is_fastResponse=" + is_fastResponse +
                ", is_tolerableResponse=" + is_tolerableResponse +
                ", is_slowResponse=" + is_slowResponse +
                '}';
    }
}
