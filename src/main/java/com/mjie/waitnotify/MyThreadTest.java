package com.mjie.waitnotify;

import java.util.Random;

public class MyThreadTest {
    public static void main(String[] args) {
        Runnable runnable = new MyThread1();

        Thread t1 = new Thread(runnable);
        Thread t2 = new Thread(runnable);

        t1.start();
        t2.start();
    }
}
class MyThread1 implements Runnable {
    int a;

    @Override
    public void run() {
        a = 0;

        while (true) {
            System.out.println("a = " + a++);

            try {
                Thread.sleep((long)(Math.random() + 1000));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (a == 30) {
                break;
            }
        }
    }
}
