package org.billing.jlab.inner;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Random;

/**
 * Демонстрация применения прокси-классов, реализующие произвольные интерфейсы
 *
 * <a href="http://www.ibm.com/developerworks/ru/library/j-jtp08305/">Декорирование при помощи динамического прокси</a>
 * <a href="http://docs.oracle.com/javase/8/docs/technotes/guides/reflection/proxy.html">Dynamic Proxy Classes</a>
 *
 * Используется для:
 * - Переадресация вызовов методов на удалённый сервер.
 * - Связывание событий GUI с определёнными действиями а программе
 * - Отслеживание вызовов методов при отдалке, которое рассматривается в примере
 *
 */
public class ProxyTest {
    public static void main(String[] args) {
        Object[] elements = new Object[1000];

        // заполнить массив прокси-объектами целых чисел от 1 до 1000
        for (int i = 0; i < elements.length; i++) {
            Integer value = i + 1;

            TraceHandler handler = new TraceHandler(value); // заключаем объекты в некоторую оболочку TraceHandler реализующую интерфейс InvocationHandler

            // 1. proxy-объекты реализуют все методы указанных в параметре интерфейсов. В данном случае в нём реализуется метод CompаreTo интерфейса Comparable.class.
            // 2. Если для прокси-объекта вызывается метод CompаreTo, то он в свою очередь вызывает метод invoke определённого в обработчике, а он там есть всегда, т.к. обработчик handler должен реализовать интерфейс InvocationHandler
            //    передаваемый в явном параметре newProxyInstance создания прокси-объекта
            // 3. В invoke передается вызываемый метод - CompаreTo и его аргументы
            // 4. В invoke для переданного объекта вызывается указанный метод с аргументами c помощью рефлексии: method.invoke(target, args);
            Object proxy = Proxy.newProxyInstance(null, new Class[]{Comparable.class}, handler);
            elements[i] = proxy;
        }

        Integer key = new Random().nextInt(elements.length) + 1;

        // поиск по критерию key
        /* стек вызова следующий:
            1. метод Arrays.binarySearch вызывает elements[i].compаreTo(key), где  elements[i] объект класса Proxy
            2. объект динамически созданного класса $Proxy0 также реализует интерфейс Comparable, т.к. он передан при создании прокси-объекта
            3. В методе compаreTo класса Proxy вызывается метод handler.invoke(elements[i], java.lang.Comparable, key)
            4. handler.invoke вызывает compаreTo для параметра TraceHandler.target, т.е. по сути с value, который был передан в TraceHandler

             В конечном счете идёт сравнение объектов Integer и их сортировка в массиве

             Arrays.binarySearch(elements, key) -> elements[i].compаreTo(key) -> handler.invoke(elements[i], java.lang.Comparable, key) -> target.compаreTo(key)
          */
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

    /**
     * В данном случае вызывается при вызове elements[i].compаreTo(key) в методе Arrays.binarySearch
     * @param proxy - прокси-объект elements[i], который содержит TraceHandler
     * @param method - java.lang.Comparable
     * @param args - key
     * @return - возвращает в данном случае результат выполнения target.compаreTo(key)
     * @throws Throwable
     */
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

        // вызов контретного метода, в данном случае конкретный объект Integer сравнивается к key, т.е. target.compаreTo(key)
        return method.invoke(target, args);
    }
}
