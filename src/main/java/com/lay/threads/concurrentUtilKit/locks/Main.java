package com.lay.threads.concurrentUtilKit.locks;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @Version 1.0
 * @Author lei.yue
 * @Created 2021/3/1 11:24
 * @Description <p>
 * @Modification <p>
 * Date Author Version Description
 * <p>
 * 2021/3/1 lei.yue 1.0 create file
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService exc = Executors.newCachedThreadPool();

        for(int i = 0;i < 1000000;i++){
            exc.execute(new VolatileTest());
        }

        //等待线程池中所有线程执行完毕
        exc.shutdown();
        exc.awaitTermination(1, TimeUnit.MINUTES);
        System.out.println(VolatileTest.data);


//        int max = 100;
//        Random random = new Random();
//        List<Integer> a = new ArrayList<>();
//        List<Integer> b = new ArrayList<>();
//        List<Integer> c = new ArrayList<>();
//        for(int i = 0;i<100;i++){
//            int r = random.nextInt(max) + 1;
//            if(r > 0 && r <=10){
//                a.add(r);
//            }else if(r > 10 & r<=90){
//                b.add(r);
//            }else {
//                c.add(r);
//            }
//        }
//        System.out.println("A组：[" + a.size() + "]");
//        a.stream().sorted((e1,e2) -> e1.compareTo(e2)).forEach(e -> System.out.print(e + ","));
//        System.out.println("B组：[" + b.size() + "]");
//        b.stream().sorted((e1,e2) -> e1.compareTo(e2)).forEach(e -> System.out.print(e + ","));
//        System.out.println("C组：[" + c.size() + "]");
//        c.stream().sorted((e1,e2) -> e1.compareTo(e2)).forEach(e -> System.out.print(e + ","));
    }
}
