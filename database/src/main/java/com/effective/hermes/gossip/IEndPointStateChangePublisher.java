package com.effective.hermes.gossip;

/**
 * filename: IEndPointStateChangePublisher
 * Description: 发布者
 * Author: ubuntu
 * Date: 1/26/18 2:40 PM
 */
public interface IEndPointStateChangePublisher {
    void register(IEndPointStateChangeSubscriber subcriber);
    void unregister(IEndPointStateChangeSubscriber subcriber);
}
