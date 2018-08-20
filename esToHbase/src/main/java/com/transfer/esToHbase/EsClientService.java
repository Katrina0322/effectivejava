package com.transfer.esToHbase;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.nio.reactor.IOReactorConfig;
import org.apache.http.nio.entity.NStringEntity;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.sniff.Sniffer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @description: es client
 * @author: tianjin
 * @email: eternity_bliss@sina.cn
 * @create: 2018-07-17 下午7:24
 */
@Service
public class EsClientService {

    private static final Logger logger = LoggerFactory.getLogger(EsClientService.class);

    @Value("${elasticsearch.clusterName}")
    private String clusterName;

    private RestClient restClient;

    private static Map<String, String> params = new HashMap<>();

    @Value("${elasticsearch.hosts}")
    private String hosts;

    static {
        params.put("pretty", "true");
        params.put("ignore_unavailable", "true");
    }

    @PostConstruct
    public void init(){
        String[] hostsArray = hosts.split(",");
        HttpHost[] httpHosts = new HttpHost[hostsArray.length];
        for(int i = 0; i < hostsArray.length; i++){
            String[] ipPort = hostsArray[i].split(":");
            HttpHost httpHost = new HttpHost(ipPort[0], Integer.parseInt(ipPort[1]), "http");
            httpHosts[i] = httpHost;
        }

        restClient = RestClient.builder(httpHosts)
                .setRequestConfigCallback(requestConfigBuilder -> requestConfigBuilder.setConnectTimeout(5000).setSocketTimeout(60000))
                .setHttpClientConfigCallback(httpAsyncClientBuilder -> httpAsyncClientBuilder.setDefaultIOReactorConfig(IOReactorConfig.custom().setIoThreadCount(Runtime.getRuntime().availableProcessors()).build()))
                .setMaxRetryTimeoutMillis(60000)
                .build();
        Sniffer sniffer = Sniffer.builder(restClient).build();
    }


    public String queryES(String method, String endpoint, String query, Header... headers) throws IOException {
        HttpEntity entity = new NStringEntity(query, ContentType.APPLICATION_JSON);

        logger.info("es请求索引:" + endpoint + ", 请求体:" + query);

        Response response = restClient.performRequest(method, endpoint, params, entity, headers);

        String content = IOUtils.toString(response.getEntity().getContent(), "UTF-8");
        logger.info("response content:" + content);
        return content;
    }

    public void curd(String method, String endpoint, String query, Header... headers) throws IOException {
        HttpEntity entity = new NStringEntity(query, ContentType.APPLICATION_JSON);
        Response response = restClient.performRequest(method, endpoint, Collections.emptyMap(), entity, headers);
        String content = IOUtils.toString(response.getEntity().getContent(), "UTF-8");
        logger.info("response content:" + content);
    }
}