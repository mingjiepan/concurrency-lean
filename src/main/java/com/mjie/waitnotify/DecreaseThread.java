package com.mjie.waitnotify;

public class DecreaseThread extends Thread {
    private MyCounter myCounter;
    public DecreaseThread(MyCounter myCounter) {
        this.myCounter = myCounter;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            myCounter.decrease();
        }
    }
}
