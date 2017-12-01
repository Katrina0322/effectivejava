package com.oneapm.vo;

import java.util.List;

/**
 * filename: ConfigVo
 * Description:
 * Author: ubuntu
 * Date: 12/1/17 4:31 PM
 */
public class ConfigVo {
    private String master;  // so was namenode
    private List<String> slaves; // so was datanodes
    private List<String> kafkaNodes; // kafka集群节点
    private List<String> zookeepers;  // size maybe 3 or 5
    private List<String> elasticNodes; //es集群节点

    public ConfigVo() {
    }

    public String getMaster() {
        return master;
    }

    public void setMaster(String master) {
        this.master = master;
    }

    public List<String> getSlaves() {
        return slaves;
    }

    public void setSlaves(List<String> slaves) {
        this.slaves = slaves;
    }

    public List<String> getKafkaNodes() {
        return kafkaNodes;
    }

    public void setKafkaNodes(List<String> kafkaNodes) {
        this.kafkaNodes = kafkaNodes;
    }

    public List<String> getZookeepers() {
        return zookeepers;
    }

    public void setZookeepers(List<String> zookeepers) {
        this.zookeepers = zookeepers;
    }

    public List<String> getElasticNodes() {
        return elasticNodes;
    }

    public void setElasticNodes(List<String> elasticNodes) {
        this.elasticNodes = elasticNodes;
    }
}
