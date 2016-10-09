package org.billing.jlab.inner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

/**
 * Рассматривается применение анонимных классов.
 * Основное отличие от локальных классов это то, что не имеют имени. Всё остальное см. комментарии к LocalClass
 *
 *  Основные причины использования анонимного класса:
 *  1. Класс создаётся лишь однажды и соответственно может не иметь имени
 *
 *  Основные моменты:
 *  1. не имеет конструктора, необходимые для создания объекта параметры передаются конструктору суперкласса
 *
 * Основная структура:
     new СуперТип(параметры конструирования объекта){
          методы и даннные внутреннего класса
     }
  ИЛИ
    new ТипИнтерфейса(){
        методы и данные
    }

    2. Механизм внутренних классов автоматически генерирует конструктор, размещающий в памяти все локальные конечные переменные (см. LocalClass)

 * http://www.quizful.net/post/inner-classes-java
 * http://www.javenue.info/post/7
 */
public class AnonimousClass
{
    public static void main(String[] args) {
        TalkingClockAnonimous talkingClock = new TalkingClockAnonimous();
        talkingClock.start(1000, true);

        JOptionPane.showMessageDialog(null, "Выход?");
        System.exit(0);
    }
}

/**
 * Часы, выводящие время через регулярные промежутки времени.
 */
class TalkingClockAnonimous {

    public void start(int interval, final boolean beep) {

        ActionListener listener = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date now = new Date();
                System.out.println("(use anonimous class) The time is " + now);
                if (beep) Toolkit.getDefaultToolkit().beep();
            }
        };

        Timer timer = new Timer(interval,listener);
        timer.start();
    }
}
