package com.lay.threads.concurrentUtilKit.atomic;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @Version 1.0
 * @Author lei.yue
 * @Created 2021/1/20 15:28
 * @Description <p>
 * @Modification <p>
 * Date Author Version Description
 * <p>
 * 2021/1/20 lei.yue 1.0 create file
 */
//AtomicBoolean:可用原子方式更新boolean值,使用了硬件底层的CAS（调用UnSafe类中的本地方法）操作来保证并发情况下值的安全
public class AtomicBooleanTest implements Runnable{

    public static AtomicBoolean flag = new AtomicBoolean(false);
    private String name;

    public AtomicBooleanTest(String name){
        this.name = name;
    }

    @Override
    public void run() {
        if(flag.compareAndSet(false,true)){
            System.out.println(name + "起床了");
            System.out.println(name + "上班了");
            System.out.println(name + "下班了");

            flag.set(false);
        }else {
            System.out.println(name + "想起床，但AtomicBoolean不同意");
        }
    }
}
