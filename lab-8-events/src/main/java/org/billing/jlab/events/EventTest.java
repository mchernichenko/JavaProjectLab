package org.billing.jlab.events;

import javax.swing.*;
import java.awt.*;

public class EventTest
{
    private ButtonFrame frame;

    public static void main(String[] args) {
        new EventTest().go();
    }

    public void go(){
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                // начинается создание GUI с создание фрейма, т.е. с new JFrame() или с класса наследника JFrame
                frame = new ButtonFrame();

                // завершает работу программыы при закрытии окна, иначе оно будет висеть на экране вечно
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                // по умолчанию фрейм не видим, требуется явно указать его видимость
                frame.setVisible(true);
             }
         });
    }
}
