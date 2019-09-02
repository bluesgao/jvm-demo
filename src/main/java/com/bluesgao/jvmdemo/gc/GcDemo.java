package com.bluesgao.jvmdemo.gc;

public class GcDemo {
    private static int _1MB = 1024 * 1024;

    public static void main(String[] args) {
        bigObjGcCase();
    }

    /**
     * 环境：win10x64,jdk8x64
     * jvm 参数
     * -Xms20m -Xmx20m -Xmn10m -XX:+PrintGCDateStamps -XX:+PrintGCDetails -XX:+PrintFlagsFinal
     * <p>
     * <p>
     * -Xms20m 初始堆空间
     * -Xmx20m 最大堆空间
     * -Xmn10m 新生代大小
     * -XX:+PrintGCDetails 打印gc信息
     * -XX:PretenureSizeThreshold=1000 当对象大小>1000byte时直接在oldGen分配
     * -XX:+UseConcMarkSweepGC  使用cms垃圾回收器
     * 注意：应用启动时已经在eden区占用一些空间（win10：2368K，linux：700k)
     * YoungGen内存空间：eden区8m，s1区1m，s2区1m
     * OldGen内存空间：10m
     */
    public static void bigObjGcCase() {
        byte[] obj1 = new byte[_1MB / 2 - 100];
        byte[] obj2 = new byte[_1MB / 2 - 50];

        byte[] obj3 = new byte[_1MB * 2];
        byte[] obj4 = new byte[_1MB * 2];

        //minor gc 8m-(2m+2m+2368k)<2m
        //1,eden区剩余空间小于新对象，即此时eden区装不下此新对象，因此触发minorgc对YoungGen进行垃圾回收。
        //2,如果survivor区不能容纳eden区中所有活跃对象，那么eden区存活对象有一部分（小于s0剩余容量的对象）晋升到survivor区，有一部分（大于s0剩余容量的对象）将会晋升到OldGen。
        //3,在eden区给该新对象分配空间
        byte[] obj5 = new byte[_1MB * 3];

        //oom
        byte[] obj6 = new byte[_1MB * 10];

        /*
         * 分析过程
         *
         * 1，初始内存分布图  -Xms20m -Xmx20m -Xmn10m
         * |      newGen     |     oldGen         |
         * |  eden   |s0 |s1 |                    |
         * ----------------------------------------
         * |         |   |   |                    |
         * |   8m    | 1m| 1m|    10m             |
         * |         |   |   |                    |
         * ----------------------------------------
         *
         * 2，对象大小
         * obj1=412k
         * ojb2=426k
         * obj3=2048k
         * obj4=2048k
         * obj5=3072k
         *
         * 3，对象分配空间步骤
         * a, obj1,obj2,obj3,obj4对象在eden区分配空间
         * |      newGen     |     oldGen         |
         * |  eden   |s0 |s1 |                    |
         * ----------------------------------------
         * |obj1 obj4|   |   |                    |
         * |obj2     |   |   |                    |
         * |obj3     |   |   |                    |
         * ----------------------------------------
         *
         * b，obj5对象大于eden区剩余容量（8m-412k-462k-2048k-2048k-3072k），因此触发minorGC,
         *    根据可达性分析obj1，obj2，obj3，obj4此时都是活跃对象，不能被回收，那么将发生对象移动，
         *    obj1和obj2将会移动到s0区（412k+426k<1m），同时由于s0区不能容纳obj3和obj4对象，因此
         *    这2个对象将会被移动到oldGen。
         * |      newGen      |     oldGen         |
         * |  eden   |s0  |s1 |                    |
         * -----------------------------------------
         * |         |obj1|   |  obj3              |
         * |         |obj2|   |  ojb4              |
         * |         |    |   |                    |
         * -----------------------------------------
         *
         * c，通过b步骤的操作，eden区此时剩余空间（8m）大于obj5对象（3m），
         *    那么jvm将会在eden区给ojb5分配空间。
         * |      newGen      |     oldGen         |
         * |  eden   |s0  |s1 |                    |
         * -----------------------------------------
         * |obj5     |obj1|   |  obj3              |
         * |         |obj2|   |  ojb4              |
         * |         |    |   |                    |
         * -----------------------------------------
         * */


        //线程挂起10分钟，方便调试
        try {
            Thread.sleep(10 * 60 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
