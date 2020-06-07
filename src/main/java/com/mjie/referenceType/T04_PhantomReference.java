package com.mjie.referenceType;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.LinkedList;
import java.util.List;
/**
 * 虚引用   管理堆外内存
 * 虚引用在程序中调get方法时，始终返回的是null
 * Java nio的zero copy 在heap上的DirectByteBuffer对象。指向对外的指针地址，对象内部又有虚引用，
 * 当directByteBuffer对象被垃圾回收器回收时，对象关联的虚引用会加入到一个队列中，GC垃圾有专门的线程
 * 来检测队列，若队列有值了，就会回收调堆外内存，从而避免内存泄露
 */
public class T04_PhantomReference {
    private static final List<Object> LIST = new LinkedList<>();
    private static final ReferenceQueue<M> QUEUE = new ReferenceQueue<>();

    public static void main(String[] args) {

        java.lang.ref.PhantomReference<M> phantomReference = new java.lang.ref.PhantomReference<>(new M(), QUEUE);

        new Thread(() -> {
            while (true) {
                LIST.add(new byte[1024 * 1024]);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }
                System.out.println(phantomReference.get());

            }
        }).start();

        new Thread(() -> {
            while (true) {
                Reference<? extends M> poll = QUEUE.poll();

                if (poll != null) {
                    System.out.println("---虚引用对象被JVM回收了----" + poll);
                }
            }
        }).start();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
