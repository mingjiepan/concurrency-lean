package com.mjie.learn;


/**
 * 若程序像这样写的话，有可能出现丢失信号的情况
 * 比如，线程B调用了notify，如果此时没有线程正在等待线程的锁的时候，那么程序是没有问题的
 * 如果在线程B调用了notify之后，线程A调用了Wait方法。并且之后没有其他线程再调用notify方法了，那么线程A将永远的睡眠下去。不会醒来。
 */
public class MyWaitNotify2 {
    private MonitorObject monitorObject = new MonitorObject();

    public void doWait() {

        synchronized (monitorObject) {
            try {
                monitorObject.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void doNotify() {
        synchronized (monitorObject) {
            monitorObject.notify();
        }
    }
}
