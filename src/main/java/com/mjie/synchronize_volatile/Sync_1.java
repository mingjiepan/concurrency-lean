package com.mjie.synchronize_volatile;
/**
 * synchronized 实现过程
 * 1. Java代码：synchronized
 * 2. monitorenter monitorexit（修饰方法时，ACC_SYNCHRONIZED标志）
 *    获取到对象的mibitor对象
 * 3. 执行过程中自动升级
 * 4. lock comxchg
 *
 * volatile实现过程
 * 1. volatile
 * 2. ACC_VOLATILE
 * 3. jvm内存屏障
 *
 *
 * 关于Lock与synchronized关键字在锁的处理上的重要差别
 * 1.锁的获取方式：前者是通过程序代码的方式由开发者手工获取，后者是通过JVM来获取（无需开发者干预）
 *
 * 2.具体实现方式：前者是通过Java代码的方式来实现，后者是通过JVM底层来实现（无需开发者关注）
 *
 * 3.锁的释放方式：前者务必通过unlock方法在finally块中手工释放，后者是通过JVM来释放
 *
 * 3.锁的具体类型：前者提供了多种，如公平锁、非公平锁，后者与前者均提供了可重入锁
 */
public class Sync_1 {
}
