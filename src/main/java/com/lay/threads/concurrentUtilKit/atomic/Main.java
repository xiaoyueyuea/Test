package com.lay.threads.concurrentUtilKit.atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @Version 1.0
 * @Author lei.yue
 * @Created 2021/1/20 15:58
 * @Description <p>
 * @Modification <p>
 * Date Author Version Description
 * <p>
 * 2021/1/20 lei.yue 1.0 create file
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        // 创建线程池
        ExecutorService exec = Executors.newCachedThreadPool();
        //AtomicBoolean测试
        exec.execute(new AtomicBooleanTest("Lay"));
        exec.execute(new AtomicBooleanTest("Krispy"));

        //AtomicReference测试
        for(int i = 0;i<1000000;i++){
            exec.execute(new AtomicReferenceTest());
        }

        //等待线程池中所有线程执行完毕
        exec.shutdown();
        exec.awaitTermination(1, TimeUnit.MINUTES);
        System.out.println(AtomicReferenceTest.count);
    }
}
