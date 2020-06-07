package com.mjie.atomic;

import sun.misc.Unsafe;

public class HelloUnSafe {
    public static void main(String[] args) throws Exception {
        Unsafe unsafe = Unsafe.getUnsafe();
        Person person = (Person)unsafe.allocateInstance(Person.class);
        person.m = 20;
        System.out.println(person.m);
    }
    static class Person {
        int m = 1;
    }
}
