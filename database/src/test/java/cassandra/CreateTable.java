package cassandra;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import org.junit.Before;
import org.junit.Test;

/**
 * filename: CreateTable
 * Description:
 * Author: ubuntu
 * Date: 1/29/18 5:48 PM
 */
public class CreateTable {
    private Session session;
    @Before
    public void init(){
        Cluster.Builder builder1 = Cluster.builder().addContactPoint("10.128.9.207");
        Cluster cluster = builder1.build();
        this.session = cluster.connect( );
    }

    @Test
    public void createTable(){

    }
}
