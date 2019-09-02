package com.bluesgao.jvmdemo.jmm;

/**
 * 原子性
 * 下面程序输出count结果是多少？
 * 答案：100000-200000之间
 * 原因:increase方法非原子性
 */
public class AtomicDemo {
    private static Long count = 0L;

    public static void increase() {
        count++;// count = count +1;
    }

    public static void loop(int loopNum) {
        for (int i = 0; i < loopNum; i++) {
            increase();
        }
    }


    public static void main(String[] args) throws Exception {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                loop(100_000);
            }
        });

        t1.setName("T1");
        t1.start();


        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                loop(100_000);
            }
        });

        t2.setName("T2");
        t2.start();

        Thread.sleep(1 * 1000);

        t1.join();
        t2.join();

        System.out.println("count=" + count);
    }
}
