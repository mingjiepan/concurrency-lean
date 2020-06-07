package com.mjie.aqs;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock 可重入
 */
public class T02_ReentrantLock2 {

    private Lock lock = new ReentrantLock();

    private  void m1() {
        try {
            lock.lock();
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
        } finally {
            lock.unlock();
        }
    }

    private void m2() {
        try {
            lock.lock();
            System.out.println("m2");
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        T02_ReentrantLock2 lock2 = new T02_ReentrantLock2();
        new Thread(lock2::m1).start();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(lock2::m2).start();
    }
}
