package com.mjie.pool;

import com.sun.xml.internal.ws.util.CompletedFuture;

import java.util.concurrent.CompletableFuture;

public class FutureTest1 {
    public static void main(String[] args) throws Exception {
        CompletableFuture<Void> runAsync = CompletableFuture.runAsync(() -> {
            System.out.println("hello world");
        });

        CompletableFuture<Integer> supplyAsync = CompletableFuture.supplyAsync(() -> {
            System.out.println("welcome");
            return 10;
        });

//        supplyAsync.complete(1000);

        Integer integer = supplyAsync.get();
        System.out.println(integer);
    }
}