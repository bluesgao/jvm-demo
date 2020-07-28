package com.bluesgao.jvmdemo.cpu;

/**
 * cpu分支预测
 * https://blog.csdn.net/xindoo/article/details/101762198
 */
public class BranchPrediction {
    public static void main(String[] args) {
        long start1 = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            for (int j = 0; j < 10000; j++) {
                for (int k = 0; k < 100000; k++) {

                }
            }
        }
        long end1 = System.currentTimeMillis();
        System.out.println("1 time spend ms " + (end1 - start1));


        long start2 = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            for (int j = 0; j < 1000; j++) {
                for (int k = 0; k < 1000; k++) {

                }
            }
        }
        long end2 = System.currentTimeMillis();
        System.out.println("2 time spend ms " + (end2 - start2));

        /**output
         * 1 time spend ms 5
         * 2 time spend ms 36
         */
    }
}
