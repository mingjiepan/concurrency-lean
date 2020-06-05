package com.mjie.synchronize_volatile;

/**
 * 查看字节码，看对象的生成过程
 */
class Dcl1 {
    int m = 8;
    public static void main(String[] args) {
        Dcl1 dcl1 = new Dcl1();
    }
}

/**
 *
 */
public class Volatile_reorder_dcl {
    //若getInstance没被调，dcl2还是会被new出来，占据内存资源
    private static final Volatile_reorder_dcl VOLATILEREORDERDCL = new Volatile_reorder_dcl();

    private Volatile_reorder_dcl() {}

    public static Volatile_reorder_dcl getInstance() {
        return VOLATILEREORDERDCL;
    }
}

class Dcl3 {
    private static Dcl3 dcl3 = null;

    private Dcl3() {}

    public static Dcl3 getInstance() throws InterruptedException {
        if (dcl3 == null) {
            Thread.sleep(1000);
            dcl3 = new Dcl3();
        }
        return dcl3;
    }
}

class Dcl4 {
    private static Dcl4 dcl4 = null;

    private Dcl4() {}

    public static Dcl4 getInstance() {
        if (dcl4 == null) {
            synchronized (Dcl4.class) {
                dcl4 = new Dcl4();
            }
        }
        return dcl4;
    }
}

class Dcl5 {
    private static Dcl5 dcl5 = null;

    private Dcl5() {}

    public static Dcl5 getInstance() {
        if (dcl5 == null) {//加上这步，效率更高，不用每次都来获取锁
            synchronized (Dcl5.class) {
                if (dcl5 == null) {
                    dcl5 = new Dcl5();
                }
            }
        }
        return dcl5;
    }
}