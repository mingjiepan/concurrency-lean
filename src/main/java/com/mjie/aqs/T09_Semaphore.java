package com.mjie.aqs;

import java.util.concurrent.Semaphore;

/**
 * 限流
 *
 * 同时只能有几个人同时执行
 *
 * 车道和收费站
 *
 */
public class T09_Semaphore {

    static Semaphore semaphore = new Semaphore(1);
//    static Semaphore semaphore = new Semaphore(1, true);

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                semaphore.acquire();
                System.out.println("t1 running");
                Thread.sleep(100);
                System.out.println("t1 running");
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                semaphore.acquire();
                System.out.println("t2 running");
                Thread.sleep(100);
                System.out.println("t2 running");
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
