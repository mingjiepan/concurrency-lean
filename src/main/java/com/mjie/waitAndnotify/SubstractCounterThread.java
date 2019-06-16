package com.mjie.waitAndnotify;

public class SubstractCounterThread extends Thread {
    private MyCounter myCounter;

    public SubstractCounterThread(MyCounter myCounter) {
        this.myCounter = myCounter;
    }

    @Override
    public void run() {
        synchronized (myCounter){
            while (myCounter.count == 1) {
                System.out.println(--myCounter.count);
                myCounter.notifyAll();
                try {
                    myCounter.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
