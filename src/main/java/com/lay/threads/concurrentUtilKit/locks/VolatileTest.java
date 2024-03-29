package com.lay.threads.concurrentUtilKit.locks;

/**
 * @Version 1.0
 * @Author lei.yue
 * @Created 2021/3/1 11:22
 * @Description <p>
 * @Modification <p>
 * Date Author Version Description
 * <p>
 * 2021/3/1 lei.yue 1.0 create file
 */
public class VolatileTest implements Runnable{

    static volatile int data = 0;


    @Override
    public void run() {
        //volatile只能保证可见性(将最后更新的值即时刷新到主存)，对非原子操作不保证原子性
        data ++;
    }
}
