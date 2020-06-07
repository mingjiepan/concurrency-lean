package com.mjie.aqs_view;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class T12_WithoutVolatile1 {

    private List<Object> list = new ArrayList<>();

    private void add(Object str) {
        list.add(str);
    }

    private int size() {
        return list.size();
    }

    public static void main(String[] args) {

        T12_WithoutVolatile1 support2 = new T12_WithoutVolatile1();

        new Thread(() -> {

            for (int i = 0; i < 10; i++) {
                support2.add(new Object());
                System.out.println("add " + i);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }, "t1").start();

        new Thread(() -> {
            while (true) {
                if (support2.size() == 5) {
                    break;
                }
            }
            System.out.println("t2 结束");
        }, "t2").start();
    }
}
