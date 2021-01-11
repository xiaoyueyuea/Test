package com.lay.quartz.job;

import org.quartz.*;

import java.util.Date;

/**
 * @Version 1.0
 * @Author lei.yue
 * @Created 2020/10/28 16:35
 * @Description <p>
 * @Modification <p>
 * Date Author Version Description
 * <p>
 * 2020/10/28 lei.yue 1.0 create file
 */
public class CountDownJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        Trigger trigger = jobExecutionContext.getTrigger();
        int totalMinutes = trigger.getJobDataMap().getInt("minutes");
        long startTime = trigger.getJobDataMap().getLong("startTime");
        long currentTime = new Date().getTime();
        int passedMinutes = (int)(currentTime - startTime)/1000/60;
        int surplusMinutes = totalMinutes - passedMinutes;
        System.out.println("倒计时：" + surplusMinutes + "分钟");

        try{
            if(surplusMinutes == 0){
                System.out.println("下班下班下班!!!");
                Scheduler scheduler = jobExecutionContext.getScheduler();
                scheduler.shutdown();
                System.out.println("-------------scheduler shutdown!-----------");
            }
        }catch (Exception e){
            System.out.println("倒计时出了点问题：" + e);
        }
    }
}
