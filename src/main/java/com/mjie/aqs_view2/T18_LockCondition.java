package com.mjie.aqs_view2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class T18_LockCondition {
    public static void main(String[] args) throws InterruptedException {
        char[] a1 = "1234567890".toCharArray();
        char[] a2 = "abcdefghij".toCharArray();

        final Lock lock = new ReentrantLock();
        Condition condition1 = lock.newCondition();
        Condition condition2 = lock.newCondition();

        new Thread(() -> {
            try {
                lock.lock();

                for (char c : a1) {
                    System.out.println(c);
                    condition2.signal();
                    condition1.await();
                }
                condition2.signal();
            } catch (Exception ex) {

            } finally {
                lock.unlock();
            }
        }, "t1").start();

        new Thread(() -> {
            try {
                lock.lock();
                for (char c : a2) {
                    System.out.println(c);
                    condition1.signal();
                    condition2.await();
                }
                condition1.signal();
            } catch (Exception ex) {

            } finally {
                lock.unlock();
            }
        },"t2").start();
    }
}
