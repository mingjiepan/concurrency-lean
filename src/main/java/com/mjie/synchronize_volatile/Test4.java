package com.mjie.synchronize_volatile;

public class Test4 {
    private Object object = new Object();
    public  void add() {
        synchronized (object) {
            System.out.println("hello world");
        }
    }
    public void decrease() {
        try {
            synchronized (object) {
                System.out.println("welcome");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    public static void main(String[] args) {
        Test4 test4 = new Test4();
        test4.add();
        test4.decrease();
    }
}
