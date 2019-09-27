package com.bluesgao.jvmdemo.gc;

/**
 * https://blog.csdn.net/baichoufei90/article/details/85097727
 * <p>
 * -XX:+PrintGCApplicationStoppedTime 系统停止的时间
 * -XX:+PrintSafepointStatistics
 * -XX:+PrintSafepointStatisticsCount=1
 * -XX:+PrintGC
 * -XX:+UseCountedLoopSafepoints
 */
public class SafePointDemo {
    static double sum = 0;

    public static void foo() {
        for (int i = 0; i < 0x7777777; i++) {
            sum += Math.sqrt(i);
        }
    }

    public static void bar() {
        for (int i = 0; i < 50_000_000; i++) {
            new Object().hashCode();
        }
    }

    public static void main(String[] args) {
        new Thread(SafePointDemo::foo).start();
        new Thread(SafePointDemo::bar).start();
    }
}
