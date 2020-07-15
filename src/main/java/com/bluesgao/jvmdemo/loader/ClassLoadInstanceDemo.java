package com.bluesgao.jvmdemo.loader;


/**
 * 创建类的实例
 */
class ClassLoadInstance {
    static {
        System.out.println("ClassLoadInstance类初始化时就会被执行！");
    }

    public ClassLoadInstance() {
        System.out.println("ClassLoadInstance构造函数！");
    }
}

public class ClassLoadInstanceDemo {
    public static void main(String[] args) {
        ClassLoadInstance instance = new ClassLoadInstance();
    }
}
