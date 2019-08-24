package com.mjie.synchronize;

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
