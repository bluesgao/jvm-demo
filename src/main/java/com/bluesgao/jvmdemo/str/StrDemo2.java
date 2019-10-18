package com.bluesgao.jvmdemo.str;

public class StrDemo2 {
    public static void main(String[] args) {
        String str1 = "111";
        String a = "111";
        String str2 = "111" + "";
        System.out.println(str1 == str2);//true ?
        /**
         * 直接在表达式里写值，java不用根据变量去内存里找对应的值，可以在编译的时候直接对这个表达式进行优化，
         * 优化后的表达式从 "111"+"" 直接变成了 "111" ，两个String类型的变量都指向了常量池的111字符串，因此结果为true;
         */
    }
}
