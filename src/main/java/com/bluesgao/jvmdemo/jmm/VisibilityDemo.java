package com.bluesgao.jvmdemo.jmm;

/**
 * 可见性
 * 1，什么是可见性：
 * 在多个线程环境中，一个线程修改了共享变量的值，其他线程能够立即看得到修改的值
 * <p>
 * 2，造成可见性问题的原因：
 * （1）现代cpu缓存架构（缓存一致性，MESI,缓存行）
 * （2）JMM（java线程内存模型）工作内存，主内存，线程通信模型（通过共享内存通信）
 * - java的所有变量都存储在主内存中
 * - 每个线程有自己独的工作内存，保存了该线程使用到的变量副本，是对主内存中变量的一份拷贝
 * - 每个线程不能访问其他线程的工作内存，线程间变量传递需要通过主内存来完成
 * - 每个线程不能直接操作主存，只能把主存的内容拷贝到工作内存后再做操作（这是线程不安全的本质），然后写回主内存
 * <p>
 * 内存交互操作有8种,虚拟机实现必须保证每一个操作都是原子的（对于double和long类型的变量来说，load、store、read和write操作在某些平台上允许例外）
 * lock     （锁定）：作用于主内存的变量，把一个变量标识为线程独占状态
 * unlock （解锁）：作用于主内存的变量，它把一个处于锁定状态的变量释放出来，释放后的变量才可以被其他线程锁定
 * read    （读取）：作用于主内存变量，它把一个变量的值从主内存传输到线程的工作内存中，以便随后的load动作使用
 * load     （载入）：作用于工作内存的变量，它把read操作从主存中变量放入工作内存中
 * use      （使用）：作用于工作内存中的变量，它把工作内存中的变量传输给执行引擎，每当虚拟机遇到一个需要使用到变量的值，就会使用到这个指令
 * assign  （赋值）：作用于工作内存中的变量，它把一个从执行引擎中接受到的值放入工作内存的变量副本中
 * store    （存储）：作用于主内存中的变量，它把一个从工作内存中一个变量的值传送到主内存中，以便后续的write使用
 * write 　（写入）：作用于主内存中的变量，它把store操作从工作内存中得到的变量的值放入主内存的变量中
 * <p>
 * JMM对这八种指令的使用，制定了如下规则：
 * 不允许read和load、store和write操作之一单独出现。即使用了read必须load，使用了store必须write
 * 不允许线程丢弃他最近的assign操作，即工作变量的数据改变了之后，必须告知主存
 * 不允许一个线程将没有assign的数据从工作内存同步回主内存
 * 一个新的变量必须在主内存中诞生，不允许工作内存直接使用一个未被初始化的变量。就是对变量实施use、store操作之前，必须经过assign和load操作
 * 一个变量同一时间只有一个线程能对其进行lock。多次lock后，必须执行相同次数的unlock才能解锁
 * 如果对一个变量进行lock操作，会清空所有工作内存中此变量的值，在执行引擎使用这个变量前，必须重新load或assign操作初始化变量的值
 * 如果一个变量没有被lock，就不能对其进行unlock操作。也不能unlock一个被其他线程锁住的变量
 * 对一个变量进行unlock操作之前，必须把此变量同步回主内存
 * <p>
 * 3，如何避免可见性造成的影响：
 * (1)原子操作-使用volatile关键字
 * (2)复合操作-使用锁机制
 * <p>
 * <p>
 * java汇编代码输出
 * 1，下载hsdis-amd64.so/hsdis-amd64.dylib（区分windows和linux系统），然后移动到$JAVA_HOME/jre/lib
 * 2，执行命令 java -XX:+UnlockDiagnosticVMOptions -XX:+PrintAssembly
 */
public class VisibilityDemo {
    //private static boolean flag = false; //有可见性问题
    private static volatile boolean flag = false;

    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "等待flag变化");
                //等待flag==true，否则循环等待
                while (!flag) {
                }
                System.out.println(Thread.currentThread().getName() + "flag已经变化");

            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + "给flag赋值为true");
                flag = true;
            }
        });

        t1.setName("T1");
        t1.start();

        try {
            Thread.sleep(1 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        t2.setName("T2");
        t2.start();

        t1.join();
        t2.join();
    }
}
