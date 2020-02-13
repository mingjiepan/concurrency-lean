package com.mjie.pool;

import java.util.concurrent.CompletableFuture;

public class FutureTest5 {
    public static void main(String[] args) {

       /* CompletableFuture<Void> helloWorld = CompletableFuture.runAsync(() -> System.out.println("hello world"));
        CompletableFuture<Void> welcome = CompletableFuture.runAsync(() -> System.out.println("welcome"));


        CompletableFuture.allOf(helloWorld, welcome).whenComplete((a, b) -> {
            System.out.println("execute success");
        });*/


        System.out.println("--------");


        CompletableFuture<Integer> async = CompletableFuture.supplyAsync(() -> {
            try {
            Thread.sleep(3000);
            } catch (Exception ex) {

            }
            return 20;
        });

        CompletableFuture<Integer> async1 = CompletableFuture.supplyAsync(() -> 100);
        CompletableFuture<Integer> async2 = CompletableFuture.supplyAsync(() -> 200);

        CompletableFuture<Void> complete = CompletableFuture.allOf(async, async1, async2).whenComplete((result, ex) -> {
            System.out.println("success = " + result);
        });

        complete.join();

    }
}
