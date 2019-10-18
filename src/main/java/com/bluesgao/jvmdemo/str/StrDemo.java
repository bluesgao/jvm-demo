package com.bluesgao.jvmdemo.str;

/**
 * https://mp.weixin.qq.com/s/Voh2468APoWfbXEYx_MPKg
 * 字符串拼接原理
 */
public class StrDemo {
    public static void main(String[] args) {
        String str1 = "111";
        String a = "111";
        String str2 = a + "";
        System.out.println(str1 == str2);//false

        /*
        通过变量和字符串拼接，java是需要先到内存找变量对应的值，才能进行完成字符串拼接的工作，
        这种方式java编译器没法优化，只能走 StringBuilder进行拼接字符串，然后调用toString方法，
        当然返回的结果和常量池中的 111这个字符串的内存地址是不一样的，因此结果为false。
         */
    }
}
