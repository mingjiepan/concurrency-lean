package com.mjie.referenceType;

import java.lang.ref.SoftReference;

/**
 * 软引用(主要用于缓存)
 * 队内存最大引用
 * -Xmx20M
 */
public class T02_SoftReference {
    public static void main(String[] args) {

        SoftReference<byte[]> m = new SoftReference<>(new byte[1024]);

        System.out.println(m.get());
        System.gc();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(m.get());
        byte[] b = new byte[15 * 1024 * 1024];

        System.out.println(m.get());
    }
}
