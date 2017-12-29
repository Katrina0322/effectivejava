package listener;

import alert.CacheData;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;

/**
 * filename: NodeListenerImpl
 * Description:
 * Author: ubuntu
 * Date: 12/27/17 3:39 PM
 */
public abstract class NodeListenerImpl implements NodeCacheListener {
    private CacheData cacheData;

    public NodeListenerImpl(CacheData cacheData) {
        this.cacheData = cacheData;
    }

    @Override
    public abstract void nodeChanged() throws Exception;
}
