package com.transfer.esToHbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;

/**
 * @description:
 * @author: tianjin
 * @email: eternity_bliss@sina.cn
 * @create: 2018-07-19 下午5:03
 */
@Service
public class HBaseClientService {

    private static final Logger logger = LoggerFactory.getLogger(HBaseClientService.class);

    @Value("${zookeeper.quorum}")
    private String zoo;

    @Value(("${zookeeper.clientPort}"))
    private String clientPort;

    private Connection conn;

    @PostConstruct
    private void init() throws IOException {
        Configuration configuration = HBaseConfiguration.create();
        configuration.set("hbase.client.ipc.pool.type", "Reusable");
        configuration.set("hbase.client.ipc.pool.size","20");
        configuration.set("hbase.zookeeper.quorum", zoo);
        configuration.set("hbase.zookeeper.property.clientPort", clientPort);
//        configuration.set("zookeeper.znode.parent", "/hbase");
        conn = ConnectionFactory.createConnection(configuration);
    }


    public void insert(String tableName, List<Put> putList) throws IOException {
        Table table = getTable(tableName);
        table.put(putList);
        logger.info("写入成功：" + putList.size());
    }

    public void insert(String tableName, Put put) throws IOException {
        Table table = getTable(tableName);
        table.put(put);
    }

    public Result getValue(String tableName, String key, String family, String column) throws IOException {
        Table table = getTable(tableName);
        Get get = new Get(key.getBytes());
        get.addColumn(family.getBytes(), column.getBytes());
        return table.get(get);
    }

    public Result getFamily(String tableName, String key) throws IOException {
        Table table = getTable(tableName);
        return table.get(new Get(key.getBytes()));
    }

    private Table getTable(String tableName) throws IOException {
        if(conn == null) init();
        TableName name = TableName.valueOf(tableName);
        return conn.getTable(name);
    }

}
