package com.mjie.pool;

import java.util.concurrent.CompletableFuture;

public class FutureTest3 {
    public static void main(String[] args) throws Exception{
        CompletableFuture.supplyAsync(() -> {
            System.out.println("hello world");
            int a = 1 / 1;
            return a;
        }).whenComplete((result, ex) -> {
            if (ex != null) {
                System.out.println("execute error");
                ex.printStackTrace();
            } else {
                System.out.println("result = " + result);
            }
        });

        System.out.println("-----------");

        CompletableFuture<Integer> completableFuture = CompletableFuture.supplyAsync(() -> {
            System.out.println("hello world");
            int a = 1 / 1;
            return a;
        }).handle((result, ex) -> {
            if (ex == null) {
                return result + 10;
            } else {
                System.out.println("execute error");
                return null;
            }
        });

        System.out.println(completableFuture.get());

    }
}
