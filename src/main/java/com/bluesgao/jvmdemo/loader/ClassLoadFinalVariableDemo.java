package com.bluesgao.jvmdemo.loader;

/**
 * 访问final修饰的静态变量时，不会触发类加载，因为在编译期已经将此常量放在常量池了
 */
class ClassLoadFinalVariable {
    static {
        System.out.println("ClassLoadFinalVariable！");
    }

    public static final int i = 100;

    public ClassLoadFinalVariable() {
        System.out.println("ClassLoadFinalVariable构造函数！");
    }
}

public class ClassLoadFinalVariableDemo {
    public static void main(String[] args) {
        System.out.println(ClassLoadFinalVariable.i);
    }
}
