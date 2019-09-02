package com.bluesgao.jvmdemo.jom;

/**
 * 计算一个对象的占用内存的大小
 * <p>
 * 压缩指针
 * -XX:-UseCompressedOops（关闭）
 * -XX:+UseCompressedOops（开启-在64位操作系统默认开启）
 * -XX:+PrintFlagsFinal （打印jvm参数）
 * <p>
 * java对象在jvm中的内存结构（总大小为8的倍数）
 * 1，header(mark,klass_pointer)
 * 占用16字节，如果开启指针压缩占用12字节
 * 2,instance data
 * 3,padding
 * <p>
 * ①原生类型(primitive type)的内存占用如下：
 * PrimitiveType	MemoryRequired(bytes)
 * boolean          1
 * byte             1
 * short            2
 * char             2
 * int              4
 * float            4
 * long             8
 * double    	    8
 * ②引用类型在32位系统上每个占用4bytes，在64位系统上每个占用8bytes,开启指针压缩后占用4bytes。
 */
public class SizeofObjectDemo {
    /**
     * -XX:+UseCompressedOops: mark/8 + metedata/4  = 16
     * -XX:-UseCompressedOops: mark/8 + metedata/8  = 16
     */
    static class DemoObj {
    }

    /**
     * -XX:+UseCompressedOops: mark/8 + metedata/4 + 4 = 16
     * -XX:-UseCompressedOops: mark/8 + metedata/8 + 4 + padding/4 = 24
     */
    static class DemoInt {
        int i;//原生类型
    }

    /**
     * -XX:+UseCompressedOops: mark/8 + metedata/4 + 4 = 16
     * -XX:-UseCompressedOops: mark/8 + metedata/8 + 8 = 24
     */
    static class DemoInteger {
        Integer integer;//引用类型
    }

    /**
     * -XX:+UseCompressedOops: mark/8 + metedata/4 + 4 + 4 + padding/4 = 24
     * -XX:-UseCompressedOops: mark/8 + metedata/8 + 4 + 8 + padding/4 = 32
     */
    static class DemoIntegerAndInt {
        int i;
        Integer integer;//引用类型
    }

    /**
     * -XX:+UseCompressedOops: mark/8 + metedata/4 + 4 + 4 + padding/4 = 24
     * -XX:-UseCompressedOops: mark/8 + metedata/8 + 8 + 8 = 32
     */
    static class DemoExtend extends DemoInteger {
        Double d;//引用类型
    }

    public static void main(String[] args) throws Exception {
        System.out.println("test start");

        DemoObj obj = new DemoObj();
        DemoInt a = new DemoInt();
        DemoInteger b = new DemoInteger();
        DemoIntegerAndInt c = new DemoIntegerAndInt();
        DemoExtend d = new DemoExtend();

        Thread.sleep(600 * 1000);
        System.out.println("test end");

    }
}
