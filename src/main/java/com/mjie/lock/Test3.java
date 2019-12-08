package com.mjie.lock;


import java.util.Arrays;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

/*
    传统上，我们可以通过synchronized关键字+wait+notify/notifyAll来实现多个线程之间的协调与通信，整个过程都是由jvm来
    帮助我们实现的，开发者无需了解底层的实现细节

    从jdk5开始，并发包提供了Lock，Condition（await与sign/signAll）来实现多个线程之间的协调与通信，整个过程都是由开发者来
    控制的，而且相比传统方式，更加灵活，功能更加强大

    Thread.sleep与await （或是Object的wait方法）的本质区别：sleep方法本质上不会释放锁，而await会释放锁，并且在signal后，还需要重写
    获得锁才能继续执行（该行为与Object的wait方法完全一致）
 */
class MyTest3 {

    private String[] strArr = new String[10];

    private Lock lock = new ReentrantLock();

    private int takeIndex;

    private int putIndex;

    private int size;

    private Condition putCondition = lock.newCondition();

    private Condition takeCondition = lock.newCondition();

    public void put(String str) {
        try {

            lock.lock();

            while (size == strArr.length) {
                putCondition.await();
            }

            strArr[putIndex] = str;

            if (++putIndex == strArr.length) {
                putIndex = 0;
            }

            size++;

            takeCondition.signal();

            System.out.println("put: " + Arrays.toString(strArr));

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public String take() {
        String takeResult = null;
        try {
            lock.lock();
            while (size == 0) {
                takeCondition.await();
            }

            if (takeIndex == strArr.length) {
                takeIndex = 0;
            }

            takeResult = strArr[takeIndex];
            strArr[takeIndex] = null;
            if (++takeIndex == strArr.length) {
                takeIndex = 0;
            }

            size--;
            putCondition.signal();

            System.out.println("take: " + Arrays.toString(strArr));

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            lock.unlock();
        }
        return takeResult;
    }
}


public class Test3 {

    public static void main(String[] args) {
        MyTest3 myTest3 = new MyTest3();

        for (int i = 0; i < 50; i++) {
            int finalI = i;
            new Thread(() -> myTest3.put("hello " + finalI)).start();
        }

        IntStream.range(0, 30).forEach(item -> new Thread(() -> {
            String result = myTest3.take();
            System.out.println(result);
        }).start());
    }
}
