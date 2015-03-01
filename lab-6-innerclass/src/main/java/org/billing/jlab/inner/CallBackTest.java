package org.billing.jlab.inner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

/**
 * Обратные вызовы
 */
public class CallBackTest {
    public static void main(String[] args) {
        ActionListener listener = new TimePrinter();

        // таймер, вызывающий обработчик событий через каждые 1 сек, т.е. через каждые 1 сек. вызывается метод
        // actionPerformed для переданного объекта listener
        Timer timer = new Timer(1000, listener); // 1000 миллисекунд = 1 секунда
        timer.start(); // запускаем таймер, который затем вызывает метод actionPerformed

        JOptionPane.showMessageDialog(null, "Выйти?");
        System.exit(0);
    }

}

class TimePrinter implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        Date now = new Date();
        System.out.println("The time is " + now);
        Toolkit.getDefaultToolkit().beep();
    }
}
