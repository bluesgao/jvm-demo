package com.bluesgao.jvmdemo.thread;

import java.util.Random;
import java.util.concurrent.*;

/**
 * corePoolSize：线程池中核心线程数的最大值
 * maximumPoolSize：线程池中能拥有最多线程数
 * keepAliveTime:空闲线程的存活时间(核心线程不受此控制)
 * workQueue：用于缓存任务的阻塞队列
 * handler:表示当workQueue已满，且池中的线程数达到maximumPoolSize时，线程池拒绝添加新任务时采取的策略
 */
public class CompletionServiceDemo {
    public static void main(String[] args) {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(0, 4, 500L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());
        CompletionService<String> completionService = new ExecutorCompletionService<String>(threadPoolExecutor);
        System.out.println("main start");

        Random random = new Random(1);

        for (int i = 0; i < 4; i++) {
            completionService.submit(new Callable<String>() {
                @Override
                public String call() throws Exception {

                    int j = 0;
                    int r = random.nextInt(10000);
                    while (j < r) {
                        j++;
                        Thread.sleep(1);
                    }

                    return Thread.currentThread().getName() + " task done " + j;
                }
            });
        }

        for (int i = 0; i < 4; i++) {
            Future<String> future = null;
            try {
                System.out.println("bloking queue take ... 1");
                //future = completionService.take();
                future = completionService.poll(100, TimeUnit.MILLISECONDS);
                System.out.println("bloking queue take ... 2");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String retString = null;
            try {
                System.out.println("future get ... 1");
                retString = future.get(5, TimeUnit.MILLISECONDS);
                System.out.println("future get ... 2");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("retString:" + retString);
        }

        //threadPoolExecutor.shutdown();
        System.out.println("main end");
    }
}
