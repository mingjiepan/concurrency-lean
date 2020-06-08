package com.mjie.waitnotify_view1;

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
