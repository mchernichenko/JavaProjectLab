package org.billing.jlab.inner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

/**
 * Рассматривается применение внутренних классов.
 * Самое важное то, что для JVM не существует внутренних классов. Компилятор все внутренние классы преобразует в обычные.
 * Для их обозначения используется разделитель $. Например, TalkingClock$TimePrinter.class
 *
 * Основные моменты:
 * - Объект внутреннего класса имеет доступ к данным объекта в котором он определён, включая закрытые.
 *    т.к. компилятор видоизменяет все конструкторы внутренних классов, добавляя параметр для ссылки на внешний класс.
 *
 *
 * - Внутренний класс можно скрыть от других классов того же пакета
 * <p/>
 * Рассматриваются:
 * -
 *
 * http://www.quizful.net/post/inner-classes-java
 */
public class LocalClass
{
    public static void main(String[] args) {
        TalkingClock1 talkingClock = new TalkingClock1(1000, true);
        //talkingClock.start();
        talkingClock.start(1000, true);

        JOptionPane.showMessageDialog(null, "Выход?");
        System.exit(0);
    }
}


/**
 * Часы, выводящие время через регулярные промежутки времени.
 */
class TalkingClock1 {
    private int interval;
    private boolean beep;

    /**
     * @param beep     - признак включения звукового сигнала (true - включить звук)
     * @param interval - интервал между сообщениями (в миллисекундах)
     */
    public TalkingClock1(int interval, boolean beep) {
        this.beep = beep;
        this.interval = interval;
    }

    /**
     * Запуск часов с помощью локального класса.
     * При создании локального классы, компилятор отслеживает использование всех локальных переменных и
     * во все его конструкторы неявным образом передаётся ссылка на внешний класс и используемые локальные переменные,
     * которые копируются в финальные переменные локального класса, синтезируемые компилятором.
     * Скомпилированный локальный класс имеет вид:
     *
     *
     * Методы локального класса могут ссылаться только на локальные переменные, объявленные как final !
     * Этим гарантируется, что локальная переменная и её копия, созданная в локальном классе, всегда имеют одно и тоже значение.
     *
     */
    public void start(int interval, final boolean beep) {

        class TimerPrinterLocal implements ActionListener
        {
            /**
             * Локальные классы всегда объявляются без модификатора доступа. Их область действия всегда ограничена блоком, в котором он объявлен
             * Они польностью скрыты от внешнего мира и даже от класса TalkingClock. О классе знает только метод start()
             * @param e
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                Date now = new Date();
                System.out.println("(use local inner class) The time is " + now);
                if (beep) Toolkit.getDefaultToolkit().beep();
            }
        }

        ActionListener listener = new TimerPrinterLocal(); // создание экземпляра внутреннего локального класса
        Timer timer = new Timer(interval,listener);
        timer.start();
    }

}
