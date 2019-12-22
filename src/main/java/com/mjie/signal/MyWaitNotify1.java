package com.mjie.signal;

/**
 * 通过空循环来等待数据的处理
 * 缺点：消耗CPU的资源
 */
public class MyWaitNotify1 {
    private MySignal mySignal = new MySignal();

    public void processData() {
        while (!mySignal.hasDataToProcess()) {
            //无数据处理时
        }

        //有数据处理了。。。

        //处理完了，将是否有数据处理设置为false
        mySignal.setHasDataToProcess(false);
    }
    public void setData(Object obj) {
        while (mySignal.hasDataToProcess()) {
        }

        //没数据处理，添加数据

        mySignal.setHasDataToProcess(true);
    }
}

/**
 * 通过共享资源来实现线程间的通信
 */
class MySignal {
    private boolean hasDataToProcess = false;

    public synchronized boolean hasDataToProcess() {
        return this.hasDataToProcess;
    }

    public synchronized void setHasDataToProcess(boolean hasDataToProcess) {
        this.hasDataToProcess = hasDataToProcess;
    }
}