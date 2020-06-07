package com.mjie.atomic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

public class T02_AtomicLongVsLongAddr {

    private static long count1 = 0;

    private static AtomicLong count2 = new AtomicLong(0L);

    //分段锁
    private static LongAdder count3 = new LongAdder();

    public static void main(String[] args) {

        Object object = new Object();
        Thread[] threads = new Thread[1000];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 100000; j++) {
                    synchronized (object) {
                        count1++;
                    }
                }
            });
        }
        testTimes(threads, "sync");
        System.out.println("count1 = " + count1);

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 100000; j++) {
                    count2.incrementAndGet();
                }
            });
        }
        testTimes(threads, "atomicLong");
        System.out.println("count2 = " + count2);

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 100000; j++) {
                    count3.increment();
                }
            });
        }
        testTimes(threads, "longAddr");
        System.out.println("count3 = " + count3);
    }



    private static void testTimes(Thread[] threads, String str) {
        List<Thread> threadList = Arrays.asList(threads);
        long timeMillis = System.currentTimeMillis();

        for (Thread thread : threadList) {
            thread.start();
        }

        threadList.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println(str + " times : " +(System.currentTimeMillis() - timeMillis));
    }
}
