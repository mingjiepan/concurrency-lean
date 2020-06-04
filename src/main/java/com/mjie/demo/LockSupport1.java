package com.mjie.demo;

import java.util.concurrent.locks.LockSupport;

public class LockSupport1 {

    static Thread t1 = null, t2 = null;

    public static void main(String[] args) {
        char[] a1 = "1234567890".toCharArray();
        char[] a2 = "abcdefghij".toCharArray();

        t1 = new Thread(() -> {
            for (char c: a1) {
                System.out.println(c);
                LockSupport.unpark(t2);
                LockSupport.park();
            }
        }, "t1");

        t2 = new Thread(() -> {

            for (char c : a2) {
                LockSupport.park();
                System.out.println(c);
                LockSupport.unpark(t1);
            }

        }, "t2");

        t1.start();
        t2.start();
    }
}
