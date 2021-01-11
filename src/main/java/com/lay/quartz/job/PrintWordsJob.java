package com.lay.quartz.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Version 1.0
 * @Author lei.yue
 * @Created 2020/7/15 10:40
 * @Description <p>
 * @Modification <p>
 * Date Author Version Description
 * <p>
 * 2020/7/15 lei.yue 1.0 create file
 */
public class PrintWordsJob implements Job {
    /**
     *
     * @param jobExecutionContext
     * @throws JobExecutionException
     */
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        String currentTime = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
        System.out.println("当前时间：" + currentTime);
    }
}
