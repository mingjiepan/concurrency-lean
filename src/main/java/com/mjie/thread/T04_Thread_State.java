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
    }
}
