package com.lay.quartz.scheduing;

import com.lay.quartz.job.PrintWordsJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @Version 1.0
 * @Author lei.yue
 * @Created 2020/7/15 10:52
 * @Description <p>
 * @Modification <p>
 * Date Author Version Description
 * <p>
 * 2020/7/15 lei.yue 1.0 create file
 */
public class PrintWordsScheduing {

    public static void main(String[] args) throws SchedulerException, InterruptedException {
        //创建调度器
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();
        // 2、创建JobDetail实例，并与PrintWordsJob类绑定(Job执行内容)
        JobDetail jobDetail = JobBuilder.newJob(PrintWordsJob.class).withIdentity("job1","group1").build();
        // 3、构建Trigger实例
        Trigger trigger = TriggerBuilder.newTrigger().withDescription("")
                .withIdentity("trigger1","triggerGroup1")
                .startAt(new Date())//默认当前时间启动
                .withSchedule(CronScheduleBuilder.cronSchedule("1 0/1 * * * ? *"))//每分钟执行一次
                .usingJobData("triggerKey","timeInfo")
                .build();

        //执行
        scheduler.scheduleJob(jobDetail,trigger);
        System.out.println("-------------scheduler start!-----------");
        scheduler.start();

        //睡眠
        TimeUnit.MINUTES.sleep(60);
        scheduler.shutdown();
        System.out.println("-------------scheduler shutdown!-----------");
    }
}
