package com.bluesgao.jvmdemo.other;

/**
 * goto是java的关键字，但是没有实现其功能
 * 使用 java标签+break[标签] continue[标签] 实现goto功能
 *
 *
 * 在相对复杂的业务逻辑中，我们常常使用到多重循环，在最里层循环判断某个条件，一旦条件不符合就直接break，可以break并不能直接跳出所有的循环，这肯定会对性能有影响，还有可能影响业务判断。
 * 最好的方式就是直接跳出所有循循，return到最外层。这个类似被广大人民诟病的goto语法
 *
 * 注意：
 * JAVA中的标签必须放在循环之前，且中间不能有其他语句。
 * 跳出标签语句范围后，其后面的语句正常执行。
 *
 * 解决java编译错误：编码GBK的不可映射字符:javac -encoding UTF-8 test.java
 */
public class GotoDemo {
    public static void main(String args[]) {
        https://www.baidu.com
        for (int i = 0; i < 3; i++) {
            System.out.print("Pass " + i + ":");
            for (int j = 0; j < 100; j++) {
                if (j == 10)
                    //break https;//跳出循环
                    continue https;//继续执行下轮循环
                System.out.print(j + " ");
            }
            System.out.println("This will not print");
        }
        System.out.println("loops complete.");
    }
}
