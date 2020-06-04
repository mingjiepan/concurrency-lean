package com.mjie.synchronize;

import java.util.concurrent.CountDownLatch;

public class Cache {

    private static long count = 100000000L;

    private static class T {
        public volatile long x = 0L;
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


        long timeMillis = System.nanoTime();
        t1.start();
        t2.start();
        countDownLatch.await();
        System.out.println("执行耗时 " + (System.nanoTime() - timeMillis));




    }






}
