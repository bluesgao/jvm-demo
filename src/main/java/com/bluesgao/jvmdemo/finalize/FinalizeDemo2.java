package com.bluesgao.jvmdemo.finalize;

/**
 * -Xms20m -Xmx20m -Xmn10m -XX:+PrintGCDateStamps -XX:+PrintGCDetails
 * -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=./heapdump.hprof
 * <p>
 * https://www.ezlippi.com/blog/2018/04/final-reference.html
 */
public class FinalizeDemo2 {
    @Override
    protected void finalize() throws Throwable {
        System.out.println("hello finalize " + Thread.currentThread().getName());
    }

    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 10_000; i++) {
            FinalizeDemo2 finalizeDemo = new FinalizeDemo2();
        }

        //让线程阻塞住,方便分析内存使用情况
        Thread.sleep(10 * 60 * 1000);
    }
}
