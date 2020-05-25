package org.billing.jlab.javacore.javaagent;
/*
   Агент обязательно должен реализовывать метод premain
*/

public class Agent007 {
    public static void premain(String args) {
        System.out.println("Запуск java-агента Agent007");
    }
}