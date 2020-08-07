package com.cm.redis;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * 定時任務測試
 */

public class TestQuartzScheduler {

    public static void main(String[] agrs) throws Exception {
        SchedulerFactory schedFact = new StdSchedulerFactory();
        Scheduler sched = schedFact.getScheduler();

        sched.start();

      // define the job and tie it to our HelloJob class

        JobDetail job = newJob()
                .ofType(HelloJob.class)
                .withIdentity("myJob", "group1")
                .build();

        // Trigger the job to run now, and then every 40 seconds
        Trigger trigger = newTrigger()
                .withIdentity("myTrigger", "group1")
                .startNow()
                .withSchedule(simpleSchedule()
                        .withIntervalInSeconds(40)
                        .repeatForever())
                .build();

        Trigger trigger2 = newTrigger()
                .withIdentity("myTrigger2", "group1")
                .startNow()
                .withSchedule(simpleSchedule()
                        .withIntervalInSeconds(20)
                        .repeatForever())
                .build();
        // Tell quartz to schedule the job using our trigger
        Map<JobDetail, Set<? extends Trigger>> triggersAndJobs = new HashMap<>();
        Set<Trigger> triggers = new HashSet<>();
        triggers.add(trigger);
        triggers.add(trigger2);
        triggersAndJobs.put(job,triggers);
        sched.scheduleJobs(triggersAndJobs,false);
    }

    public static  class  HelloJob implements Job{

        @Override
        public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
            System.out.println("hello quartz job"+System.currentTimeMillis());
        }
    }
}
