package com.mjie.thread;

/**
 * 线程状态  new  ready running terminated
 *
 * yield()  running -> ready
 *
 * TimeWaiting（sleep(time)、wait(time) join(time) parkNanos() parkUntil()）
 * Waiting (wait() join() LockSupport.park())
 * blocked
 *
 * 线程要正常结束，不要试图手动关闭线程
 */
public class T04_Thread_State {
    public static void main(String[] args) {
        Thread t = new Thread();

        System.out.println(t.getState());

        t.start();

        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(t.getState());
    }
}
