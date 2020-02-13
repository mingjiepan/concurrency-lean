package com.mjie.tools;

import java.util.concurrent.Semaphore;

/**
 * 信号量，许可
 *
 *
 * CountDownLatch
 *
 * CyclicBarrier
 *
 * Semaphore
 *
 * Exchanger
 */
public class SemaphoreTest1 {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(1, true);

        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("hello world");
        semaphore.release(1);
    }
}