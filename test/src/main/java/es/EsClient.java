package es;

import org.apache.commons.io.IOUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.entity.ContentType;
import org.apache.http.nio.entity.NStringEntity;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @description: restful es query
 * @author: tianjin
 * @email: eternity_bliss@sina.cn
 * @create: 2018-06-27 下午3:07
 */
public class EsClient {
    private final static Logger logger = LoggerFactory.getLogger(EsClient.class);
    private RestClient restClient;
    private static Map<String, String> params = new HashMap<>();

//    @Value("${elasticsearch.hosts}")
    private String hosts;

    static {
        params.put("pretty", "true");
        params.put("ignore_unavailable", "true");
    }

//    @PostConstruct
    public void init(){
        String[] hostsArray = hosts.split(",");
        HttpHost[] httpHosts = new HttpHost[hostsArray.length];
        for(int i = 0; i < hostsArray.length; i++){
            String[] ipPort = hostsArray[i].split(":");
            HttpHost httpHost = new HttpHost(ipPort[0], Integer.parseInt(ipPort[1]), "http");
            httpHosts[i] = httpHost;
        }
        restClient = RestClient.builder(httpHosts).setMaxRetryTimeoutMillis(60000).build();
    }

    /**
     * query from elastic
     * @param method httpMethod
     * @param endpoint index and operate such as _search
     * @param query es query
     * @param trans TransFunction impl
     * @param headers HTTP HEADER
     * @param <T> result class
     * @return List T
     * @throws IOException
     */
    public <T> T queryES(String method, String endpoint, String query, TransFunction<String, T> trans, Header... headers) throws IOException {
        HttpEntity entity = new NStringEntity(query, ContentType.APPLICATION_JSON);
        if(logger.isDebugEnabled()) {
            logger.debug("es请求索引:" + endpoint + ", 请求体:" + query);
        }
        Response response = restClient.performRequest(method, endpoint, params, entity, headers);
        return transResponse(trans, response);
    }

    /**
     * response to result list
     * @param trans  TransFunction impl
     * @param response  org.elasticsearch.client.Response
     * @param <T> result class
     * @return
     * @throws IOException
     */
    private <T> T transResponse(TransFunction<String, T> trans, Response response) throws IOException {
        String source = IOUtils.toString(response.getEntity().getContent(), "UTF-8");
//        logger.info("查询结果：" + source);
        return trans.transfer(source);
    }
}
