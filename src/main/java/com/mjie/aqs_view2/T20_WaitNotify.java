package com.mjie.aqs_view2;

public class T20_WaitNotify {

    static volatile boolean t2Start = false;
    private static Object object = new Object();

    public static void main(String[] args) {

        char[] a1 = "1234567890".toCharArray();
        char[] a2 = "abcdefghij".toCharArray();

        new Thread(() -> {
            synchronized (object) {
                while (!t2Start) {
                    try {
                        object.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                for (char c : a1) {
                    System.out.println(c);
                    object.notify();
                    try {
                        object.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                object.notify();
            }
        }).start();

        new Thread(() -> {
            synchronized (object) {
                t2Start = true;
                for (char c : a2) {
                    System.out.println(c);
                    object.notify();
                    try {
                        object.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                object.notify();
            }
        }).start();
    }
}
