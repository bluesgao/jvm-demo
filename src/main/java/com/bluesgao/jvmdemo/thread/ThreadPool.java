package com.bluesgao.jvmdemo.thread;

import javafx.concurrent.Worker;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ThreadPool {
    private BlockingQueue<Runnable> taskQueue;
    List<Thread> workers = new ArrayList<>();

    public ThreadPool(BlockingQueue<Runnable> taskQueue, int poolSize, String poolName) {
        this.taskQueue = taskQueue;
        NamedThreadFactory namedThreadFactory = new NamedThreadFactory(poolName);
        // 创建工作线程
        for (int i = 0; i < poolSize; i++) {
            Thread w = namedThreadFactory.newThread(new Worker());
            w.start();
            workers.add(w);
        }
    }

    // 提交任务
    void execute(Runnable task) {
        try {
            taskQueue.put(task);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    void shutdown() {
        if (taskQueue.size() == 0) {
            for (Thread w : workers) {
                w.interrupt();
            }
        }
    }

    private class Worker implements Runnable {
        private boolean flag = true;
        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    //从任务队列获取任务
                    Runnable task = taskQueue.take();
                    //执行任务
                    task.run();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    flag = false;
                }

                if(!flag){
                    break;
                }
            }
            System.out.println(Thread.currentThread().getName() + " is shutdown");
        }
    }

    public static void main(String[] args) {
        System.out.println("----main start----");

        // 创建有界阻塞队列
        BlockingQueue workQueue = new LinkedBlockingQueue<>(2);
        // 创建线程池
        ThreadPool pool = new ThreadPool(workQueue, 10, "111");
        // 提交任务
        for (int i = 0; i < 1000; i++) {
            String str = ":hello-" + i;
            pool.execute(() -> {
                System.out.println(Thread.currentThread().getName() + str);
            });
        }

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        pool.execute(() -> {
            System.out.println(Thread.currentThread().getName() + "test1");
        });

        pool.shutdown();

        pool.execute(() -> {
            System.out.println(Thread.currentThread().getName() + "test2");
        });

        System.out.println("----main end----");

    }
}
