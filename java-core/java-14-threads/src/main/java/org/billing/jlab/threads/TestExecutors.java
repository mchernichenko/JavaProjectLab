package org.billing.jlab.threads;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class TestExecutors {

    public static void use(Callable<Integer> expression) {}

    public static void main(String[] args) throws InterruptedException {
        use(() -> {throw new IOException();});

        ExecutorService executor = Executors.newFixedThreadPool(3);
        Account acc1 = new Account("Account_1", 1000);
        Account acc2 = new Account("Account_2", 2000);
        System.out.println("Баланс a=" + acc1.getBalance());
        System.out.println("Баланс b=" + acc2.getBalance());
        Random rnd = new Random();

        List<Future<Boolean>> futureList = new ArrayList<>();

        try {
            for (int i = 0; i < 10; i++) {
                Transfer tr = new Transfer(acc1, acc2, 400);
                tr.setID(i);
                futureList.add(executor.submit(tr));
            }
        } finally {
            if (executor != null) {
                executor.shutdown();
            }
        }

        executor.awaitTermination(10, TimeUnit.SECONDS); // ждём не более 10 сек. пока все таски не завершаться
        System.out.println(Thread.currentThread().getName() + " Баланс a=" + acc1.getBalance());
        System.out.println(Thread.currentThread().getName() + " Баланс b=" + acc2.getBalance());

        System.out.println("Неудачных блокировок на " + acc1.getName() + "=" + acc1.getCountFailTransfer());
        System.out.println("Неудачных блокировок на " + acc2.getName() + "=" + acc2.getCountFailTransfer());

        System.out.println("-------------------------------------------");
        int i = 1;
        for (Future<Boolean> future : futureList) {
            try {
                System.out.println( i++ + ". Результат выполнения " + future.get());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
