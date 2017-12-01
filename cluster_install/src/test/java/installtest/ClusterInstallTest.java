package installtest;

import com.oneapm.ClusterInstallApplication;
import com.oneapm.controller.ClusterInstall;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * filename: ClusterInstallTest
 * Description:
 * Author: ubuntu
 * Date: 12/1/17 3:31 PM
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ClusterInstallApplication.class)
public class ClusterInstallTest {
    @Autowired
    private ClusterInstall clusterInstall;

    @Test
    public void getHost(){
        String host1 = clusterInstall.getHost("203.208.46.146");
        Assert.assertEquals("www.google.com", host1);

        String host2 = clusterInstall.getHost("64.233.162.81");
        Assert.assertEquals("www.google.com.gr", host2);
    }
}
