package com.mjie.aqs;

import java.util.concurrent.TimeUnit;

/**
 * synchronized 可重入
 */
public class T01_ReentrantLock1 {

    private synchronized void m1() {
        for (int i = 0; i < 10; i++) {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("m1 " + i);
            //可重入锁
            if (i == 2) m2();
        }
    }

    private synchronized void m2() {
        System.out.println("m2");
    }

    public static void main(String[] args) {
        T01_ReentrantLock1 lock1 = new T01_ReentrantLock1();
        new Thread(lock1::m1).start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(lock1::m2).start();
    }
}
