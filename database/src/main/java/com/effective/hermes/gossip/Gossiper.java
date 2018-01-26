package com.effective.hermes.gossip;

import com.effective.hermes.net.EndPoint;
import com.effective.hermes.service.IComponentShutdown;

import java.util.*;

/**
 * filename: Gossiper
 * Description:
 * Author: ubuntu
 * Date: 1/26/18 10:52 AM
 */
public class Gossiper implements IFailureDetectionEventListener, IEndPointStateChangePublisher, IComponentShutdown{
    private EndPoint localEndPoint;
    private long aVeryLongTime;
    private Random random_ = new Random();
    private int prevIndex = 0;
    private int rrIndex = 0;
    /* 订阅者 */
    private List<IEndPointStateChangeSubscriber> subscribers = new ArrayList<>();
    /* live member set */
    private Set<EndPoint> liveEndpoints = new HashSet<>();
    /* unreachable member set */
    private Set<EndPoint> unreachableEndpoints = new HashSet<>();
    private Map<EndPoint, EndPointState> endPointStateMap = new Hashtable<>();
    private Set<EndPoint> seeds = new HashSet<>();

    private Gossiper() {
        aVeryLongTime = 259200 * 1000;
    }

    public static Gossiper getInstance(){
        return GossiperHolder.INSTANCE;
    }

    private static class GossiperHolder{
        private static final Gossiper INSTANCE = new Gossiper();
    }

    @Override
    public void shutdown() {

    }

    @Override
    public void register(IEndPointStateChangeSubscriber subcriber) {

    }

    @Override
    public void unregister(IEndPointStateChangeSubscriber subcriber) {

    }

    @Override
    public void convict(EndPoint ep) {

    }

    @Override
    public void suspect(EndPoint ep) {

    }
}
