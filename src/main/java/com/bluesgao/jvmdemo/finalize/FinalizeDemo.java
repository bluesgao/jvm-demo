package com.bluesgao.jvmdemo.finalize;

/**
 * -Xms20m -Xmx20m -Xmn10m -XX:+PrintGCDateStamps -XX:+PrintGCDetails
 * -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=./heapdump.hprof
 *
 * https://www.ezlippi.com/blog/2018/04/final-reference.html
 * https://blog.csdn.net/Great_Smile/article/details/49935307
 */
public class FinalizeDemo {
    @Override
    protected void finalize() throws Throwable {
        while (true) {
            Thread.yield();
        }
    }

    public static void main(String[] args) {
        while (true) {
            for (int i = 0; i < 1_000; i++) {
                FinalizeDemo finalizeDemo = new FinalizeDemo();
            }
        }
    }
}
