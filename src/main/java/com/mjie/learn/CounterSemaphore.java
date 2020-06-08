package com.mjie.learn;

public class CounterSemaphore {
    private int counter = 0;

    public synchronized void take() {
        this.counter++;
        this.notify();
    }

    public synchronized void release() {
        while (this.counter == 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.counter--;
    }
}
