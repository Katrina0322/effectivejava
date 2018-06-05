package es;

import com.alibaba.fastjson.JSON;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.net.InetSocketAddress;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * used to get data from ES and change it to List or Map
 * Created by tianjin on 4/18/16.
 */
public class EsGet implements Serializable {

    private static final Logger LOG = LoggerFactory.getLogger(EsGet.class);
    private static final long serialVersionUID = 5148318684360301354L;

    private static SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd", Locale.CHINA);

    private Client client;

    private EsGet() {
    }

    public static EsGet getInstance() {
        return LazyHolder.INSTANCE;
    }

    public static void main(String[] args) throws ParseException {


        SimpleDateFormat esFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
        Calendar now = Calendar.getInstance();

        now.setTime(esFormat.parse(esFormat.format(new Date())));
        now.setTime(esFormat.parse(esFormat.format(new Date())));
        now.set(Calendar.SECOND, 0);

        now.add(Calendar.MINUTE, -15);
        int minute = now.get(Calendar.MINUTE);

        if (minute % 5 != 0) {
            now.set(Calendar.MINUTE, minute / 5 * 5);
        }
        EsGet esGet = EsGet.getInstance();
        if (esGet.getClient() == null) {
            esGet.setNodeClient();
        }
//        List<HttpInfoBeanJ> value = esGet.connectES(now.getTime(), "http-stat-session-" + format.format(now.getTime()), "noparam_page", HttpInfoBeanJ.class);
//        System.out.println(value.get(0).toString());

////        List<HttpInfoBeanJ> value3 = EsGet.getInstance().connectES(calendar.getTime(), "http-stat-session-2017.02.13", "noparam_page", HttpInfoBeanJ.class);
////        System.out.println(value3.get(0).toString());
//
//        List<HostBean> value2 = EsGet.getInstance().connectES(now.getTime(), "ni-base-hostname", "base", HostBean.class);

//        System.out.println(value2.get(0).toString());

//        Map<ServiceKey, BaseData> service = esGet.setService(now.getTime(), "ni-base-protocal_type");
//        System.out.println(service.size());

        System.out.println(now.getTime());
        esGet.connectES(now.getTime(), "ns_news_v1", "newsIndex", JSON.class);
//        System.out.println(transInfo.size());
    }

    public Client getClient() {
        return client;
    }

    /**
     * init the ES client with version 5.2.0
     */
    public void setNodeClient() {

        String clusterName = "dev_inews2";

        String host = "10.45.32.166";

        Settings settings = Settings.builder()
                .put("cluster.name", clusterName)
                .put("client.transport.ignore_cluster_name", false)
//                .put("node.client", true)
//                .put("client.transport.sniff", true)
                .build();

        client = new PreBuiltTransportClient(settings).addTransportAddress(new InetSocketTransportAddress(new InetSocketAddress(host, 9500)));
    }



    public List<TransInfoBean> getTransFromEs(Date timestamp, String index) {
        if (client == null) {
            setNodeClient();
        }
        return connectES(timestamp, index + format.format(timestamp), "business", TransInfoBean.class);
    }

    /**
     * get data from ES with giving index,type,and timestamp
     *
     * @param timestamp
     * @param index
     * @param type
     * @param clazz
     * @param <T>
     * @return
     */
    private <T> ArrayList<T> connectES(Date timestamp, String index, String type, Class<T> clazz) {
        return connectES(timestamp, index, type, "", "", clazz);
    }

    public <T> ArrayList<T> connectES(Date timestamp, String index, String type, String key, String val, Class<T> clazz) {
        if (client == null) {
            setNodeClient();
        }
        ArrayList<T> list = new ArrayList<>();
        if (!client.admin().indices().prepareExists(index).execute().actionGet().isExists()) {
            LOG.warn("index " + index + " not exist");
            return new ArrayList<>();
        }

        DateTime start = new DateTime(timestamp);
        DateTime end = start.plusMinutes(1);
        SearchResponse scrollResp;
        if (!"".equals(key) && !"".equals(val)) {
            scrollResp = client.prepareSearch(index)
                    .setTypes(type).setSearchType(SearchType.QUERY_THEN_FETCH)
                    .setPostFilter(QueryBuilders.rangeQuery("start_timestamp").from(start).to(end,false))
                    .setQuery(QueryBuilders.boolQuery().must(QueryBuilders.matchQuery(key, val)))
//                .setQuery(QueryBuilders.boolQuery().must(QueryBuilders.matchQuery("start_timestamp_double", timestamp.getTime() / 1000)))
                    .setScroll(new TimeValue(60000))
                    .setSize(10000).execute().actionGet();
        } else {
            scrollResp = client.prepareSearch(index)
                    .setTypes(type).setSearchType(SearchType.QUERY_THEN_FETCH)
//                .setQuery(QueryBuilders.boolQuery().must(QueryBuilders.matchQuery("start_timestamp_double", timestamp.getTime() / 1000)).must(QueryBuilders.matchQuery(key,val)))
                    .setPostFilter(QueryBuilders.rangeQuery("start_timestamp").from(start).to(end,false))
                    .setScroll(new TimeValue(60000))
                    .setSize(10000).execute().actionGet();
        }

//        client.prepareSearch(index).setTypes(type).setSearchType(SearchType.QUERY_THEN_FETCH).setPostFilter(QueryBuilders.rangeQuery("start_timestamp_double").from(timestamp.getTime()).to(timestamp.getTime() + 60,false))
//                .setScroll(new TimeValue(60000))
//                .setSize(10000).execute().actionGet();

//        LOG.debug("Get total hit at time: " + timestamp + ",total hits " + scrollResp.getHits().getTotalHits());

        while (true) {
            for (SearchHit hit : scrollResp.getHits().getHits()) {
                T value = JSON.parseObject(hit.sourceAsString(), clazz);
                list.add(value);
            }
            scrollResp = client.prepareSearchScroll(scrollResp.getScrollId()).setScroll(new TimeValue(60000)).execute
                    ().actionGet();
            //Break condition: No hits are returned
            if (scrollResp.getHits().getHits().length == 0) {
                break;
            }
        }
        return list;
    }


    private static class LazyHolder implements Serializable {
        private static final EsGet INSTANCE = new EsGet();
    }

}
