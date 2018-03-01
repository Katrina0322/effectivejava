package com.effective.hermes.gossip;

/**
 * filename: ApplicationState
 * Description:
 * Author: ubuntu
 * Date: 1/26/18 11:10 AM
 */
public enum ApplicationState {
        STATUS,
        LOAD,
        SCHEMA,
        DC,
        RACK,
        RELEASE_VERSION,
        REMOVAL_COORDINATOR,
        INTERNAL_IP,
        RPC_ADDRESS,
        X_11_PADDING, // padding specifically for 1.1
        SEVERITY,
        NET_VERSION,
        HOST_ID,
        TOKENS,
        RPC_READY
}
