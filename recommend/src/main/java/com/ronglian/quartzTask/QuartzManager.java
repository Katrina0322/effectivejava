package com.ronglian.quartzTask;

import org.quartz.Job;

/**
 * description:
 * Created by spark on 18-5-16.
 */
public class QuartzManager {
    public boolean addJob(String jobName, String jobGroup, Class<? extends Job> jobClass){
        return true;
    }
}
