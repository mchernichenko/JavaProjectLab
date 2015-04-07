package org.billing.jlab.inner;

import org.billing.jlab.reflection.ReflectionTest;
import org.junit.Test;

import javax.swing.*;
import java.awt.event.ActionListener;


/**
 * Тестируем:
 *   - Обратные вызовы
 *   - Внутренние классы
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
