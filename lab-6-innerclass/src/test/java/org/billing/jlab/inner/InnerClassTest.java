package org.billing.jlab.inner;

import org.billing.jlab.reflection.ReflectionTest;
import org.junit.*;
import javax.swing.*;
import java.awt.event.ActionListener;


/**
 * Тестируем:
 *   - Обратные вызовы
 *   - Внутренние классы
 */
public class InnerClassTest {

    /**
     *  Тестируем обратный вызов. Используем обычный класс для реализации listener
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
     *  Тестируем обратный вызов. Используем внутренний класс для реализации listener
     * @throws Exception
     */
    @Test
    public void testInnerClass() throws Exception {
        TalkingClockInner talkingClock = new TalkingClockInner(2000, true);
        talkingClock.start();

        JOptionPane.showMessageDialog(null, "Выход?");
        System.exit(0);

    }

    /**
     *
     * @throws Exception
     */
    @Test
    public void testReflection() throws Exception {
        ReflectionTest.printClass("org.billing.jlab.inner.TalkingClockInner");
    //    ReflectionTest.printClass("org.billing.jlab.inner.TalkingClockInner$TimePrinter");
        // ReflectionTest.printClass("java.util.Date");
    }
}
