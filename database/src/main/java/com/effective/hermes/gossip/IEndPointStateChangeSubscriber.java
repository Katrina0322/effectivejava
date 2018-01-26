package com.effective.hermes.gossip;

import com.effective.hermes.net.EndPoint;

/**
 * filename: IEndPointStateChangeSubscriber
 * Description: 订阅者 Subscriber
 * Author: ubuntu
 * Date: 1/26/18 2:40 PM
 */
public interface IEndPointStateChangeSubscriber {
    void onChange(EndPoint endpoint, EndPointState epState);
}
