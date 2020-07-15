package com.bluesgao.jvmdemo.loader;

//访问类的静态方法
class ClassLoadStaticMethod {
    static {
        System.out.println("ClassLoadStaticMethod类初始化时就会被执行！");
    }

    public static void method() {
        System.out.println("静态方法被调用");
    }

    public ClassLoadStaticMethod() {
        System.out.println("ClassLoadStaticMethod构造函数！");
    }
}

public class ClassLoadStaticMethodDemo {
    public static void main(String[] args) {
        ClassLoadStaticMethod.method();
    }
}
