package com.mjie.synchronize;

import org.openjdk.jol.info.ClassLayout;
/**
 *
 * java -XX:+PrintFlagsFinal
 *
 *
 * 早期的synchronized锁是重量级锁，因为申请锁资源需要经过内核态kernel，系统调用
 *
 * 用户空间锁 重量级锁
 *
 * 偏向锁 自旋锁 都是用户空间完成
 * 重量级锁是需要向内核申请
 *
 * 为何会有偏向锁？
 * 多数synchronized方法，在很多情况下，只有一个线程在运行
 * 例如：
 * StringBuffer中的一些sync方法
 * Vector中的一些sync方法
 *
 * //偏向锁默认是开启的，在JVM虚拟机启动4秒后，才启动  -XX:BisedLockingStartupDelay=0 设置偏向锁的启动延迟时间
 * 为什么？jvm启动过程中会有很多的线程在竞争资源，此时肯定存在多线程竞争锁的情况，因为启动偏向锁效率更低，因为需要锁撤销，再升级
 *
 * 偏向锁 一定比自旋锁的效率高么？
 * 不一定，因为偏向锁需要先锁撤销，再变为自旋锁，如果明确知道多线程池竞争
 *
 *
 * synchronized 与 volatile 硬件级别实现
 *
 * lock cmpxchg  指令    lock addl
 *
 * MESI 缓存一致性协议  lock addl如何使其他cpu的缓存失效
 *
 *
 *
 *
 *
 */
public class ClassLayout1 {
    public static void main(String[] args) throws Exception {

        //睡眠后5秒，再开启偏向锁
        Thread.sleep(5000);

        Object o = new Object();
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
        System.out.println("------");

        //markword变了，包含了锁信息、GC信息、hashcode
        synchronized (o) {
            System.out.println(ClassLayout.parseInstance(o).toPrintable());
        }
    }
}
