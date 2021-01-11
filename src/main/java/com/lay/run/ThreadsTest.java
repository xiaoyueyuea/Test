package com.lay.run;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @Version 1.0
 * @Author lei.yue
 * @Created 2020/8/6 9:49
 * @Description <p>
 * @Modification <p>
 * Date Author Version Description
 * <p>
 * 2020/8/6 lei.yue 1.0 create file
 */
public class ThreadsTest {

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0;i<4;i++){
//                    System.out.println("A");
//                    System.out.println("B");
                    getTime("thread1");
                }
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i = 0;i<4;i++){
//                    System.out.println("C");
//                    System.out.println("D");
                    getTime("thread2");
                }
            }
        });
        thread1.start();
        //thread1.join();//thread1执行完成后执行thread2
        thread2.start();
    }

    private static synchronized void getTime(String objectName){
        for(int i = 0;i<3;i++){
            System.out.println(objectName + ": " + i);
        }
    }

}
