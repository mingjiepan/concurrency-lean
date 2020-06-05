package com.mjie.synchronize_volatile;
/**
 * 当使用synchronized关键字来修饰代码块时，字节码层面上是通过monitorenter与monitorexit指令来实现的锁的获取与释放动作。
 * 当线程进入到monitor指令后，线程将会持有monitor对象，退出monitorenter指令（执行monitorexit指令）后，线程将会释放monitor对象。
 */
public class Test1 {

    private Object object = new Object();

    public void method() {
        synchronized (object) {
            System.out.println("hello world");
            throw new RuntimeException();
        }
    }

    public void method2() {
        synchronized (object) {
            System.out.println("welcome");
        }
    }
}
