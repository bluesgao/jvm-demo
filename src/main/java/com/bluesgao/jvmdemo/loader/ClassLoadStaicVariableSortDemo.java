package com.bluesgao.jvmdemo.loader;

//静态变量初始化顺序
class ClassLoadStaicVariableSort {
    static {
        System.out.println("ClassLoadStaicVariableSort静态代码块1");
    }

    //类加载初始化，按顺序初始化静态变量
    private static ClassLoadStaicVariableSort singleTon = new ClassLoadStaicVariableSort();

    static {
        System.out.println("ClassLoadStaicVariableSort静态代码块2 count1:" + ClassLoadStaicVariableSort.count1);
        System.out.println("ClassLoadStaicVariableSort静态代码块2 count2:" + ClassLoadStaicVariableSort.count2);

    }

    public static int count1;
    public static int count2 = 99;

    static {
        System.out.println("ClassLoadStaicVariableSort静态代码块3 count1:" + ClassLoadStaicVariableSort.count1);
        System.out.println("ClassLoadStaicVariableSort静态代码块3 count2:" + ClassLoadStaicVariableSort.count2);

    }

    private ClassLoadStaicVariableSort() {
        System.out.println("ClassLoadStaicVariableSort构造函数");
        count1++;
        count2++;
    }

    public static ClassLoadStaicVariableSort getInstance() {
        return singleTon;
    }
}

public class ClassLoadStaicVariableSortDemo {
    public static void main(String[] args) {
        ClassLoadStaicVariableSort singleTon = ClassLoadStaicVariableSort.getInstance();
        System.out.println("count1=" + singleTon.count1);
        System.out.println("count2=" + singleTon.count2);

/*输出
ClassLoadStaicVariableSort静态代码块1
ClassLoadStaicVariableSort构造函数
ClassLoadStaicVariableSort静态代码块2 count1:1
ClassLoadStaicVariableSort静态代码块2 count2:1
ClassLoadStaicVariableSort静态代码块3 count1:1
ClassLoadStaicVariableSort静态代码块3 count2:99
count1=1
count2=99
 */
    }
}
