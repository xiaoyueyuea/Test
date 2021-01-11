package com.lay.quartz.scheduing;

import com.lay.quartz.job.CountDownJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;
import java.util.Scanner;

/**
 * @Version 1.0
 * @Author lei.yue
 * @Created 2020/10/28 16:33
 * @Description <p>
 * @Modification <p>
 * Date Author Version Description
 * <p>
 * 2020/10/28 lei.yue 1.0 create file
 */
public class CountDownScheduling {

    public static void main(String[] args) throws SchedulerException, InterruptedException {
        Scanner sc = new Scanner(System.in);
        System.out.print("请输入倒计时分钟数：");
        int minutes = sc.nextInt();
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();
        JobDetail job = JobBuilder.newJob(CountDownJob.class).withIdentity("job2","group2").build();
        Trigger trigger = TriggerBuilder.newTrigger().withDescription("倒计时")
                .withIdentity("trigger2","triggerGroup2")
                .startAt(new Date())
                .withSchedule(CronScheduleBuilder.cronSchedule("0 0/1 * * * ? *"))
                .usingJobData("minutes",minutes)
                .usingJobData("startTime",new Date().getTime())
                .build();
        scheduler.scheduleJob(job,trigger);
        System.out.println("-------------scheduler start!-----------");
        scheduler.start();
    }
}
