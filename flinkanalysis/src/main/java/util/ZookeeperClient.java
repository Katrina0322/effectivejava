package util;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.imps.CuratorFrameworkState;
import org.apache.curator.framework.recipes.cache.*;
import org.apache.curator.framework.state.ConnectionStateListener;
import org.apache.curator.retry.RetryNTimes;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

import java.io.Closeable;
import java.io.Serializable;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * filename: ZookeeperClient
 * Description: Thread safe with public methods such as getData, isExist...
 * Author: ubuntu
 * Date: 12/13/17 6:40 PM
 */
public class ZookeeperClient implements Serializable, Closeable {
    private static final long serialVersionUID = 2594876332205429555L;
    private static volatile Lock lock = new ReentrantLock();
    //整个应用唯一
    private static volatile CuratorFramework client;

    private ZookeeperClient() {
    }

    public static ZookeeperClient getInstance() {
        return LazyHolder.INSTANCE;
    }

    /**
     * create Persistent node
     * if node exist, throw exception
     *
     * @param path
     * @param payload
     * @return
     */
    public String createPersistentNode(String path, byte[] payload) throws Exception {
        return createNode(path, payload, false);
    }

    /**
     * create Ephemeral node
     * if node exist, throw exception
     *
     * @param path
     * @param payload
     * @return
     */
    public String createEphemeralNode(String path, byte[] payload) throws Exception {
        return createNode(path, payload, true);
    }


    /**
     * if node not exist, throw exception
     *
     * @param path
     * @throws Exception
     */
    public void deleteNode(String path) throws Exception {
        lock.lock();
        try {
            checkClient();
            client.delete().guaranteed().forPath(path);
        } finally {
            lock.unlock();
        }
    }

    /**
     * if node exist, return true
     *
     * @param path
     * @return
     */
    public boolean isExist(String path) {
        Stat stat;
        lock.lock();
        checkClient();
        try {
            stat = client.checkExists().forPath(path);
        } catch (Exception e) {
            stat = null;
        } finally {
            lock.unlock();
        }
        return stat != null;
    }

    /**
     * get data
     * if node not exist, throw exception
     *
     * @param path
     * @return
     * @throws Exception
     */
    public String getData(String path) throws Exception {
        byte[] data = new byte[0];
        lock.lock();
        checkClient();
        try {
            data = client.getData().forPath(path);
        } finally {
            lock.unlock();
        }
        return new String(data);
    }

    /**
     * set data
     * @param path
     * @param data
     * @return
     * @throws Exception
     */
    public Stat setData(String path, String data) throws Exception {
        lock.lock();
        checkClient();
        try {
            return client.setData().forPath(path, data.getBytes());
        } finally {
            lock.unlock();
        }
    }

    public List<String> getChildren(String path) throws Exception {
        lock.lock();
        checkClient();
        try {
            return client.getChildren().forPath(path);
        } finally {
            lock.unlock();
        }
    }


    public void addConnectionStateListener(ConnectionStateListener listener, Executor service){
        lock.lock();
        checkClient();
        client.getConnectionStateListenable().addListener(listener, service);
        lock.unlock();
    }

    /**
     * add watch for path whether node is exist
     * @param path
     * @throws Exception
     */
    public void addNodeCacheListener(String path, NodeCacheListener listener, Executor service) throws Exception {
        lock.lock();
        checkClient();
        NodeCache nodeCache = new NodeCache(client, path);
        try {
            nodeCache.start();
            nodeCache.getListenable().addListener(listener, service);
        }finally {
            lock.unlock();
        }
    }

    public void addPathChildrenCacheListener(String path, PathChildrenCacheListener listener, Executor service) throws Exception {
        lock.lock();
        checkClient();
        PathChildrenCache childrenCache = new PathChildrenCache(client, path, true);
        try {
            childrenCache.start(PathChildrenCache.StartMode.POST_INITIALIZED_EVENT);
            childrenCache.getListenable().addListener(listener, service);
        }finally {
            lock.unlock();
        }
    }

    @Override
    public void close() {
        lock.lock();
        try {
            if (client != null) {
                client.close();
            }
            client = null;
        } finally {
            lock.unlock();
        }

    }

    private void checkClient() {
        if (client == null || client.getState() == CuratorFrameworkState.STOPPED) {
            createClient();
        }
    }

    private void createClient() {
        RetryPolicy retryPolicy = new RetryNTimes(6, 5000);
        client = CuratorFrameworkFactory.newClient("10.128.9.207:2181", retryPolicy);
        client.start();
    }

    private String createNode(String path, byte[] payload, boolean ephemeral) throws Exception {
        String result = null;
        lock.lock();
        checkClient();
        try {
            if (ephemeral)
                result = client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL).forPath(path, payload);
            else
                result = client.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT).forPath(path, payload);
        } finally {
            lock.unlock();
        }
        return result;
    }

    private static class LazyHolder {
        private static final ZookeeperClient INSTANCE = new ZookeeperClient();
    }

}
