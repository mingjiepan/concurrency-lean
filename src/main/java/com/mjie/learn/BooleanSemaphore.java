package com.mjie.learn;

public class BooleanSemaphore {
    private boolean isSignal = false;
    /**
     * 发送信号
     */
    public synchronized void take() {
        isSignal = true;
        this.notify();
    }
    /**
     * 等待信号
     */
    public synchronized void release() {
        while (!isSignal) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        this.isSignal = false;
    }
}

class SendThread {

    private BooleanSemaphore semaphore;
    public SendThread(BooleanSemaphore booleanSemaphore) {
        this.semaphore = booleanSemaphore;
    }

    public void run() {
        while (true) {
            //do something , then signal
            semaphore.take();
        }
    }
}

class ReceiveThread {
    private BooleanSemaphore semaphore;
    public ReceiveThread(BooleanSemaphore booleanSemaphore) {
        this.semaphore = booleanSemaphore;
    }

    public void run() {
        while (true) {
            semaphore.release();
            //receive signal, then do something
        }
    }
}