package com.mjie.aqs_view;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class T14_NotifyHoldingLock {

    private List<Object> list = new ArrayList<>();
    private void add(Object str) {
        list.add(str);
    }
    private int size() {
        return list.size();
    }

    public static void main(String[] args) {
        T14_NotifyHoldingLock c = new T14_NotifyHoldingLock();
        final Object object = new Object();

        new Thread(() -> {

            synchronized (object) {

                System.out.println("t2 启动");

                if (c.size() != 5) {
                    try {
                        object.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                System.out.println("t2 结束");

                //t2结束后，唤醒t1
                object.notify();
            }



        }, "t2").start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(() -> {
            System.out.println("t1 启动");
            synchronized (object) {
                for (int i = 0; i < 10; i++) {
                    c.add(new Object());
                    System.out.println("add " + i);

                    if (c.size() == 5) {
                        object.notify();
                        try {
                            object.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }


                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }

        }, "t1").start();






    }
}
