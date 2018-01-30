package sort;

import com.effective.hermes.core.IColumn;

import java.util.Date;

/**
 * filename: DefaultColumn
 * Description:
 * Author: ubuntu
 * Date: 1/30/18 11:11 AM
 */
public class DefaultColumn implements IColumn{
    private byte[] columnName;
    private byte[] columnValue;
    private long timestamp;
    private Type type;

    public DefaultColumn(String columnName, String columnValue) {
        this(columnName, columnValue, new Date().getTime());
    }

    public DefaultColumn(String columnName, String columnValue, long timestamp) {
        this(columnName, columnValue, timestamp, Type.Put);
    }

    public DefaultColumn(String columnName, String columnValue, Type type) {
        this(columnName, columnValue, new Date().getTime(), type);
    }

    public DefaultColumn(String columnName, String columnValue, long timestamp, Type type) {
        this.columnName = columnName.getBytes();
        this.columnValue = columnValue.getBytes();
        this.timestamp = timestamp;
        this.type = type;
    }

    @Override
    public byte[] getColumnName() {
        return columnName;
    }

    @Override
    public byte[] getColumnValue() {
        return columnValue;
    }

    @Override
    public long getTimestamp() {
        return timestamp;
    }

    @Override
    public Type getType() {
        return type;
    }

    @Override
    public int compareTo(IColumn o) {
        return 0;
    }

    @Override
    public long heapSize() {
        return 0;
    }


}
