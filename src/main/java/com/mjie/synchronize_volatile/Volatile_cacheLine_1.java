package com.mjie.synchronize_volatile;

import java.util.concurrent.CountDownLatch;

/**
 *  一个CPU 有多个核心。
 *  一个CPU会共享一块缓存（L3）
 *  每个核心有自己的缓存（L1, L2）
 *
 *  缓存行对齐
 *  缓存行64个字节是CPU同步数据的基本单位，缓存行隔离会比共享效率要高
 *  CPU核心（线程）操作内存（主存）的数据时，会将内存的数据以cache line（缓存行）的形式，一次性读取一块（64个字节）内存数据（比如需要读取变量a，
 *  那么如果变量a所在的内存块（缓存行）还有其他变量，也会一并被读取cpu缓存中）到自己的核心缓存中。
 *
 *  如果两个线程操作的变量来自同一个cache line中，并且变量被修饰了volatile关键字，那么各自线程在修改自己的变量并同步到主内存的cache line时，要与另一个线程做好同步操作。
 *  如果两个线程操作的变量不是来自于同一个cache line中，那么即使变量修饰了volatile关键字，各自线程只需要将变量值及时同步到自己的cache line中即可。
 *
 *  缓存一致性MESI（modified exclusive share invalid）
 *
 *  用于同一缓存行在多个CPU间保持数据一致性，该协议是intel的，并不是所有CPU都支持这个协议
 */
public class Volatile_cacheLine_1 {

    private static long count = 100000000L;

    private static class T {
        public volatile long x = 0L;
    }

    public static T[] arr = new T[2];
    static {
        arr[0] = new T();
        arr[1] = new T();
    }


    public static void main(String[] args) throws Exception {

        CountDownLatch countDownLatch = new CountDownLatch(2);

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < count; i++) {
                arr[0].x = i;
            }
            countDownLatch.countDown();
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < count; i++) {
                arr[1].x = i;
            }
            countDownLatch.countDown();
        });

        long timeMillis = System.currentTimeMillis();
        t1.start();
        t2.start();
        countDownLatch.await();
        System.out.println("执行耗时 " + (System.currentTimeMillis() - timeMillis));
    }
}
