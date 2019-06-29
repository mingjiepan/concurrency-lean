package com.mjie.synchronize;

/**
 * 对于synchronized关键字修饰方法来说，并没有出现monitorenter与monitorext指令，而是出现一个ACC_SYNCHRONIZED标志
 * jvm使用了ACC_SYNCHRONIZED访问标志来区分一个方法是否为同步方法；当方法被调用时，调用指令会检查该方法是否拥有ACC_SYNCHRONIZED标志；
 * 如果有，那么执行线程将会先持有方法所在对象的monitor对象，然后再去执行方法体；在该方法执行期间，其他任何线程均无法获取到这个monitor对象。
 * 当线程执行完该方法后，它会释放掉这个monitor对象。
 */
public class Test2 {
    public synchronized void method() {
        System.out.println("hello world");
    }
}
