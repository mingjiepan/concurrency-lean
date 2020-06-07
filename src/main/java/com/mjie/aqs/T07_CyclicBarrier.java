package com.mjie.aqs;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 使用场景
 *
 * 复杂场景
 * 1. 数据库
 * 2. 网络
 * 3. 文件
 *
 * 使用CyclicBarrier并发执行
 * 1. 一个线程执行数据库操作
 * 2. 一个线程执行网络操作
 * 3. 一个线程执行文件操作
 * 等三个执行完了，再执行后续的
 */
public class T07_CyclicBarrier {
    public static void main(String[] args) {

        CyclicBarrier barrier = new CyclicBarrier(20, () -> {
            System.out.println("满人，发车");
        });

        for (int i = 0; i < 100; i++) {
            int finalI = i;
            new Thread(() -> {
                try {
                    barrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
                System.out.println("after i " + finalI);
            }).start();
        }
    }
}
