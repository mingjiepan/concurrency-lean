package com.mjie.synchronize_volatile;

/**
 * 锁消除
 * JIT编译器（Just in Time 编译器）可以在动态编译器同步代码时，使用一种叫做逃逸分析的技术、来通过该项技术判别程序中所使用的锁对象是否只被一个线程所使用，
 * 而没有散布到其他线程当中；如果情况就是这样的话，
 * 那么JIT编辑器在编译这个同步代码时就不会生成synchronized关键字所标识的锁的申请与释放机器码，从而消除了锁的
 */

public class Test7 {

//    private Object object = new Object();


    public void method() {

        Object object = new Object();

        synchronized (object) {
            System.out.println("hello world");
        }
    }


}
