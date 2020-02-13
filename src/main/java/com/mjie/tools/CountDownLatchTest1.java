package com.mjie.tools;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 使用场景1：启动多个线程去处理多个任务，当所有任务都完成后，再处理后续的逻辑
 */
public class CountDownLatchTest1 {
    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(5);

        ExecutorService executor = Executors.newSingleThreadExecutor();

        long startTime = System.currentTimeMillis();

        for (int i = 0; i < countDownLatch.getCount(); i++) {
            executor.submit(new Worker(countDownLatch));
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long l = System.currentTimeMillis() - startTime;

        System.out.println("times = " + l);
        System.out.println("main end");

        executor.shutdownNow();
    }
}


class Worker implements Runnable {
    private final CountDownLatch latch;

    public Worker(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        doWork();
        latch.countDown();
    }
    private void doWork() {
        try {
            Thread.sleep(new Random().nextInt(10) * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("threadName = " + Thread.currentThread().getName() + " work done");
    }
}