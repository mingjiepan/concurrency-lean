package com.mjie.synchronize;

public class Test8 {
    private Object object = new Object();

    public void method() {
        synchronized (object) {
            System.out.println("hello");
        }

        synchronized (object) {
            System.out.println("world");
        }

        synchronized (object) {
            System.out.println("welcome");
        }
    }
}
