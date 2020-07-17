package com.bluesgao.jvmdemo.loader;

class ClassLoadArray {
    static {
        System.out.println("ClassLoadArray类初始化时就会被执行！");
    }
    public ClassLoadArray(){
        System.out.println("ClassLoadArray构造函数");
    }
}

public class ClassLoadArrayDemo {
    public static void main(String[] args) {
        //数组创建动作由字节码指令 newarray 触发，这是一个对数组引用类型的初初始化，
        //而该数组中的元素仅仅包含一个对 ClassLoadArray 类的引用，并没有对其进行初始化
        ClassLoadArray arr[] = new ClassLoadArray[3];

        //如果对数组中各个ClassLoadArray类元素的实例化，便会触发ClassLoadArray类的初始化
        for (int i = 0; i < arr.length; i++) {
            arr[i] = new ClassLoadArray();
        }
    }
}
