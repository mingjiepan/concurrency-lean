package com.mjie.waitAndnotify;

/**
 * 在调用wait方法时，线程必须要持有被调用对象的锁，当调用wait方法后，线程就会释放掉该对象的锁（monitor）。
 * 在调用Thread类的sleep方法时，线程是不会释放掉对象的锁（monitor）
 */
public class Test1 {
    public static void main(String[] args) throws Exception {
        Object object = new Object();
        synchronized (object) {
            object.wait();
        }
    }
}