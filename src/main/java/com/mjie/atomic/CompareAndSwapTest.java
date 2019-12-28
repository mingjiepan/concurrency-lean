package com.mjie.atomic;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 并发线程时会出问题
 */
class CAS1 {
    private boolean locked = false;
    public boolean lock() {
        if (!locked) {
            locked = true;
            return true;
        }
        return false;
    }
}

/**
 * 通过synchronized关键字实现compare and swap
 * 先判断locked是否为期望的false，若是将locked置为true，并返回
 */
class CAS2 {
    private boolean locked = false;
    public synchronized boolean lock() {
        if (!locked) {
            locked = true;
            return true;
        }
        return false;
    }
}

/**
 * 利用底层硬件技术，实现CAS
 */
class CAS3 {
    private AtomicBoolean atomicBoolean = new AtomicBoolean(false);

    public boolean lock() {
        return atomicBoolean.compareAndSet(false, true);
    }
}

public class CompareAndSwapTest {
    public static void main(String[] args) {

    }
}
