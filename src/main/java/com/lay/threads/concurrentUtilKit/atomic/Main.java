package com.lay.threads.concurrentUtilKit.atomic;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

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
    public static void main(String[] args) throws InterruptedException {
//        // 创建线程池
        ExecutorService exec = Executors.newCachedThreadPool();
//        //AtomicBoolean测试
//        exec.execute(new AtomicBooleanTest("Lay"));
//        exec.execute(new AtomicBooleanTest("Krispy"));
//
//        //AtomicReference测试
//        for(int i = 0;i<1000000;i++){
//            exec.execute(new AtomicReferenceTest());
//        }
//
//        //等待线程池中所有线程执行完毕
//        exec.shutdown();
//        exec.awaitTermination(1, TimeUnit.MINUTES);
//        System.out.println(AtomicReferenceTest.count);

//        List<String> strs = new ArrayList<>();
//        String test = "";
//        System.out.println(strs.contains(test));
//        System.out.println(strs.contains(null));

//        String[] strs = new String[]{"Lay","Krispy"};
//        List<String> stringList = Arrays.asList(strs);
//        stringList.add("测试");

        Map<String,String> map = new HashMap<>();
        map.put("me","Lay");
        map.put("she","Krispy");
        //map.keySet().forEach(System.out::println);
        //map.values().forEach(System.out::println);
//        map.entrySet().forEach(e -> {
//            System.out.println(e.getKey() + "###" + e.getValue());
//        });

//        map.keySet().add("测试");
//        map.values().add("测试");


        BigDecimal bigDecimal = new BigDecimal("");
        System.out.println(bigDecimal);
    }
}
