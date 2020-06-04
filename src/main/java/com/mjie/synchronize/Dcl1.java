package com.mjie.synchronize;

/**
 * DCL 问题
 *
 * 通过字节码指令看生成一个对象的过程
 *
 *
 * JSR4中内存屏障(JVM层级的规范要求，具体实现看具体虚拟机)
 *
 * LoadLoad StoreStore LoadStore StoreLoad
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
 *
 *
 *
 */
public class Dcl1 {
    int m = 8;

    public static void main(String[] args) {
        Dcl1 d = new Dcl1();
    }
}
