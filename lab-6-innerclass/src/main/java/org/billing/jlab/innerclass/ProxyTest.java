package org.billing.jlab.innerclass;

import java.lang.reflect.InvocationHandler;

/**
 * Демонстрация применения прокси-объектов
 */
public class ProxyTest {
    public static void main(String[] args) {
        Object[] objects = new Object[1000];

    }
}

class TraceHandler implements InvocationHandler {
    private Object target;

    /**
     * Конструирование объекта.
     * @param target - неявный параметр вызова метода
     */
    public TraceHandler(Object target) {
        this.target = target;
    }
}
