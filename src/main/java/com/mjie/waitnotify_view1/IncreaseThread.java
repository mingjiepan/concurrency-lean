package com.mjie.waitnotify_view1;

public class IncreaseThread extends Thread {
    private MyCounter myCounter;
    public IncreaseThread(MyCounter myCounter) {
        this.myCounter = myCounter;
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            myCounter.increase();
        }
    }
}
