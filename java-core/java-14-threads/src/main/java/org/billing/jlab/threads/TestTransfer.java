package org.billing.jlab.threads;

import org.billing.jlab.threads.exceptions.InsufficientFundsException;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class TestTransfer {
    public static void main(String[] args) throws InterruptedException {
        final Account a = new Account("Account_1", 1000);
        final Account b = new Account("Account_2", 2000);

        System.out.println("Баланс a=" + a.getBalance());
        System.out.println("Баланс b=" + b.getBalance());

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String threadName = Thread.currentThread().getName();
                    System.out.println(threadName + " Перенос a->b :300");
                    transferUseLock(a, b, 300);
                } catch (InsufficientFundsException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String threadName = Thread.currentThread().getName();
                    System.out.println(threadName + " Перенос b->a :500");
                    transferUseLock(b, a, 500);
                } catch (InsufficientFundsException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        Thread.sleep(4000);
        System.out.println(Thread.currentThread().getName() + " Баланс a=" + a.getBalance());
        System.out.println(Thread.currentThread().getName() + " Баланс b=" + b.getBalance());
    }

    /**
     * Трансфер с использованием синхронизационных блоков
     *
     * @param acc1
     * @param acc2
     * @param amount сумма перевода
     * @throws InsufficientFundsException если не хватает суммы на счёте 1
     * @throws InterruptedException
     */
    static void transferUseSync(Account acc1, Account acc2, int amount) throws InsufficientFundsException, InterruptedException {
        String threadName = Thread.currentThread().getName();

        if (acc1.getBalance() < amount) {
            throw new InsufficientFundsException();
        }

        System.out.println(threadName + " Попытка лока " + acc1.getName());
        synchronized (acc1) {
            System.out.println(threadName + " Получен лок " + acc1.getName());
            Thread.sleep(2000); // эта задержка моделирует deadlock
            System.out.println(threadName + " Попытка лока " + acc2.getName());
            synchronized (acc2) {
                System.out.println(threadName + " Получен лок " + acc2.getName());
                acc1.withdraw(amount);
                acc2.deposit(amount);
                System.out.println(threadName + " Перенос " + acc1.getName() + "->" + acc2.getName() + ":" + amount + " завершён успешно");
            }
            System.out.println(threadName + " Отпущен лок " + acc2.getName());
        }
        System.out.println(threadName + " Отпущен лок " + acc1.getName());
    }

    /**
     * Перевод суммы со счёта 1 на счет 2
     * Трансфер с использованием ReentrantLock
     *
     * @param acc1
     * @param acc2
     * @param amount сумма перевода
     * @throws InsufficientFundsException если не хватает суммы на счёте 1
     * @throws InterruptedException
     */
    static void transferUseLock(Account acc1, Account acc2, int amount) throws InsufficientFundsException, InterruptedException {
        String threadName = Thread.currentThread().getName();

        System.out.println(threadName + " Попытка лока " + acc1.getName());
        if (acc1.getLockAccont().tryLock(1, TimeUnit.SECONDS)) {
            try {
                System.out.println(threadName + " Получен лок " + acc1.getName());
                Thread.sleep(1000); // эта задержка моделирует deadlock
                System.out.println(threadName + " Попытка лока " + acc2.getName());
                if (acc2.getLockAccont().tryLock(1, TimeUnit.SECONDS)) {
                    System.out.println(threadName + " Получен лок " + acc2.getName());
                    try {
                        if (acc1.getBalance() < amount) {
                            throw new InsufficientFundsException();
                        }
                        acc1.withdraw(amount);
                        acc2.deposit(amount);
                        System.out.println(threadName + " Перенос " + acc1.getName() + "->" + acc2.getName() + ":" + amount + " завершён успешно");
                    } finally {
                        acc2.getLockAccont().unlock();
                    }
                    System.out.println(threadName + " Отпущен лок " + acc2.getName());
                } else {
                    acc2.incCountFailTransfer();
                    System.out.println(threadName + " Ошибка захвата " + acc2.getName());
                }
            } finally {
                acc1.getLockAccont().unlock();
            }
        } else {
            acc1.incCountFailTransfer();
            System.out.println(threadName + " Ошибка захвата " + acc1.getName());
        }
        System.out.println(threadName + " Отпущен лок " + acc1.getName());
    }
}
