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
        Cluster.Builder builder1 = Cluster.builder().addContactPoint("127.0.0.1");
        Cluster cluster = builder1.build();
        this.session = cluster.connect();
//        String query = "CREATE KEYSPACE tp WITH replication "
//                + "= {'class':'SimpleStrategy', 'replication_factor':1}; ";
//        session.execute(query);
    }

    @Test
    public void createTable(){
        String query = "CREATE TABLE tp.emp(emp_id int PRIMARY KEY, "
                + "emp_name text, "
                + "emp_city text, "
                + "emp_sal varint, "
                + "emp_phone varint );";
        session.execute(query);
    }
}
