package org.billing.jlab.threads;

import org.billing.jlab.threads.exceptions.InsufficientFundsException;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * Имитируем взаимную блокировку
 */
public class Transfer implements Callable<Boolean> {
    Account acc1;
    Account acc2;
    int amount;

    int ID;
    String threadName = Thread.currentThread().getName();

    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

    public void setID(int ID) {
        this.ID = ID;
    }

    public Transfer(Account acc1, Account acc2, int amount) {
        this.acc1 = acc1;
        this.acc2 = acc2;
        this.amount = amount;
    }

    @Override
    public Boolean call() throws Exception {
        return transferUseLock(acc1, acc2, amount);
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

    Boolean transferUseLock(Account acc1, Account acc2, int amount) throws InsufficientFundsException, InterruptedException {

        printMessage("[%s] --- %s " + " Попытка лока " + acc1.getName() + " +++ " + ID);
        if (acc1.getLockAccont().tryLock(1, TimeUnit.SECONDS)) {
            try {
                printMessage("[%s] --- %s " + " Получен лок " + acc1.getName());
                Thread.sleep(1000); // эта задержка моделирует deadlock
                printMessage("[%s] --- %s " +" Попытка лока " + acc2.getName());
                if (acc2.getLockAccont().tryLock(1, TimeUnit.SECONDS)) {
                    printMessage("[%s] --- %s " + " Получен лок " + acc2.getName());
                    try {
                        if (acc1.getBalance() < amount) {
                            System.out.println("ERR: Недостатоный баланс");
                            throw new InsufficientFundsException();
                        }
                        acc1.withdraw(amount);
                        acc2.deposit(amount);
                        System.out.println(String.format("[%s] --- %s Перенос " + acc1.getName() + "->" + acc2.getName() + ":" + amount + " завершён успешно", Thread.currentThread().getName(), LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)));
                    } finally {
                        acc2.getLockAccont().unlock();
                    }
                    printMessage("[%s] --- %s " + " Отпущен лок " + acc2.getName());
                } else {
                    acc2.getCountFailTransfer().addAndGet(1);
                    printMessage("[%s] --- %s " +" Ошибка захвата " + acc2.getName());
                }
            } finally {
                acc1.getLockAccont().unlock();
            }
        } else {
            acc1.getCountFailTransfer().addAndGet(1);
            printMessage("[%s] --- %s " + " Ошибка захвата " + acc1.getName());
        }
        printMessage("[%s] --- %s " + " Отпущен лок " + acc1.getName());
        return true;
    }

    private void printMessage(final String templ)
    {
        String text = String.format(templ, Thread.currentThread().getName(), LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        System.out.println(text);
    }
}

