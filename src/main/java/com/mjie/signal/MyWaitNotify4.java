package com.mjie.signal;
/**
 * 不要使用全局的锁对象或者字符串对象
 */
public class MyWaitNotify4 {
    private MonitorObject monitorObject = new MonitorObject();

    private boolean hasNotify = false;
    /**
     * 用while替代if，避免虚假唤醒
     */
    public void doWait() {
        synchronized (monitorObject) {
            while (!hasNotify) {
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
