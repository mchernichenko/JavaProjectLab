package org.billing.jlab.innerclass;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Random;

/**
 * Демонстрация применения прокси-объектов
 */
public class ProxyTest {
    public static void main(String[] args) {
        Object[] elements = new Object[1000];

        // заполнить массив прокси-объектами целых чисел от 1 до 1000
        for (int i = 0; i < elements.length; i++) {
            Integer value = i + 1;
            TraceHandler handler = new TraceHandler(value);
            Object proxy = Proxy.newProxyInstance(null, new Class[]{Comparable.class}, handler);
            elements[i] = proxy;
        }

        Integer key = new Random().nextInt(elements.length) + 1;

        // поиск по критерию key
        int result = Arrays.binarySearch(elements, key);

        // вывод совпавшего элемента, если такой найден
        if (result >= 0) System.out.println(elements[result]);
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

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.print(target); //вывод неявного параметра
        System.out.print("." + method.getName() + "("); // вывод имени метода

        if (args != null) {
            for (int i = 0; i < args.length; i++) {
                System.out.print(args[i]);
                if (i < args.length - 1) System.out.print(", ");
            }
        }
        System.out.println(")");

        // вызов контретного метода
        return method.invoke(target, args);
    }
}
