package com.mjie.referenceType;
import java.lang.ref.WeakReference;
/**
 * 弱引用
 * 当发生GC时，弱引用对象便会被垃圾收集器回收。典型场景ThreadLocal
 *
 * ThreadLocal<Integer> tl = new ThreadLocal();
 * tl.set(2);
 *
 * 当代码执行到上面这行时，堆的threadLocal对象会有2个引用指向它，一个是tl。
 * 另一个是  当前线程的ThreadLocalMap的Entry对象的key指向它。
 *
 * 有一个点需要注意的是entry对象key是一个WeakReference，防止内存泄露，因为假如是强引用的话，那么当tl为null时，threadlocal
 * 对象还是会有引用指向它，那么threadlocal便不会被回收，而设置成若引用时，会在发生GC时自动回收，
 *
 * 但是这边还有一个注意点，虽然弱引用能使threadlocal被及时回收，但是entry的value对象无法被访问到，也可能导致内存泄露
 * 因此，在使用threadLocal对象时，在使用完set后，最好remove调。避免value导致的内存泄露
 */
public class T03_WeakReference {
    public static void main(String[] args) {
        WeakReference<M> m = new WeakReference<>(new M());

        System.out.println(m.get());
        System.gc();
        System.out.println(m.get());

        System.out.println("-----------");

        ThreadLocal<M> tl = new ThreadLocal<>();
        tl.set(new M());
        tl.remove();

    }
}
