package com.bluesgao.jvmdemo.gc;

/**
 * https://www.jianshu.com/p/580f17760f6e
 * https://www.jianshu.com/p/20bd2e9b1f03
 * https://www.iteye.com/blog/rednaxelafx-659108
 *
 * 逃逸分析
 * 发生逃逸行为的情况有两种：方法逃逸和线程逃逸。
 * 1、方法逃逸：当一个对象在方法中定义之后，作为参数传递到其它方法中；
 * 2、线程逃逸：如类变量或实例变量，可能被其它线程访问到；
 * -XX:+DoEscapeAnalysis开启逃逸分析
 * -XX:+PrintEscapeAnalysis 开启逃逸分析后，可通过此参数查看分析结果(jvm-debug)
 * <p>
 * 标量替换
 * 1、标量是指不可分割的量，如java中基本数据类型和reference类型，相对的一个数据可以继续分解，称为聚合量；
 * 2、如果把一个对象拆散，将其成员变量恢复到基本类型来访问就叫做标量替换；
 * 3、如果逃逸分析发现一个对象不会被外部访问，并且该对象可以被拆散，那么经过优化之后，并不直接生成该对象，而是在栈上创建若干个成员变量；
 * -XX:+EliminateAllocations开启标量替换（默认开启）
 * -XX:+PrintEliminateAllocations查看标量替换情况
 *  -XX:+PrintCompilation(jvm-debug)
 *  -XX:+PrintInlining(jvm-debug)
 * <p>
 * 栈上分配
 * 故名思议就是在栈上分配对象，其实目前Hotspot并没有实现真正意义上的栈上分配，实际上是标量替换。
 * <p>
 * 运行JVM参数
 * -Xmx3G -Xmn2G -server -XX:-DoEscapeAnalysis
 * <p>
 * 分析步骤：
 * 1，获取pid
 * jps -l
 * 21796 com.bluesgao.jvmdemo.gc.EscapeAnalysisDemo
 * <p>
 * 2，查看jvm堆上对象分布情况
 * ①关闭逃逸分析（-Xmx3G -Xmn2G -server -XX:-DoEscapeAnalysis）
 $ jmap -histo 15824

 num     #instances         #bytes  class name
 ----------------------------------------------
 1:           620       94877336  [I
 2:       2000000       32000000  com.bluesgao.jvmdemo.gc.User

 * <p>
 * ②开启逃逸分析（-Xmx3G -Xmn2G -server -XX:+DoEscapeAnalysis）
 $ jmap -histo 21640

 num     #instances         #bytes  class name
 ----------------------------------------------
 1:           619       91161216  [I
 2:        218991        3503856  com.bluesgao.jvmdemo.gc.User

 */
public class EscapeAnalysisDemo {
    int count = 1_000_000;

    public int getAge() {
        User user = new User(999);
        return user.getAge();
    }

    public static void main(String[] args) throws Exception {

        EscapeAnalysisDemo escapeAnalysisDemo = new EscapeAnalysisDemo();

        for (int i = 0; i < escapeAnalysisDemo.count; i++) {
            escapeAnalysisDemo.getAge();
        }

        Thread.sleep(500);
        for (int i = 0; i < escapeAnalysisDemo.count; i++) {
            escapeAnalysisDemo.getAge();
        }

        Thread.sleep(10 * 60 * 1000);
    }


}

class User {
    private int age;

    public User(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }
}