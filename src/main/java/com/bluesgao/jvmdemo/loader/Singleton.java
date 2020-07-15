package com.bluesgao.jvmdemo.loader;

//类加载过程
// -verbose:class 输出类加载过程
// -XX:+TraceClassLoading 类加载过程
//单例延迟初始化
public class Singleton {
    private Singleton() {
    }

    static {
        System.out.println("Singleton.<clinit>");
    }

    private static class LazyHolder {
        final static Singleton INSTANCE = new Singleton();

        static {
            System.out.println("LazyHolder.<clinit>");
        }
    }

    public static Singleton getInstance() {
        return LazyHolder.INSTANCE;
    }

    public static void main(String[] args) {
        Object o = Singleton.getInstance();
    }

}
