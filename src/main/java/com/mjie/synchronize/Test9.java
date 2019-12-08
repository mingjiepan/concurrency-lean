package com.mjie.synchronize;

/**
 死锁：线程1等待线程2互斥持有的资源，而线程2也在等待线程1互斥持有的资源，两个线程都无法继续执行
 活锁：线程持续重试一个失败的操作，导致无法继续执行
 饿死：线程一直被调度器延迟访问其赖以执行的资源，也许是调度器先于低优先级的线程而执行高优先级的线程，同时总是会有一个高优先级的线程可以执行，饿死也叫无限延迟
 */
public class Test9 {

    private Object lock1 = new Object();
    private Object lock2 = new Object();


    public void method1() {
        synchronized (lock1) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (lock2) {
                System.out.println("method1");
            }
        }
    }

    public void method2() {
        synchronized (lock2) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized (lock1) {
                System.out.println("method2");
            }
        }
    }

    public static void main(String[] args) {
        Test9 test9 = new Test9();

        Runnable runnable1 = () -> test9.method1();

        Runnable runnable2 = () -> test9.method2();

        Thread t1 = new Thread(runnable1);
        Thread t2 = new Thread(runnable2);

        t1.start();
        t2.start();
    }
}
