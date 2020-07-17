package com.bluesgao.jvmdemo.loader;

//当初始化一个类时，发现其父类还未初始化，则先触发父类的初始化
class Father {
    public static int fatherAge = 100;

    static {
        System.out.println("father静态代码块");
    }

    public Father() {
        System.out.println("father的构造函数");
    }
}

class Son extends Father {
    public static int sonAge = 50;

    static {
        System.out.println("son静态代码块");
    }

    public Son() {
        System.out.println("son的构造函数");
    }
}

public class ClassLoadInheritDemo {
    public static void main(String[] args) {
        //当初始化一个类时，发现其父类还未初始化，则先触发父类的初始化
        //Son son = new Son();
        //通过子类引用父类中的静态字段，这时对子类的引用为被动引用，因此不会初始化子类，只会初始化父类
        System.out.println(Father.fatherAge);
    }
}
