package com.mjie.synchronize_volatile;


/**
 * CPU乱序执行，指令重排序
 *
 * 系统底层如何实现数据一致性
 * MESI如果能解决，就使用MESI
 * 如果不能，就锁总线
 *
 * 系统底层如何保证有序性
 * 1. 内存屏障 sfence mfence lfence 等系统原语（屏障两边的指令不可以重排）
 * 2. 锁总线
 */
public class Volatile_reorder {
    static int x,y = 0;
    static int a,b = 0;

    public static void main(String[] args) throws Exception {
        int i = 0;
        for(;;) {
            i++;
            x = 0; y = 0;
            a = 0; b = 0;

            Thread one = new Thread(() -> {
                a = 1;
                x = b;
            });
            Thread other = new Thread(() -> {
                b = 1;
                y = a;
            });

            one.start();other.start();
            one.join();other.join();
            String result = "第" + i + "次 （" + x + "," + y + ")";
            if (x == 0 && y == 0) {
                System.out.println(result);
                break;
            } else {
            }
        }
    }
}
