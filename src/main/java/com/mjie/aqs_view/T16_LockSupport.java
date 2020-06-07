package com.mjie.aqs_view;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class T16_LockSupport {

    private List<Object> list = new ArrayList<>();
    private void add(Object str) {
        list.add(str);
    }
    private int size() {
        return list.size();
    }

    static Thread t1, t2 = null;

    public static void main(String[] args) {
        T16_LockSupport c = new T16_LockSupport();

        t1 = new Thread(() -> {
            System.out.println("t1 启动");
            for (int i = 0; i < 10; i++) {
                c.add(new Object());
                System.out.println("add " + i);

                if (c.size() == 5) {
                    LockSupport.unpark(t2);
                    LockSupport.park();
                }
            }
        }, "t1");

        t2 = new Thread(() -> {
            System.out.println("t2 启动");
            LockSupport.park();
            System.out.println("t2 结束");
            LockSupport.unpark(t1);
        }, "t2");

        t1.start();
        t2.start();
    }
}
