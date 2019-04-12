package org.billing.jlab.threads.synchronizers;

import java.util.concurrent.*;

public class FutureTaskTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        Callable<Integer> myCallable = () -> {
            System.out.println(Thread.currentThread().getName() + " Очень сложное вычисление Callable");
            Thread.sleep(1000);
            return 42;
        };

        Runnable myRunnable = () -> {
            System.out.println(Thread.currentThread().getName() + " Очень сложное вычисление Runnable");
        };

        // оборачиваем во FutureTask
        int result = 0;
        FutureTask<Integer> task = new FutureTask<Integer>(myCallable);
        FutureTask<Integer> task2 = new FutureTask<Integer>(myCallable);
        FutureTask<Integer> task3 = new FutureTask<Integer>(myRunnable, result);

        Thread t1 = new Thread(task3);
        Thread t2 = new Thread(myRunnable);
        t1.start();
        t2.start();
       // System.out.println(task.get());

        // запустить можно по-всякому
        ExecutorService executor = Executors.newFixedThreadPool(2);
        try {
            System.out.println("Step_1");
            executor.execute(task);
            System.out.println("Step_2");
            Future<?> future = executor.submit(task);
        } finally {
            if (executor != null) executor.shutdown();
        }

        Thread t3 = new Thread(()->{
            try {
                int r = task.get();
                System.out.println("Результат " + r);
            } catch (InterruptedException | ExecutionException e) {}
        });
        System.out.println("Step_4");

     //   System.out.println("Step_5");
    //    t2.start(); // таск t2 будет ждать запуска выполнения task
    //    task.run();
    }
}
