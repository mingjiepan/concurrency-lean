package com.mjie.synchronize_volatile;

import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * JIT  just in time compile
 */
public class CAS_1 {
    static AtomicInteger atomicInteger = new AtomicInteger(1);

    public static void main(String[] args) throws Exception {


        CountDownLatch latch = new CountDownLatch(100);

        Thread[] threads = new Thread[100];


        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    atomicInteger.getAndIncrement();
                }
                latch.countDown();
            });
        }

        Arrays.asList(threads).forEach(Thread::start);
        latch.await();

        System.out.println(atomicInteger.get());
    }
}
