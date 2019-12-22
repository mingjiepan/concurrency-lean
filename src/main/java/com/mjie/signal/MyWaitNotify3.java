package com.mjie.signal;

public class MyWaitNotify3 {

    private MonitorObject monitorObject = new MonitorObject();

    private boolean hasNotify = false;

    /**
     * 用IF的话，有可能会出现虚假唤醒
     */
    public void doWait() {
        synchronized (monitorObject) {
            if (!hasNotify) {
                try {
                    monitorObject.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void doNotify() {
        synchronized (monitorObject) {
            hasNotify = true;
            monitorObject.notify();
        }
    }
}
