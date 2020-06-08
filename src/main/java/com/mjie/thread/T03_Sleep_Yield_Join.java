package com.mjie.thread;

import java.util.concurrent.TimeUnit;

/**
 * sleep 线程睡眠，将CPU让给其他线程运行，线程不会释放对象的锁，而wait的，线程会释放对象的锁
 * yield  线程返回就绪状态，进入等待队列，和其他线程一起竞争，让出CPU
 * join 等待另一个线程的结束 t1线程里执行t2.join t1会等待t2线程执行完才继续执行t1后续的流程
 */

public class T03_Sleep_Yield_Join {
    public static void main(String[] args) {
        testJoin();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void testJoin() {
        Thread t1 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("t1");
        }, "t1");
        Thread t2 = new Thread(() -> {
            try {
                t1.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("t2");
        });
        t1.start();
        t2.start();
    }
}
