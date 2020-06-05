package com.mjie.synchronize_volatile;
/**
 *  java memory model（JMM）java内存模型，
 *  每个线程操作内存上的变量时，会先将数据拷贝一份到自己的缓存中，然后进行修改，此时修改后的数据不会立即写回主内存中，因此对其他线程是不可见的。
 *  若是变量加上volatile变量，那么线程每次对变量的读写都会立即反应到主内存中
 *
 *  JSR4中内存屏障(JVM层级的规范要求，具体实现看具体虚拟机)
 *  4种内存屏障
 *  LoadLoad StoreStore LoadStore StoreLoad
 *
 *   SS
 *   volatile 写
 *   SL
 *
 *   LL
 *   volatile 读
 *   LS
 *
 *   happen-before 规范 （JVM规定重排序必须准守的规则） 8种情况不可以重排序，也就说jvm实现这8种情况下，必须加内存屏障
 *
 *   as if serial 不管如何重排序，单线程执行结果不会改变
 */
public class Volatile1 {
    //若没加volatile，那么线程将永远也不会退出
//    private static boolean flag = true;

    //加上关键字后，主线程对flag的修改对start线程是可见的
    private static volatile boolean flag = true;

    public static void main(String[] args)throws Exception {

        new Thread(() -> {
            while (flag) {

            }

            System.out.println("new thread end");
        }, "start").start();

        Thread.sleep(1000);

        //修改这个值对start线程不可用
        flag = false;
    }
}
