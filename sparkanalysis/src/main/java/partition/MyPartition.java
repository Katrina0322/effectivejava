package partition;

import org.apache.spark.Partitioner;

/**
 * @filename: MyPartition
 * @Description:
 * @Author: ubuntu
 * @Date: 12/23/16 5:28 PM
 */
public class MyPartition extends Partitioner {

    private int partitions = 10;

    public MyPartition(int partitions) {
        this.partitions = partitions;
    }

    @Override
    public int numPartitions() {
        return 0;
    }

    @Override
    public int getPartition(Object key) {
        int partition = key.hashCode();
        return partition % partitions < 0 ? partition + partitions : partition;
    }
}
