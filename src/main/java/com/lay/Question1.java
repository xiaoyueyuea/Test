package com.lay;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Question1 {

    private static final AtomicInteger number = new AtomicInteger(22222222);

    private static final AtomicInteger count = new AtomicInteger(0);

    private static final ThreadPoolExecutor executor = new ThreadPoolExecutor(9, 15, 3000L, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(1000), new ThreadPoolExecutor.DiscardPolicy());

    public static void main(String[] args) {

        // 计数，当打印质数达到1000时终止
        while(count.get() < 1000) {
            // 多线程，配合利用原子类实现
            executor.execute(() -> {
                if(isPrimeNumber(number.incrementAndGet()) && count.incrementAndGet() <= 1000) {
                    System.out.println("质数数值:" + number.get() + " 线程id：" + Thread.currentThread() + "毫秒数：" + System.currentTimeMillis());
                }
            });
        }

        executor.shutdown();
    }

    public static boolean isPrimeNumber(int number) {
        if (number <= 1) {
            return false;
        }
        for (int i = 2; i <number ; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

}
