package com.mjie.aqs_view;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class T13_WithoutVolatile2 {
    //加volatile，使t1对list的修改对t2可见(这个是行不通的，因为volatile修饰的是引用类型，引用类型始终保持不变)
//    private volatile List<Object> list = new ArrayList<>();

    //使用原生的synchronized关键字
    private List<Object> list = Collections.synchronizedList(new ArrayList<>());
    private void add(Object str) {
        list.add(str);
    }

    private int size() {
        return list.size();
    }

    public static void main(String[] args) {

        T13_WithoutVolatile2 support2 = new T13_WithoutVolatile2();

        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                support2.add(new Object());
                System.out.println("add " + i);
                /*try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/
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
