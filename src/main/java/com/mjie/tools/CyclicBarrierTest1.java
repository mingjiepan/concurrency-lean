package com.mjie.tools;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierTest1 {
    public static void main(String[] args) {

        CyclicBarrier cyclicBarrier = new CyclicBarrier(5, () -> {
            System.out.println("all task has finished");
        });

        for (int i = 0; i < cyclicBarrier.getParties(); i++) {
            new Thread(new Worker3(cyclicBarrier)).start();
        }

        int numberWaiting = cyclicBarrier.getNumberWaiting();
        System.out.println("NumberWaiting = " + numberWaiting);
    }
}



class Worker3 implements Runnable {
    private CyclicBarrier cyclicBarrier;
    public Worker3(CyclicBarrier cyclicBarrier) {
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        doWork();
        try {
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    private void doWork() {
        try {
            Thread.sleep(new Random().nextInt(10) * 5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "do work");
    }
}
