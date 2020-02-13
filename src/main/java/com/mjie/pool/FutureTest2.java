package com.mjie.pool;

import java.util.concurrent.CompletableFuture;

public class FutureTest2 {
    public static void main(String[] args) {
        System.out.println("hello");

        CompletableFuture.supplyAsync(() -> {
            System.out.println("stage1，" + Thread.currentThread().getName());
            return 10;
        }).thenApply(a -> {
            System.out.println("stage2, " + Thread.currentThread().getName());
            return a + 10;
        }).thenAcceptAsync(result -> {
            System.out.println("stage3, result = " + result + ", " + Thread.currentThread().getName());
        });

        System.out.println("world");
    }
}
