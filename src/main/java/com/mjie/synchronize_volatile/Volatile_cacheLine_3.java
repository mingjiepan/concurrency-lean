package com.mjie.synchronize_volatile;

import java.util.concurrent.CountDownLatch;

public class Volatile_cacheLine_3 {

    private static long count = 100000000L;

    /**
     * 保证和其他实例的成员变量不在同一行
     */
    private static class T {
        public volatile long l1,l2,l3,l4,l5,l6,l7,l8 = 0L;
        public volatile long x = 0L;
        public volatile long l9,l10,l11,l12,l13,l14,l15 = 0L;
    }

    public static T[] arr = new T[2];
    static {
        arr[0] = new T();
        arr[1] = new T();
    }


    public static void main(String[] args) throws Exception {

        CountDownLatch countDownLatch = new CountDownLatch(2);

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < count; i++) {
                arr[0].x = i;
            }
            countDownLatch.countDown();
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < count; i++) {
                arr[1].x = i;
            }
            countDownLatch.countDown();
        });


        long timeMillis = System.currentTimeMillis();
        t1.start();
        t2.start();
        countDownLatch.await();
        System.out.println("执行耗时 " + (System.currentTimeMillis() - timeMillis));
    }
}
