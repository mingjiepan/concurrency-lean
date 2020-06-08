package com.mjie.learn;


public class BoundSemaphore {

    private int counter = 0;
    private int bound = 0;

    public BoundSemaphore(int bound) {
        this.bound = bound;
    }

    public synchronized void take() {
        while (this.counter == bound) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        this.counter++;
        this.notify();
    }
    public synchronized void release() {
        while (this.counter == 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.counter--;
        this.notify();
    }


    public static void main(String[] args) {
        BoundSemaphore boundSemaphore = new BoundSemaphore(1);
        boundSemaphore.take();
        try {
            //执行需要同步的代码
        } finally {
            boundSemaphore.release();
        }
    }
}
