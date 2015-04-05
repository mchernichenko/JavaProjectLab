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

    @Test
    public void testInnerClass() throws Exception {
        TalkingClock talkingClock = new TalkingClock(2000, true);
        talkingClock.start();

        JOptionPane.showMessageDialog(null, "Выход?");
        System.exit(0);

    }

    @Test
    public void testReflection() throws Exception {
        ReflectionTest.printClass("org.billing.jlab.inner.TalkingClock$TimePrinter");
        // ReflectionTest.printClass("java.util.Date");
    }
}
