package com.lay.threads.instance;


/**
 * @Version 1.0
 * @Author lei.yue
 * @Created 2021/10/13 16:03
 * @Description <p>
 * @Modification <p>
 * Date Author Version Description
 * <p>
 * 2021/10/13 lei.yue 1.0 create file
 */
//中断测试（与阻塞不同）:中断仅为一个标志位，具体执行逻辑由程序本身决定
public class InterruptTest {

    static class MyThead implements Runnable{

        @Override
        public void run() {
            for(int i = 0;i < 200;i++){
                System.out.println("当前位置：" + i);
                if(i == 5){
                    try{
                        Thread.sleep(10000);//阻塞当前线程(wait()、join()、yield()等方法也可阻塞)
                    }catch (InterruptedException e){
                        //
                    }
                }
            }
        }
    }

    static class MyThead2 implements Runnable{

        private Thread thread;

        public MyThead2(Thread thread){
            this.thread = thread;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "中断" + thread.getName());
            thread.interrupt();//中断此线程，若此线程当前为阻塞状态则抛出InterruptedException异常，使线程跳出阻塞状态，并且重置中断标识为false
            System.out.println(thread.getName() + ":" + thread.isInterrupted());
        }
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new MyThead());
        Thread thread2 = new Thread(new MyThead2(thread));
        thread.start();
        thread2.start();
    }
}
