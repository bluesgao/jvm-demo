package com.bluesgao.jvmdemo.loader;


//反射
class ClassLoadReflect {
    static {
        System.out.println("ClassLoadReflect类初始化时就会被执行！");
    }

    public static void method() {
        System.out.println("静态方法被调用");
    }

    public ClassLoadReflect() {
        System.out.println("ClassLoadReflect构造函数！");
    }
}

public class ClassLoadReflectDemo {

    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName("com.bluesgao.jvmdemo.loader.ClassLoadReflect");
    }
}
