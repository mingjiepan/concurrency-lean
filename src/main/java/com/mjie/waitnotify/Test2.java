package com.mjie.waitnotify;

public class Test2 {
    public static void main(String[] args) {
        MyCounter myCounter = new MyCounter();
        IncreaseThread increaseThread = new IncreaseThread(myCounter);
        IncreaseThread increaseThread2 = new IncreaseThread(myCounter);
        IncreaseThread increaseThread3 = new IncreaseThread(myCounter);
        DecreaseThread decreaseThread = new DecreaseThread(myCounter);
        DecreaseThread decreaseThread2 = new DecreaseThread(myCounter);

        increaseThread.start();
        increaseThread2.start();

        decreaseThread.start();
        decreaseThread2.start();
    }
}
