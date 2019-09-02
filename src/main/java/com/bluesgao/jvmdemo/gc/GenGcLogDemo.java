package com.bluesgao.jvmdemo.gc;

/**
 * 环境：win10x64, jdk8x64
 * jvm 参数
 * -XX:+PrintGCDetails -XX:+PrintGCDateStamps -Xmx10m -Xms10m -Xloggc:./gclog.log
 * <p>
 * <p>
 */
public class GenGcLogDemo {

    private static int _1MB = 1024 * 1024;

    public static void main(String[] args) {
        while (true) {
            allocate();
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void allocate() {
        byte[] bigObj = new byte[_1MB];
    }
}
