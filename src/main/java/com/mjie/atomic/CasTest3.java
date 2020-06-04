package com.mjie.atomic;

import java.util.concurrent.atomic.AtomicStampedReference;

public class CasTest3 {
    public static void main(String[] args) {

        AtomicStampedReference<Integer> stampedReference = new AtomicStampedReference<>(1, 12);
        stampedReference.compareAndSet(1, 2, 12, 22);
        System.out.println(stampedReference.getReference());
    }
}
