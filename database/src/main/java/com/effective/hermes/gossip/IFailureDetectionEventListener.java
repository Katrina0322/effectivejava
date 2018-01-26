package com.effective.hermes.gossip;

import com.effective.hermes.net.EndPoint;

/**
 * filename: IFailureDetectionEventListener
 * Description:
 * Author: ubuntu
 * Date: 1/26/18 3:04 PM
 */
public interface IFailureDetectionEventListener {
    void convict(EndPoint ep);
    void suspect(EndPoint ep);
}
