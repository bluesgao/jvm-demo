package com.bluesgao.jvmdemo.jol;

import org.openjdk.jol.info.ClassLayout;

/**
 * 输出对象头信息
 * https://www.cnblogs.com/LemonFive/p/11246086.html
 * https://blog.csdn.net/shihlei/article/details/84914901
 * http://openjdk.java.net/groups/hotspot/docs/HotSpotGlossary.html
 *
 * Java对象的对象头由 mark_word 和  klass_pointer部分组成。
 *
 * 以64位操作系统为例，对象头存储内容图例
 * LP64位机器（L-long,P-pointer）
 * 64位JVM会默认使用选项 +UseCompressedOops开启指针压缩，将指针压缩至32位，如果开启指针压缩，那么object header占用96bits（12字节），
 * 其中mark word（64bits），klass pointer（32bits）。
 * |--------------------------------------------------------------------------------------------------------------|
 * |                                              Object Header (128 bits)                                        |
 * |--------------------------------------------------------------------------------------------------------------|
 * |                        Mark Word (64 bits)                                    |      Klass Word (64 bits)    |
 * |--------------------------------------------------------------------------------------------------------------|
 * |  unused:25 | identity_hashcode:31 | unused:1 | age:4 | biased_lock:1 | lock:2 |     OOP to metadata object   |无锁
 * |----------------------------------------------------------------------|--------|------------------------------|
 * |  thread:54 |         epoch:2      | unused:1 | age:4 | biased_lock:1 | lock:2 |     OOP to metadata object   |偏向锁
 * |----------------------------------------------------------------------|--------|------------------------------|
 * |                     ptr_to_lock_record:62                            | lock:2 |     OOP to metadata object   |轻量锁
 * |----------------------------------------------------------------------|--------|------------------------------|
 * |                     ptr_to_heavyweight_monitor:62                    | lock:2 |     OOP to metadata object   |重量锁
 * |----------------------------------------------------------------------|--------|------------------------------|
 * |                                                                      | lock:2 |     OOP to metadata object   |GC标记
 * |--------------------------------------------------------------------------------------------------------------|
 *
 *简单介绍一下各部分的含义
 * lock:  锁状态标记位，该标记的值不同，整个mark word表示的含义不同。
 * biased_lock：偏向锁标记，为1时表示对象启用偏向锁，为0时表示对象没有偏向锁。
 * --------------------------
 * |biased_lock|lock|状态    |
 * --------------------------
 * |0          | 01 |无锁    |
 * --------------------------
 * |1          |01  |偏向锁  |
 * --------------------------
 * |0          |00  |轻量级锁|
 * --------------------------
 * |0          |10  |重量级锁|
 * -------------------------
 * |0          |11  |GC标记 |
 * --------------------------
 * age：JavaGC标记位对象年龄。
 * identity_hashcode：对象标识Hash码，采用延迟加载技术。当调用对象的hashCode()方法后，并会将结果写到该对象头中。当对象被锁定时，该值会移动到线程Monitor中。
 * thread：持有偏向锁的线程ID和其他信息。这个线程ID并不是JVM分配的线程ID号，和Java Thread中的ID是两个概念。
 * epoch：偏向时间戳。
 * ptr_to_lock_record：指向栈中锁记录的指针。
 * ptr_to_heavyweight_monitor：指向线程Monitor的指针。
 *
 */
public class ObjectHeaderDemo {
    static class A {
        boolean flag = true;
    }

    public static void main(String[] args) {
//        A a = new A();
//        System.out.println(ClassLayout.parseInstance(a).toPrintable());
//        System.out.println("=============hashCode:"+a.hashCode()+"======"+Integer.toHexString(a.hashCode()));
//        System.out.println(ClassLayout.parseInstance(a).toPrintable());


        System.out.println(ClassLayout.parseInstance(new Integer(1)).toPrintable());
    }
}
