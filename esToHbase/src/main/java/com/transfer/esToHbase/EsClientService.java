package com.transfer.esToHbase;

import com.sun.scenario.Settings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import java.net.InetSocketAddress;

/**
 * @description:
 * @author: tianjin
 * @email: eternity_bliss@sina.cn
 * @create: 2018-07-19 下午5:03
 */

public class EsClientService {

    private static final Logger logger = LoggerFactory.getLogger(EsClientService.class);

    private String clusterName;

    private String hosts;

    @PostConstruct
    public void init(){
        Settings settings = Settings.builder()
                .put("cluster.name", clusterName)
                .put("client.transport.ignore_cluster_name", false)
                .put("client.transport.sniff", true)
                .build();

        client = new PreBuiltTransportClient(settings).addTransportAddress(new InetSocketTransportAddress(new InetSocketAddress(host, 9500)));

    }
}
