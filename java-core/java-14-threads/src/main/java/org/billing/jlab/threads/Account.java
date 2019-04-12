package org.billing.jlab.threads;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class Account {
    private int balance;
    private String name;
    private AtomicInteger countFailTransfer = new AtomicInteger(0); // количество неуспешных трансферов на лиц. счёте
    private ReentrantLock lockAccount = new ReentrantLock();

    public Account(String name, int initBalance)
    {
        this.name = name;
        this.balance = initBalance;
    }

    public int getBalance() {
        return balance;
    }

    public String getName() {
        return name;
    }

    public AtomicInteger getCountFailTransfer() {
        return countFailTransfer;
    }

    public int incCountFailTransfer() {
        return countFailTransfer.addAndGet(1);
    }

    public ReentrantLock getLockAccont() {
        return lockAccount;
    }

    public void withdraw(int summa) {
        balance -= summa;
    }

    public void deposit(int summa) {
        balance += summa;
    }
}
