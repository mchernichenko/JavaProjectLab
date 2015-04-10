package org.billing.jlab.swingintro;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * JFrame - объект, который представляет собой окно на экране в которое помещаются все остальные элементы GUI
 * JFrame по-разному выглядит в зависимости от платформы
 */
public class SimpleFrameTest {

    JButton button;

    public static void main(String[] args) {
        SimpleFrameTest simpleFrameTest = new SimpleFrameTest();
        simpleFrameTest.go();
    }

    public void go() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {

                // начинается создание GUI с создание фрейма, т.е. с new JFrame() или с класса наследника JFrame
                SimpleFrame frame = new SimpleFrame();

                // создаём кнопку и регистрируем текущий класс в список слушателей. Слушатели должны реализовывать интерфейс ActionListener
                // Объект является источником событий, если он имеет метод addБлаБлаБлаListener - это шаблон наименования метода,
                // а в качестве аргумента интерфейс слушателя
                button = new JButton("Надпись на кнопке");
                button.addActionListener(new MyListener());

                // завершает работу программыы при закрытии окна, иначе оно будет висеть на экране вечно
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                // добавляем кнопку на панель фрейма
                frame.getContentPane().add(button);

                // по умолчанию фрейм не видим, требуется явно указать его видимость
                frame.setVisible(true);
            }
        });
    }

    class MyListener implements ActionListener {

        /**
         * Метод обработки события нажатия кнопки
         *
         * @param e
         */
        @Override
        public void actionPerformed(ActionEvent e) {
            button.setText("Опаньки");
        }
    }
}

/**
 * Класс наследник JFrame для установки начальных параметров фрейма
 */
class SimpleFrame extends JFrame {
    private static final int DAFAULT_WIDTH = 300;
    private static final int DAFAULT_HEIGHT = 200;

    public SimpleFrame() {
        // присваиваем фрейму размер
        setSize(DAFAULT_WIDTH, DAFAULT_HEIGHT);
    }
}
