package com.mjie.synchronize;

public class Test7 {

//    private Object object = new Object();


    public void method() {

        Object object = new Object();

        synchronized (object) {
            System.out.println("hello world");
        }
    }


}
