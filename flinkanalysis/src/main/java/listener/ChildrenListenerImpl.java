package listener;

import alert.CacheData;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;


/**
 * filename: ChildrenListenerImpl
 * Description:
 * Author: ubuntu
 * Date: 12/27/17 4:03 PM
 */
public class ChildrenListenerImpl implements PathChildrenCacheListener{
    private CacheData cacheData;

    @Override
    public void childEvent(CuratorFramework curatorFramework, PathChildrenCacheEvent pathChildrenCacheEvent) throws Exception {
        System.out.println(new String(pathChildrenCacheEvent.getData().getData()));
    }
}
