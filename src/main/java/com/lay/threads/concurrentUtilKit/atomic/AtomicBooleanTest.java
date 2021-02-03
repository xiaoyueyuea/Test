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

    //使用static修饰
    //被static修饰的变量、被static修饰的方法统一属于类的静态资源，是类实例之间共享的，换言之，一处变、处处变。
    //静态资源是类初始化的时候加载的，而非静态资源是类new的时候加载的。类的初始化早于类的new
    public static AtomicBoolean flag = new AtomicBoolean(false);
    private String name;

    public AtomicBooleanTest(String name){
        this.name = name;
    }

    @Override
    public void run() {
        //与expect值相同则update并返回true
        if(flag.compareAndSet(false,true)){
            try{
                System.out.println(name + "起床了");
                Thread.sleep(1000);
                System.out.println(name + "上班了");
                Thread.sleep(1000);
                System.out.println(name + "下班了");

                System.out.println("AtomicBoolean被" + name +"更新为" + flag.get());

                flag.set(false);
            }catch (Exception e){
                //异常
            }
        }else {
            System.out.println(name + "想起床，但AtomicBoolean不同意");
        }
    }
}
