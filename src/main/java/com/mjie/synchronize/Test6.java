package com.mjie.synchronize;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

public class Test6 {

    private ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();


    public void add(String key) {

        Integer value = map.get(key);

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (value == null) {
            map.put(key, 1);
        } else {
            map.put(key, value + 1);
        }

    }


    public static void main(String[] args) {
        Test6 test6 = new Test6();

        CompletableFuture.runAsync(() -> test6.add("hello"));
        CompletableFuture.runAsync(() -> test6.add("hello"));

        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Integer hello = test6.map.get("hello");
        System.out.println(hello);
    }
}
