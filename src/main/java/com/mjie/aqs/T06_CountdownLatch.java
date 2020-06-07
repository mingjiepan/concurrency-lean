package com.mjie.aqs;

import java.util.concurrent.CountDownLatch;

public class T06_CountdownLatch {
    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(20);
        for (int i = 0; i < 100; i++) {
            int finalI = i;
            new Thread(()-> {
                System.out.println("我来了" + finalI);
                countDownLatch.countDown();
            }).start();
        }


        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("countdown 被减为0");
    }
}
