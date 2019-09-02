package com.bluesgao.jvmdemo.gc;


/**
 * 环境：win10x64, jdk8x64
 * jvm 参数
 * -XX:+PrintFlagsFinal -Xss2m
 * <p>
 * <p>
 * -Xss:128k 设置线程栈大小(默认值1m)  对应 ThreadStackSize
 * <p>
 * 测试数据
 * 栈大小     帧数量
 * 128k      800
 * 1m        5000
 * 2m        9000
 */
public class StackOverflowDemo {
    private Integer depth = 0;

    //递归调用
    public void test() {
        depth++;
        System.out.println("depth:" + depth);
        test();
    }

    public static void main(String[] args) {
        StackOverflowDemo stackOverflowCase = new StackOverflowDemo();
        stackOverflowCase.test();
    }

}
