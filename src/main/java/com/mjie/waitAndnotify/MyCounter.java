package com.mjie.waitAndnotify;

public class MyCounter {
    public int count;

    public static void main(String[] args) {
        MyCounter myCounter = new MyCounter();

        AddCounterThread addThread1 = new AddCounterThread(myCounter);
        AddCounterThread addThread2 = new AddCounterThread(myCounter);
        AddCounterThread addThread3 = new AddCounterThread(myCounter);

        SubstractCounterThread substractThread = new SubstractCounterThread(myCounter);

        addThread1.start();
        addThread2.start();
        addThread3.start();

        substractThread.start();
    }
}
