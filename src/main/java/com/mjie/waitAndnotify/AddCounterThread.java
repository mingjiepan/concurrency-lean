package com.mjie.waitAndnotify;

public class AddCounterThread extends Thread {
    private MyCounter myCounter;
    public AddCounterThread(MyCounter myCounter) {
        this.myCounter = myCounter;
    }

    @Override
    public void run() {
        synchronized (myCounter){
            while (myCounter.count == 0) {
                System.out.println(++myCounter.count);
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
