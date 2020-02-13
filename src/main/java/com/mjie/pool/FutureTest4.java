package com.mjie.pool;

import com.sun.xml.internal.ws.util.CompletedFuture;

import java.util.concurrent.CompletableFuture;

public class FutureTest4 {
    public static void main(String[] args) throws Exception{
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("stage1");
            try {
                Thread.sleep(2000);
            } catch (Exception ex) {

            }
            System.out.println("stage1 end");
            int a = 1 / 0;
            return a;
        }).whenComplete((result, ex) -> {
            if (ex != null) {
                System.out.println("execute error");
            } else {
                System.out.println("execute success");
            }
        }).exceptionally(ex -> {
            System.out.println("exceptionally");
            return 0;
        });

        System.out.println("main end before");

        Integer integer = future.get();
        System.out.println("main result = " + integer);
    }
}
