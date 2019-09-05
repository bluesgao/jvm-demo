package com.bluesgao.jvmdemo.jol;

import org.openjdk.jol.info.ClassLayout;

/**
 * 输出对象头信息
 * https://www.cnblogs.com/LemonFive/p/11246086.html
 */
public class ObjectHeaderDemo {
    static class A {
        boolean flag = true;
    }

    public static void main(String[] args) {
        A a = new A();
        System.out.println(ClassLayout.parseInstance(a).toPrintable());
    }
}
