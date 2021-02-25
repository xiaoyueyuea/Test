package com.lay.threads.concurrentUtilKit.atomic;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @Version 1.0
 * @Author lei.yue
 * @Created 2021/2/25 15:53
 * @Description <p>
 * @Modification <p>
 * Date Author Version Description
 * <p>
 * 2021/2/25 lei.yue 1.0 create file
 */
//对”对象”进行原子操作。 提供了一种读和写都是原子性的对象引用变量。原子意味着多个线程试图改变同一个AtomicReference(例如比较和交换操作)将不会使得AtomicReference处于不一致的状态。
public class AtomicReferenceTest implements Runnable{

    public static AtomicReference<Integer> count = new AtomicReference<>(0);

    @Override
    public void run() {
        //set方法与get方法不是原子性的，避免误用,参考源码
        count.updateAndGet(v -> v + 1);
    }
}
