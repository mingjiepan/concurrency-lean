package com.mjie.waitnotify_view1;

public class MyCounter {

    private int count;

    public synchronized void increase() {
        while (count != 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        count++;
        System.out.println(count);
        notifyAll();
    }

    public synchronized void decrease() {
        while (count == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        count--;
        System.out.println(count);
        notifyAll();
    }
}
