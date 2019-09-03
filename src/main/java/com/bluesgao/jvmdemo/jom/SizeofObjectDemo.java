package com.bluesgao.jvmdemo.jom;

/**
 * 环境：win10x64,jdk8x64
 * <p>
 * 计算一个对象的占用内存的大小
 * <p>
 * 1，压缩指针
 * -XX:-UseCompressedOops（关闭）
 * -XX:+UseCompressedOops（开启-在64位操作系统默认开启）
 * -XX:+PrintFlagsFinal （打印jvm参数）
 * <p>
 * 2，java对象在jvm中的内存结构（总大小为8的倍数）
 * ①header(mark-8bytes,metadata-8bytes,length-4bytes-数组对象才有)
 * 普通对象头-占用16字节，如果开启指针压缩占用12字节
 * 数字对象头-占用24字节（8+8+4+padding4），如果开启指针压缩占用16字节
 * ②instance data
 * ③padding
 * <p>
 * <p>
 * 类型占用内存大小
 * ①原生类型(primitive type)
 * PrimitiveType	MemoryRequired(bytes)
 * boolean          1
 * byte             1
 * short            2
 * char             2
 * int              4
 * float            4
 * long             8
 * double    	    8
 * ②引用类型
 * 在32位系统上每个占用4bytes，在64位系统上每个占用8bytes(开启指针压缩后占用4bytes)。
 * ③数组类型
 * 数组对象头+数组元素个数*大小
 */
public class SizeofObjectDemo {
    static class DemoObj {
    }

    static class DemoInt {
        int i;//原生类型
    }

    static class DemoInteger {
        Integer integer;//引用类型
    }

    static class DemoIntegerAndInt {
        int i;
        Integer integer;//引用类型
    }

    static class DemoExtend extends DemoInteger {
        Double d;//引用类型
    }

    public static void main(String[] args) throws Exception {
        System.out.println("test start");
        ////////////////////////////对象//////////////////////////
        /**
         * -XX:+UseCompressedOops: (mark/8 + metedata/4)  = 16
         * -XX:-UseCompressedOops: (mark/8 + metedata/8)  = 16
         */
        DemoObj obj = new DemoObj();

        /**
         * -XX:+UseCompressedOops: (mark/8 + metedata/4) + (4) = 16
         * -XX:-UseCompressedOops: (mark/8 + metedata/8) + (4) + (padding/4) = 24
         */
        DemoInt a = new DemoInt();

        /**
         * -XX:+UseCompressedOops: (mark/8 + metedata/4) + (4) = 16
         * -XX:-UseCompressedOops: (mark/8 + metedata/8) + (8) = 24
         */
        DemoInteger b = new DemoInteger();

        /**
         * -XX:+UseCompressedOops: (mark/8 + metedata/4) + (4 + 4) + (padding/4) = 24
         * -XX:-UseCompressedOops: (mark/8 + metedata/8) + (4 + 8) + (padding/4) = 32
         */
        DemoIntegerAndInt c = new DemoIntegerAndInt();

        /**
         * -XX:+UseCompressedOops: (mark/8 + metedata/4) + (4 + 4) + (padding/4) = 24
         * -XX:-UseCompressedOops: (mark/8 + metedata/8) + (8 + 8) = 32
         */
        DemoExtend d = new DemoExtend();

        /**
         * -XX:+UseCompressedOops: (mark/8 + metedata/4 + length/4) +(4) + (padding/4) = 24
         * -XX:-UseCompressedOops: (mark/8 + metedata/8 + length/4) +(8) + (padding/4) = 32
         */
        Integer[] arrayInteger = new Integer[1];
        arrayInteger[0] = 1212;

        ////////////////////////////数组//////////////////////////
        /**
         * -XX:+UseCompressedOops: (mark/8 + metedata/4 + length/4) +(4 +4) = 24
         * -XX:-UseCompressedOops: (mark/8 + metedata/8 + length/4) +(8 +8) +padding/4) = 40
         */
        Integer[] arrayInteger2 = new Integer[2];
        arrayInteger2[0] = new Integer(1314);
        arrayInteger2[1] = new Integer(1618);

        /**
         * -XX:+UseCompressedOops: (mark/8 + metedata/4 + length/4) +(4 +4) = 24
         * -XX:-UseCompressedOops: (mark/8 + metedata/8 + length/4) +(4 +4) +padding/4) = 32
         */
        int[] arrayInt = new int[2];
        arrayInt[0] = 1999;
        arrayInt[1] = 2999;

        /**
         * -XX:+UseCompressedOops: (mark/8 + metedata/4 + length/4) +(8 +8) = 32
         * -XX:-UseCompressedOops: (mark/8 + metedata/8 + length/4) +(8 +8) +(padding/4) = 40
         */
        long[] arrayLong = new long[2];
        arrayLong[0] = 8888;
        arrayLong[1] = 9999;

        ////////////////////////////字符串//////////////////////////
        /**
         * String包含2个属性，一个用于存放字符串数据的char[], 一个int类型的hashcode
         * -XX:+UseCompressedOops: (mark/8 + metedata/4) +(4 +4) + (padding/4)= 24
         * -XX:-UseCompressedOops: (mark/8 + metedata/8) +(8 +4) +(padding/4) = 32
         */
        String str = new String("hello world");


        Thread.sleep(10 * 60 * 1000);
        System.out.println("test end");

    }
}
