package com.bluesgao.jvmdemo.thread;

/**
 * https://my.oschina.net/itblog/blog/787024
 *
 * 1，interrupt()
 * 调用该方法的线程的状态将被置为"中断"状态,不会停止线程。
 *
 * 2，static boolean interrupted()
 * 测试当前线程（当前线程是指运行interrupted()方法的线程）是否已经中断，且清除中断状态。
 *
 * 3，boolean isInterrupted()
 * 测试线程（调用该方法的线程）是否已经中断，不清除中断状态。
 *
 * 4，区别：静态方法interrupted将会清除中断状态（传入的参数ClearInterrupted为true）。
 *      实例方法isInterrupted则不会（传入的参数ClearInterrupted为false）。
 *
 * 5，InterruptedException
 * 当抛出这个异常的时候，中断状态已被清除。
 * if any thread has interrupted the current thread.
 * The interrupted status of the current thread is cleared when this exception is thrown.
 *
 * 为什么要在抛出InterruptedException的时候清除掉中断状态呢？
 * 一个中断应该只被处理一次（你catch了这个InterruptedException，说明你能处理这个异常，你不希望上层调用者看到这个中断）。
 */
public class InterruptDemo {
    public static void main(String[] args) {
        System.out.println("main thread start...");

        Thread t = new Thread(new Worker());
        t.start();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t.interrupt();

        System.out.println("main thread end...");
    }

    public static class Worker implements Runnable {
        @Override
        public void run() {
            System.out.println("worker thread start....");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread curr = Thread.currentThread();
                //再次调用interrupt方法中断自己，将中断状态设置为“中断”
                curr.interrupt();
                System.out.println("Worker IsInterrupted: " + curr.isInterrupted());
                System.out.println("Worker IsInterrupted: " + curr.isInterrupted());
                System.out.println("Static Call: " + Thread.interrupted());//clear status
                System.out.println("---------After Interrupt Status Cleared----------");
                System.out.println("Static Call: " + Thread.interrupted());
                System.out.println("Worker IsInterrupted: " + curr.isInterrupted());
                System.out.println("Worker IsInterrupted: " + curr.isInterrupted());
            }
            System.out.println("worker thread end....");
        }
    }
}
