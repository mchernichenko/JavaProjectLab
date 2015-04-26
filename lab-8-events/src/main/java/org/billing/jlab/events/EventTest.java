package org.billing.jlab.events;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;

import static java.lang.Thread.sleep;

public class EventTest
{
    private JFrame frame;

    public static void main(String[] args) {
        new EventTest().go();
    }

    public void go(){
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                // начинается создание GUI с создание фрейма, т.е. с new JFrame() или с класса наследника JFrame
                //frame = new ButtonFrame();
                frame = new ActionFrame();

                // завершает работу программыы при закрытии окна, иначе оно будет висеть на экране вечно
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                // по умолчанию фрейм не видим, требуется явно указать его видимость
                frame.setVisible(true);

                // Чтобы перехватить событие закрытия фрейма приёмник событий должен реализовать интерфейс WindowListener
                // Здесь опеределяем анонимный класс расширяющий адаптер и переопределяющий метод отвечающий за выход из программы
                // От адаптера наследуются шесть методов, невыполняющих никаких действий
                frame.addWindowListener(new WindowAdapter(){

                    // Закрываем фрейм с задержкой в 2 сек.
                    @Override
                    public void windowClosing(WindowEvent e) {
                       // frame.jLabel.setText("ПОКА!!!"); // это строка что-то не работает
                        try {
                            sleep(2000);
                        } catch (InterruptedException e1) {
                            e1.printStackTrace();
                        }
                        System.exit(0);
                    }
                });

             }
         });
    }
}
