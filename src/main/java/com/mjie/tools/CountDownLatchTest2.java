package com.mjie.tools;

import java.util.Random;
import java.util.concurrent.*;

/**
 * 场景2：一个开始的latch，一个结束的latch，所有的任务在同一时刻开始执行任务，在所有的任务都执行完毕后，处理其他任务。
 */
public class CountDownLatchTest2 {
    public static void main(String[] args) {
        CountDownLatch startLatch = new CountDownLatch(1);
        CountDownLatch doneLatch = new CountDownLatch(10);

        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(8, 16, 5, TimeUnit.SECONDS, new ArrayBlockingQueue(15));
        threadPool.allowCoreThreadTimeOut(true);
        threadPool.setKeepAliveTime(3, TimeUnit.SECONDS);
        threadPool.setRejectedExecutionHandler(new ThreadPoolExecutor.AbortPolicy());

        for (int i = 0; i < doneLatch.getCount(); i++) {
            threadPool.submit(new Worker2(startLatch, doneLatch));
        }

        mainWork();
        startLatch.countDown();

        mainWork2();


        try {
            doneLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }



        mainWork3();

       /* try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/



    }


    private  static void mainWork() {
        System.out.println("main work");
    }

    private static void mainWork2() {
        System.out.println("main work2");
    }
    private static void mainWork3() {
        System.out.println("main work3");
    }
}


class Worker2 implements Runnable {

    private final CountDownLatch startLatch;
    private final CountDownLatch doneLatch;

    public Worker2(CountDownLatch startLatch, CountDownLatch doneLatch) {
        this.startLatch = startLatch;
        this.doneLatch = doneLatch;
    }

    @Override
    public void run() {
        try {
            startLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        doWork();
        doneLatch.countDown();
    }

    private void doWork() {
        try {
            Thread.sleep(new Random().nextInt(10) * 300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " do Work done");

    }
}