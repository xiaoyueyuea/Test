package com.lay.test;

import java.util.concurrent.CountDownLatch;

/**
 * @Version 1.0
 * @Author lei.yue
 * @Created 2022/3/2 10:48
 * @Description <p>
 * @Modification <p>
 * Date Author Version Description
 * <p>
 * 2022/3/2 lei.yue 1.0 create file
 */

/**
 * 重排序：1.编译器优化：对于没有数据依赖关系的操作，编译器在编译的过程中会进行一定程度的重排（线程 1 中编译器是可以将 a = 1 和 x = b 换一下顺序的，因为它们之间没有数据依赖关系，线程2同理）
 *        2.指令重排序：CPU 优化行为，也是会对不存在数据依赖关系的指令进行一定程度的重排
 *        3.内存系统重排序：内存系统没有重排序，但是由于有缓存的存在，使得程序整体上会表现出乱序的行为。（假设不发生编译器重排和指令重排，线程 1 修改了 a 的值，但是修改以后，a 的值可能没有及时写回到主存中）
 */
public class ReorderingTest {
    private static int x = 0, y = 0;
    private static int a = 0, b =0;

    public static void main(String[] args) throws InterruptedException {
        int i = 0;
        for(;;) {
            i++;
            x = 0; y = 0;
            a = 0; b = 0;
            CountDownLatch latch = new CountDownLatch(1);

            Thread one = new Thread(() -> {
                try {
                    latch.await();
                } catch (InterruptedException e) {
                }
                a = 1;
                x = b;
            });

            Thread other = new Thread(() -> {
                try {
                    latch.await();
                } catch (InterruptedException e) {
                }
                b = 1;
                y = a;
            });
            one.start();other.start();
            latch.countDown();
            one.join();other.join();

            String result = "第" + i + "次 (" + x + "," + y + "）";
            if(x == 0 && y == 0) {
                System.err.println(result);
                break;
            } else {
                System.out.println(result);
            }
        }
    }
}
