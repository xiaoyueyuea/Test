package com.lay.threads.concurrentUtilKit;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @Version 1.0
 * @Author lei.yue
 * @Created 2021/10/12 17:12
 * @Description <p>
 * @Modification <p>
 * Date Author Version Description
 * <p>
 * 2021/10/12 lei.yue 1.0 create file
 */
//Semaphore 通常我们叫它信号量,可以用来控制同时访问特定资源的线程数量，通过协调各个线程，以保证合理的使用资源。
public class SemaphoreTest {

    //1.默认创建一个非公平的锁的同步阻塞队列
    //2.把初始令牌数量赋值给同步队列的state状态，state的值就代表当前所剩余的令牌数量
    private static Semaphore semaphore = new Semaphore(10);//停车场最多能停10辆车

    static class ParkCar implements Runnable{

        private int carNo;

        public ParkCar(int carNo){
            this.carNo = carNo;
        }

        @Override
        public void run() {
            try {
                if(semaphore.availablePermits() == 0){
                    System.out.println("停车位已满：" + carNo);
                }
                //1、当前线程会尝试去同步队列获取一个令牌，获取令牌的过程也就是使用原子的操作去修改同步队列的state ,获取一个令牌则修改为state=state-1
                //2、当计算出来的state<0，则代表令牌数量不足，此时会创建一个Node节点加入阻塞队列，挂起当前线程。
                //2、当计算出来的state>=0，则代表获取令牌成功。
                semaphore.acquire(1);//获取令牌
                System.out.println("进入停车场：" + carNo);
                Thread.sleep(new Random().nextInt(20000));//模拟停车
                System.out.println("离开停车场：" + carNo);
                //1、线程会尝试释放一个令牌，释放令牌的过程也就是把同步队列的state修改为state=state+1的过程
                //2、释放令牌成功之后，同时会唤醒同步队列中的一个线程。
                //3、被唤醒的节点会重新尝试去修改state=state-1 的操作，如果state>=0则获取令牌成功，否则重新进入阻塞队列，挂起线程。
                semaphore.release(1);//释放令牌
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        for(int i = 0;i < 15;i++){
            exec.execute(new ParkCar(i));
        }
    }

}
