package com.oneapm.pojo;

import java.util.List;

/**
 * filename: Elastic
 * Description:
 * Author: ubuntu
 * Date: 11/13/17 10:51 AM
 */
public class Elastic {
    private List<String> nodeNames;
    private String clusterName;
    private String host = "localhost";
    private int port = 9200;
    private int numberOfReplicas;
    private List<String> unicastHosts;

}
