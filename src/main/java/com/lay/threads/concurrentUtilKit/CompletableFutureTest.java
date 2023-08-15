package com.lay.threads.concurrentUtilKit;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author yuelei
 * @Date 2023/08/14 17:50
 **/
// 参考 https://javadoop.com/post/completable-future
@Slf4j
public class CompletableFutureTest {

    public static void main(String[] args) {
        // 定义线程池
        ExecutorService executorService = Executors.newFixedThreadPool(3);
//        // 无返回值
//        CompletableFuture.runAsync(() -> {
//            log.info("【无返回值】A执行好了");
//        }, executorService).thenRun(() -> {log.info("【无返回值】B执行");});
//
//        //有返回值
//        CompletableFuture.supplyAsync(() -> {
//            log.info("【有返回值】A执行");
//            return "Task A success";
//        }, executorService).thenAccept((result) -> {log.info("【有返回值】A任务返回值:{}", result);});
//
        // 并行执行demo
        CompletableFuture<String> cfA = CompletableFuture.supplyAsync(() -> {
            log.info("【测试并行执行】A execute start");
            try {
                Thread.sleep(6000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("【测试并行执行】A execute end");
            return "resultA";
        }, executorService).exceptionally(ex -> "A任务异常");
        CompletableFuture<String> cfB = CompletableFuture.supplyAsync(() -> {
            log.info("【测试并行执行】B execute start");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            log.info("【测试并行执行】B execute end");
            return "resultB";
        }, executorService).exceptionally(ex -> "B任务异常");

        // 后续的处理不需要返回值
        cfA.thenAcceptBoth(cfB, (resultA, resultB) -> {log.info("【测试并行执行】thenAcceptBoth:{}", resultA + " " + resultB);});
        // 有返回值
        cfA.thenCombine(cfB, (resultA, resultB) -> {
            log.info("【测试并行执行】thenCombine:{}", resultA + " " + resultB);
            return resultA + " " + resultB;
        });

        log.info("【测试并行执行】标记1");
        // 同理 对应还有anyOf 但anyOf有返回值，用Object接收
        CompletableFuture<Void> allOf = CompletableFuture.allOf(cfA, cfB);
        log.info("【测试并行执行】标记2");
        // 阻塞，直到所有任务完成
        allOf.join();
        log.info("【测试并行执行】任务全部已完成");

        executorService.shutdown();
    }
}
