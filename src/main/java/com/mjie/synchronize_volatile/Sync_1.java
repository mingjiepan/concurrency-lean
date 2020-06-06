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
 */
public class Sync_1 {
}
