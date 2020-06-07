package com.mjie.aqs_view2;

public class T17_CAS {

    enum ReadToRun {T1, T2}

    static volatile ReadToRun readToRun = ReadToRun.T1;

    static Thread t1, t2 = null;

    public static void main(String[] args) {
        char[] a1 = "1234567890".toCharArray();
        char[] a2 = "abcdefghij".toCharArray();

        t1 = new Thread(() -> {
            for (char c : a1) {
                while (readToRun != ReadToRun.T1) {
                }
                System.out.println(c);
                readToRun = ReadToRun.T2;
            }
        }, "t1");

        t2 = new Thread(() -> {

            for (char c : a2) {
                while (readToRun != ReadToRun.T2) {
                }
                System.out.println(c);
                readToRun = ReadToRun.T1;
            }

        }, "t2");

        t1.start();
        t2.start();
    }
}
