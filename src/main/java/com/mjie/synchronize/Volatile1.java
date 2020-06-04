package com.mjie.synchronize;

public class Volatile1 {
    //若没加volatile，那么线程将永远也不会退出
//    private static boolean flag = true;

    //加上关键字后，主线程对flag的修改对start线程是可见的
    private static volatile boolean flag = true;

    public static void main(String[] args)throws Exception {

        new Thread(() -> {
            while (flag) {

            }

            System.out.println("new thread end");
        }, "start").start();

        Thread.sleep(1000);

        //修改这个值对start线程不可用
        flag = false;
    }
}
