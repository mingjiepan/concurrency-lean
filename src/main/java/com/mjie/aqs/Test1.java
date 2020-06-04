package com.mjie.aqs;


import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;
/*
        1.尝试获取对象的锁，如果获取不到（意味着其他线程持有了锁，并且尚未释放），那么就会进入到AQS的阻塞队列中
        2.如果获取到，那么根据锁是公平锁还是非公平锁来进行不同的处理
            2.1 如果是公平锁，那么线程会直接放置到AQS阻塞队列的末尾
            2.2 如果是非公平锁，那么线程会首先尝试进行CAS计算，如果成功，则直接获取锁；
                如果失败，则与公平锁的处理方式一致，被放置到阻塞队列末尾；
        3.当锁被释放时，那么底层会调用release方法对state成员变量值进行减1操作，如果剪1后，state值不为0，那么release执行完毕。如果减1后，
        state为0，则调用LockSupport的unpark方法唤醒该线程后的等待队列中的第一个后继线程，将其唤醒，使之能够获取到对象的锁（release时，对于公平锁与
        非公平锁的处理逻辑是一致的）；之所以调用release方法后state值可能不为0，原因在于ReentrantLock是可重入锁，表示线程可以多次调用Lock方法，导致
        每调用一次，state值都会加1
        对于ReentrantLock来说，所谓的上锁，本质就是对AQS的state成员变量的操作，对该成员变量+1，表示上锁，对该成员变量-1，表示释放锁。
 */
public class Test1 {
    private Lock lock = new ReentrantLock();
    public void method() {
        try {
            lock.lock();
            Thread.sleep(1000);
            System.out.println("method");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        Test1 test1 = new Test1();
        IntStream.range(0, 10).forEach(i -> {
            new Thread(test1::method).start();
        });
    }
}
