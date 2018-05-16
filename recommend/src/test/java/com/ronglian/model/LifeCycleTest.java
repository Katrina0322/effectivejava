package com.ronglian.model;

import com.ronglian.core.LifeCycle;
import org.junit.Test;

/**
 * description:
 * Created by spark on 18-5-15.
 */
public class LifeCycleTest {
    @Test
    public void testLifeCycle(){
        MyLifeCycle cycle = new MyLifeCycle();
        org.junit.Assert.assertTrue(cycle.startTraining());
    }

    private static class MyLifeCycle implements LifeCycle {

        @Override
        public String getStatus() {
            return LifeCycle.START_TRAINING;
        }
    }
}
