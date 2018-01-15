package es;

import java.io.Serializable;
import java.util.Date;

/**
 * description:
 * Created by spark on 17-7-11.
 */
public class TransInfoBean implements Serializable{
    private static final long serialVersionUID = -192906877480559050L;
    private String app_customer; //自定义服务
    private String transaction;
    private String server_ip;
    private String sender;
    private String recipient;
    private String channel;
    private String organ;
    private String business_name;

    private String user_name;
    private String transaction_id;
    private String sub_transaction_id;
    private int error_page_count;
    private int http_count;
    private int trans_count;
    private int tradecode_system_error_count;
    private int error_type_success_flag;
    private int error_return_code_count;
    private int all_return_code_count;
    private double total_page_load_time;
    private int total_page_load_time_count;
    private int total_trans_process_time_count;
    private double total_trans_process_time;
    private Date start_timestamp; //链接开始时间
    private int days_count;

    public TransInfoBean() {
    }

    public String getTransaction() {
        return transaction;
    }

    public void setTransaction(String transaction) {
        this.transaction = transaction;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(String transaction_id) {
        this.transaction_id = transaction_id;
    }

    public int getError_page_count() {
        return error_page_count;
    }

    public void setError_page_count(int error_page_count) {
        this.error_page_count = error_page_count;
    }

    public int getHttp_count() {
        return http_count;
    }

    public void setHttp_count(int http_count) {
        this.http_count = http_count;
    }

    public int getError_return_code_count() {
        return error_return_code_count;
    }

    public void setError_return_code_count(int error_return_code_count) {
        this.error_return_code_count = error_return_code_count;
    }

    public int getAll_return_code_count() {
        return all_return_code_count;
    }

    public void setAll_return_code_count(int all_return_code_count) {
        this.all_return_code_count = all_return_code_count;
    }

    public double getTotal_page_load_time() {
        return total_page_load_time;
    }

    public void setTotal_page_load_time(double total_page_load_time) {
        this.total_page_load_time = total_page_load_time;
    }

    public int getTotal_page_load_time_count() {
        return total_page_load_time_count;
    }

    public String getApp_customer() {
        return app_customer;
    }

    public void setApp_customer(String app_customer) {
        this.app_customer = app_customer;
    }

    public Date getStart_timestamp() {
        return start_timestamp;
    }

    public void setStart_timestamp(Date start_timestamp) {
        this.start_timestamp = start_timestamp;
    }

    public double getTotal_trans_process_time() {
        return total_trans_process_time;
    }

    public void setTotal_trans_process_time(double total_trans_process_time) {
        this.total_trans_process_time = total_trans_process_time;
    }

    public int getTotal_trans_process_time_count() {
        return total_trans_process_time_count;
    }

    public void setTotal_trans_process_time_count(int total_trans_process_time_count) {
        this.total_trans_process_time_count = total_trans_process_time_count;
    }

    public void setTotal_page_load_time_count(int total_page_load_time_count) {
        this.total_page_load_time_count = total_page_load_time_count;
    }

    public String getSub_transaction_id() {
        return sub_transaction_id;
    }

    public void setSub_transaction_id(String sub_transaction_id) {
        this.sub_transaction_id = sub_transaction_id;
    }

    public int getDays_count() {
        return days_count;
    }

    public void setDays_count(int days_count) {
        this.days_count = days_count;
    }

    public String getServer_ip() {
        return server_ip;
    }

    public void setServer_ip(String server_ip) {
        this.server_ip = server_ip;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getOrgan() {
        return organ;
    }

    public void setOrgan(String organ) {
        this.organ = organ;
    }

    public int getTradecode_system_error_count() {
        return tradecode_system_error_count;
    }

    public void setTradecode_system_error_count(int tradecode_system_error_count) {
        this.tradecode_system_error_count = tradecode_system_error_count;
    }

    public int getError_type_success_flag() {
        return error_type_success_flag;
    }

    public void setError_type_success_flag(int error_type_success_flag) {
        this.error_type_success_flag = error_type_success_flag;
    }

    public String getBusiness_name() {
        return business_name;
    }

    public void setBusiness_name(String business_name) {
        this.business_name = business_name;
    }

    public int getTrans_count() {
        return trans_count;
    }

    public void setTrans_count(int trans_count) {
        this.trans_count = trans_count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TransInfoBean that = (TransInfoBean) o;

        if (error_page_count != that.error_page_count) return false;
        if (http_count != that.http_count) return false;
        if (trans_count != that.trans_count) return false;
        if (tradecode_system_error_count != that.tradecode_system_error_count) return false;
        if (error_type_success_flag != that.error_type_success_flag) return false;
        if (error_return_code_count != that.error_return_code_count) return false;
        if (all_return_code_count != that.all_return_code_count) return false;
        if (Double.compare(that.total_page_load_time, total_page_load_time) != 0) return false;
        if (total_page_load_time_count != that.total_page_load_time_count) return false;
        if (total_trans_process_time_count != that.total_trans_process_time_count) return false;
        if (Double.compare(that.total_trans_process_time, total_trans_process_time) != 0) return false;
        if (days_count != that.days_count) return false;
        if (app_customer != null ? !app_customer.equals(that.app_customer) : that.app_customer != null) return false;
        if (transaction != null ? !transaction.equals(that.transaction) : that.transaction != null) return false;
        if (server_ip != null ? !server_ip.equals(that.server_ip) : that.server_ip != null) return false;
        if (sender != null ? !sender.equals(that.sender) : that.sender != null) return false;
        if (recipient != null ? !recipient.equals(that.recipient) : that.recipient != null) return false;
        if (channel != null ? !channel.equals(that.channel) : that.channel != null) return false;
        if (organ != null ? !organ.equals(that.organ) : that.organ != null) return false;
        if (business_name != null ? !business_name.equals(that.business_name) : that.business_name != null)
            return false;
        if (user_name != null ? !user_name.equals(that.user_name) : that.user_name != null) return false;
        if (transaction_id != null ? !transaction_id.equals(that.transaction_id) : that.transaction_id != null)
            return false;
        if (sub_transaction_id != null ? !sub_transaction_id.equals(that.sub_transaction_id) : that.sub_transaction_id != null)
            return false;
        return start_timestamp != null ? start_timestamp.equals(that.start_timestamp) : that.start_timestamp == null;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = app_customer != null ? app_customer.hashCode() : 0;
        result = 31 * result + (transaction != null ? transaction.hashCode() : 0);
        result = 31 * result + (server_ip != null ? server_ip.hashCode() : 0);
        result = 31 * result + (sender != null ? sender.hashCode() : 0);
        result = 31 * result + (recipient != null ? recipient.hashCode() : 0);
        result = 31 * result + (channel != null ? channel.hashCode() : 0);
        result = 31 * result + (organ != null ? organ.hashCode() : 0);
        result = 31 * result + (business_name != null ? business_name.hashCode() : 0);
        result = 31 * result + (user_name != null ? user_name.hashCode() : 0);
        result = 31 * result + (transaction_id != null ? transaction_id.hashCode() : 0);
        result = 31 * result + (sub_transaction_id != null ? sub_transaction_id.hashCode() : 0);
        result = 31 * result + error_page_count;
        result = 31 * result + http_count;
        result = 31 * result + trans_count;
        result = 31 * result + tradecode_system_error_count;
        result = 31 * result + error_type_success_flag;
        result = 31 * result + error_return_code_count;
        result = 31 * result + all_return_code_count;
        temp = Double.doubleToLongBits(total_page_load_time);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + total_page_load_time_count;
        result = 31 * result + total_trans_process_time_count;
        temp = Double.doubleToLongBits(total_trans_process_time);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (start_timestamp != null ? start_timestamp.hashCode() : 0);
        result = 31 * result + days_count;
        return result;
    }

    @Override
    public String toString() {
        return "TransInfoBean{" +
                "app_customer='" + app_customer + '\'' +
                ", transaction='" + transaction + '\'' +
                ", server_ip='" + server_ip + '\'' +
                ", sender='" + sender + '\'' +
                ", recipient='" + recipient + '\'' +
                ", channel='" + channel + '\'' +
                ", organ='" + organ + '\'' +
                ", business_name='" + business_name + '\'' +
                ", user_name='" + user_name + '\'' +
                ", transaction_id='" + transaction_id + '\'' +
                ", sub_transaction_id='" + sub_transaction_id + '\'' +
                ", error_page_count=" + error_page_count +
                ", http_count=" + http_count +
                ", trans_count=" + trans_count +
                ", tradecode_system_error_count=" + tradecode_system_error_count +
                ", error_type_success_flag=" + error_type_success_flag +
                ", error_return_code_count=" + error_return_code_count +
                ", all_return_code_count=" + all_return_code_count +
                ", total_page_load_time=" + total_page_load_time +
                ", total_page_load_time_count=" + total_page_load_time_count +
                ", total_trans_process_time_count=" + total_trans_process_time_count +
                ", total_trans_process_time=" + total_trans_process_time +
                ", start_timestamp=" + start_timestamp +
                ", days_count=" + days_count +
                '}';
    }
}
