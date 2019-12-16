package com.bluesgao.jvmdemo.other;

/**
 * 给一个大二维数组赋值
 * 知识点：缓存行
 */
public class CacheLineTest {
    public static void main(String[] args) {
        int[][] arr = new int[6400][6400];

        long start1 = System.currentTimeMillis();
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                arr[i][j] = i + j;////注意
            }
        }
        //51ms, 由于数据是按照行放的，按行遍历，下一条数据大概率在Cache line中，因而耗时较短。
        System.out.println("time =" + (System.currentTimeMillis() - start1));


        long start2 = System.currentTimeMillis();
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                arr[j][i] = i + j;//注意
            }
        }
        //511
        System.out.println("time =" + (System.currentTimeMillis() - start2));
    }
}
