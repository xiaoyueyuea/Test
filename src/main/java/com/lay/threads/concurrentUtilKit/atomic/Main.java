package com.lay.threads.concurrentUtilKit.atomic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
    public static void main(String[] args) {
        // 创建线程池
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new AtomicBooleanTest("Lay"));
        exec.execute(new AtomicBooleanTest("Krispy"));
    }
}
