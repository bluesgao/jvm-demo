package com.bluesgao.jvmdemo.loader;

//访问类的静态变量
class ClassLoadStaticVariable {
    static {
        System.out.println("ClassLoadStaticVariable类初始化时就会被执行！");
    }

    public static int i = 100;

    public ClassLoadStaticVariable() {
        System.out.println("ClassLoadStaticVariable构造函数！");
    }
}

public class ClassLoadStaticVariableDemo {
    public static void main(String[] args) {
        System.out.println(ClassLoadStaticVariable.i);
    }
}
