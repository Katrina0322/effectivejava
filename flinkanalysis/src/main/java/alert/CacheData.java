package alert;

import com.alibaba.fastjson.JSONObject;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.framework.state.ConnectionStateListener;
import util.ZookeeperClient;

import java.util.List;
import java.util.concurrent.Executor;

/**
 * filename: CacheData
 * Description:
 * Author: ubuntu
 * Date: 12/27/17 3:36 PM
 */
public class CacheData {

    private volatile List<Rule> rules;

    private ZookeeperClient zookeeperClient = ZookeeperClient.getInstance();

    public CacheData() {
    }

    public List<Rule> getRules() {
        return rules;
    }

    private void setRules(String path) throws Exception {
        String data = zookeeperClient.getData(path);
        this.rules = JSONObject.parseArray(data, Rule.class);
    }

    public void init(Executor executor) throws Exception {
        setRules("/ni/caution");
        addRuleListener("/ni/caution", executor);
        zookeeperClient.addConnectionStateListener(new ConnectionStateListener() {
            @Override
            public void stateChanged(CuratorFramework curatorFramework, ConnectionState connectionState) {
                if(connectionState == ConnectionState.LOST){
                    while (true){
                        try {
                            if(curatorFramework.getZookeeperClient().blockUntilConnectedOrTimedOut()){
                                zookeeperClient.close();
                                init(executor);
                                break;
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            break;
                        }
                    }
                }
            }
        }, executor);
    }

    private void addRuleListener(String path, Executor executor) throws Exception {
        zookeeperClient.addNodeCacheListener(path, new NodeCacheListener() {
            @Override
            public void nodeChanged() throws Exception {
               setRules(path);
            }
        }, executor);
    }
}
