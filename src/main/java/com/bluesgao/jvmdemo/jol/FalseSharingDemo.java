package com.bluesgao.jvmdemo.jol;

import sun.misc.Contended;

//伪共享
//避免伪共享的主要思路就是让不相干的变量不要出现在同一个缓存行中
//@sun.misc.Contended 默认使用这个注解是无效的，需要在JVM启动参数加上-XX:-RestrictContended才会生效
//字段填充
/**
 * 一，没有处理伪共享
 * 耗时ms:3244
 * pointer.x:100000000
 * pointer.y:100000000
 *
 * 二，处理伪共享
 * ①使用字段填充
 * 耗时ms:904
 * pointer.x:100000000
 * pointer.y:100000000
 *
 * ② @Contended注解
 * 耗时ms:749
 * pointer.x:100000000
 * pointer.y:100000000
 */
public class FalseSharingDemo {
    public static void main(String[] args) throws InterruptedException {
        testPointer(new Pointer());
    }

    private static void testPointer(Pointer pointer) throws InterruptedException {
        long start = System.currentTimeMillis();
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 100_000_000; i++) {
                pointer.x++;
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 100_000_000; i++) {
                pointer.y++;
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("耗时ms:"+ (System.currentTimeMillis() - start) );
        System.out.println("pointer.x:" + pointer.y);
        System.out.println("pointer.y:" + pointer.y);
    }
}

class Pointer {
    @Contended
    volatile long x;
    //字段填充
    //long p1, p2, p3, p4, p5, p6, p7;
    volatile long y;
}

