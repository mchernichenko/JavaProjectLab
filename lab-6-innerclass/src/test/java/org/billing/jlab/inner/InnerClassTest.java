package org.billing.jlab.inner;

import org.billing.jlab.reflection.ReflectionTest;
import org.junit.Test;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Random;


/**
 * Тестируем:
 *   - Обратные вызовы
 *   - Внутренние/локальные/анонимные классы
 *   - Прокси-классы
 */
public class InnerClassTest {

    /**
     * Тестируем обратный вызов. Используем обычный класс для реализации listener
     *
     * @throws Exception
     */
    @Test
    public void testCallBack() throws Exception {
        ActionListener listener = new TimePrinter();

        // таймер, вызывающий обработчик событий через каждые 1 сек, т.е. через каждые 1 сек. вызывается метод
        // actionPerformed для переданного объекта listener
        Timer timer = new Timer(3000, listener); // 3000 миллисекунд = 1 секунда
        timer.start(); // запускаем таймер, который затем вызывает метод actionPerformed

        JOptionPane.showMessageDialog(null, "Выйти?");
        System.exit(0);
    }

    /**
     * Используем внутренний класс для реализации listener (обратного вызова).
     *
     * @throws Exception
     */
    @Test
    public void testInnerClass() throws Exception {
        TalkingClockInner talkingClock = new TalkingClockInner(2000, true);
        talkingClock.start();

        JOptionPane.showMessageDialog(null, "Выход?");
        System.exit(0);

    }

    @Test
    public void testLocalClass() throws Exception {
        TalkingClockLocal talkingClock = new TalkingClockLocal();
        talkingClock.start(1000, true);

        JOptionPane.showMessageDialog(null, "Выход?");
        System.exit(0);
    }

    @Test
    public void testAnonimousClass() throws Exception {
        TalkingClockAnonimous talkingClock = new TalkingClockAnonimous();
        talkingClock.start(1000, true);

        JOptionPane.showMessageDialog(null, "Выход?");
        System.exit(0);
    }

    @Test
    public void testStaticInnerClass() throws Exception {
        double[] doubles = new double[20];
        for (int i = 0; i < doubles.length; i++) {
            doubles[i] = 100 * Math.random();
        }
        ArrayAlg.Pair pair = ArrayAlg.minmax(doubles);
        System.out.println("min=" + pair.getFirst());
        System.out.println("max=" + pair.getSecond());
    }

    @Test
    public void testProxyClass() throws Exception {
        Object[] elements = new Object[1000];

        // заполнить массив прокси-объектами целых чисел от 1 до 1000
        for (int i = 0; i < elements.length; i++) {
            Integer value = i + 1;

            TraceHandler handler = new TraceHandler(value); // заключаем объекты в некоторую оболочку TraceHandler реализующую интерфейс InvocationHandler

            // 1. proxy-объекты реализуют все методы интерфейсов указанных в параметре. В данном случае в переметре передаём Comparable.class => в нём реализуется метод CompаreTo интерфейса Comparable.class.
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

             В конечном счете идёт сравнение объектов Integer, переданных в конструктор TraceHandler и их сортировка в массиве.

             Arrays.binarySearch(elements, key) -> elements[i].compаreTo(key) -> handler.invoke(elements[i], java.lang.Comparable, key) -> target.compаreTo(key)
          */
        int result = Arrays.binarySearch(elements, key);

        // вывод совпавшего элемента, если такой найден
        if (result >= 0) System.out.println(elements[result]);
    }

    /**
     * Анализ классов
     * @throws Exception
     */
    @Test
    public void testReflection() throws Exception {

        // анализ внешнеого класса
        // ReflectionTest.printClass("org.billing.jlab.inner.TalkingClockInner");

        // анализ внутреннего класса
        // ReflectionTest.printClass("org.billing.jlab.inner.TalkingClockInner$TimePrinter");

        // анализ локального класса
        ReflectionTest.printClass("org.billing.jlab.inner.TalkingClockLocal");
       // ReflectionTest.printClass("org.billing.jlab.inner.TalkingClockLocal$1TimePrinter");

        // ReflectionTest.printClass("java.util.Date");
    }
}
