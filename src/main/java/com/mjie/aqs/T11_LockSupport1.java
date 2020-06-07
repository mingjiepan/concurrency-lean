package com.mjie.aqs;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class T11_LockSupport1 {
    public static void main(String[] args) {
        Thread t = new Thread(()-> {
            for (int i = 0; i < 10; i++) {
                System.out.println(i);
                if (i == 5) {
                    LockSupport.park();
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();

        try {
            TimeUnit.SECONDS.sleep(8);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("after 8 seconds");
        LockSupport.unpark(t);
    }
}