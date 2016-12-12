package org.microframe.rpc.rpccore;

import org.microframe.rpc.rpcmodel.MessageResponse;
import org.microframe.rpc.rpcmodel.MessageRequest;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * used to
 * Created by tianjin on 12/8/16.
 */
public class MessageCallBack {
    private MessageRequest messageRequest;
    private MessageResponse messageResponse;
    private Lock lock = new ReentrantLock();
    private Condition finish = lock.newCondition();

    public MessageCallBack(MessageRequest messageRequest) {
        this.messageRequest = messageRequest;
    }

    public Object start() throws InterruptedException {
        try {
            lock.lock();
            finish.await(10*1000, TimeUnit.MILLISECONDS);
            if(messageResponse != null) {
                return messageResponse.getResultDesc();
            }else {
                return null;
            }
        } finally {
            lock.unlock();
        }
    }

    public void over(MessageResponse reponse) {
        try {
            lock.lock();
            finish.signal();
            messageResponse = reponse;
        } finally {
            lock.unlock();
        }
    }
}
