package com.effective.raft;

/**
 * filename: RaftServer
 * Description:
 * Author: ubuntu
 * Date: 3/26/18 11:40 AM
 */
public interface RaftServer {

    enum Role{
        INACTIVE(false),
        PASSIVE(false),
        PROMOTABLE(false),
        FOLLOWER(true),
        CANDIDATE(true),
        LEADER(true);
        private final boolean active;

        Role(boolean active) {
            this.active = active;
        }
        public boolean active(){
            return active;
        }
    }
}
