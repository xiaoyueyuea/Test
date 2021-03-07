package com.lay.threads.concurrentUtilKit.locks;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @Version 1.0
 * @Author lei.yue
 * @Created 2021/3/1 11:24
 * @Description <p>
 * @Modification <p>
 * Date Author Version Description
 * <p>
 * 2021/3/1 lei.yue 1.0 create file
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService exc = Executors.newCachedThreadPool();

        for(int i = 0;i < 1000000;i++){
            exc.execute(new VolatileTest());
        }

        //等待线程池中所有线程执行完毕
        exc.shutdown();
        exc.awaitTermination(1, TimeUnit.MINUTES);
        System.out.println(VolatileTest.data);
    }
}
