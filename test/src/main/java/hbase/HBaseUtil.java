package hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @description: HBASE
 * @author: tianjin
 * @email: eternity_bliss@sina.cn
 * @create: 2018-06-26 下午2:06
 */
public class HBaseUtil {
    private static Connection conn = null;

    private void init() throws IOException {
        Configuration configuration = HBaseConfiguration.create();
        configuration.set("hbase.client.ipc.pool.type", "Reusable");
        configuration.set("hbase.client.ipc.pool.size","20");
        configuration.set("hbase.zookeeper.quorum", "");
        configuration.set("zookeeper.znode.parent", "/hbase");
        conn = ConnectionFactory.createConnection(configuration);
    }

    public void insert(String tableName, String row, String columnFamily,String column,String data) throws IOException {
        Table table = conn.getTable(TableName.valueOf(tableName));
        Put put = new Put(Bytes.toBytes(row));
        put.addColumn(Bytes.toBytes(columnFamily),Bytes.toBytes(column),Bytes.toBytes(data));
        table.put(put);
    }

    public void multInsert(String tableName, List<Put> putList) throws IOException {
        Table table = conn.getTable(TableName.valueOf(tableName));
        table.put(putList);
    }

    public void delete(String tableName, String rowKey) throws IOException {
        Table table = conn.getTable(TableName.valueOf(tableName));
        Delete delete = new Delete(Bytes.toBytes(rowKey));
        table.delete(delete);
    }

    public byte[] getColumn(String tableName, String rowKey, String columnFamily,String column) throws IOException {
        Table table = conn.getTable(TableName.valueOf(tableName));
        Get get = new Get(Bytes.toBytes(rowKey));
        get.addColumn(Bytes.toBytes(columnFamily), Bytes.toBytes(column));
        Result result = table.get(get);
        return result.listCells().get(0).getValueArray();
    }

    public void multGetColumn(String tableName, List<String> keys, String columnFamily,String column) throws IOException {
        Table table = conn.getTable(TableName.valueOf(tableName));
        List<Get> getList = new ArrayList<>(keys.size());
        for(String key:keys){
            Get get = new Get(Bytes.toBytes(key));
            get.addColumn(Bytes.toBytes(columnFamily), Bytes.toBytes(column));
            getList.add(get);
        }
        Result[] results = table.get(getList);

    }


}
