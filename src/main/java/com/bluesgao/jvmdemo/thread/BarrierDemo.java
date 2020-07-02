package com.bluesgao.jvmdemo.thread;

import java.util.concurrent.*;

public class BarrierDemo {
    public static void main(String[] args) {
        Executor executor = Executors.newFixedThreadPool(1);
        final CyclicBarrier cyclicBarrier = new CyclicBarrier(2, ()->{
            executor.execute(()->{
                System.out.println("cyclicBarrier回调");
            });
        });

        Thread t1 = new Thread(()->{
            try {
                System.out.println("t1 wait");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }

        });

        Thread t2 = new Thread(()->{
            try {
                System.out.println("t2 wait");
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }

        });

        t1.start();
        t2.start();
    }
}
