package com.bluesgao.jvmdemo.jit;

/**
 *-XX:+PrintCompilation -XX:+UnlockDiagnosticVMOptions -XX:+PrintInlining -XX:+PrintFlagsFinal -XX:+PrintFlagsInitial
 -XX:+PrintCompilation 在控制台打印编译过程信息
 -XX:+UnlockDiagnosticVMOptions 解锁对JVM进行诊断的选项参数。默认是关闭的，开启后支持一些特定参数对JVM进行诊断
 -XX:+PrintInlining 将内联方法打印出来
 */
public class InlineDemo {

    private static int add1(int x1, int x2, int x3, int x4) {
        return add2(x1, x2) + add2(x3, x4);
    }

    private static int add2(int x1, int x2) {
        return x1 + x2;
    }

    public static void main(String[] args) {

        //方法调用计数器的默认阈值在C1模式下是1500次，在C2模式在是10000次，我们循环遍历超过需要阈值
        for (int i = 0; i < 1_000_000; i++) {
            add1(1, 2, 3, 4);
        }
    }
}
