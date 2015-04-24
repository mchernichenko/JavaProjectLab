package org.billing.jlab.inner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

/**
 * Рассматривается применение локальных классов.
 * Основное отличие от внутренних классов это, то что дополнительно имеют доступ к локальным (final) переменным
 * Для этого компилятор синтезирует в локальном классе соответствующие переменные значения которых
 * передаётся и инициализируется в конструкторе.
 *
 *  Основные причины использования внутреннего класса:
 *  1. Класс используется лишь однажды и больше он никому не нужен.
 *
 * Основные моменты:
 * 1. локальные классы никогда не используют модификаторов доступа. Их область действия всегда ограничена блоком, в котором они объявлены.
 * 2. Они полностью скрыты от внешнего кода и даже от внешнего класса. О его существовании известно только методу где он объявлен.
 * 3. Локальные классы дополнительно имеют доступ к локальным переменным (объявленным как final)
 *
 * http://www.quizful.net/post/inner-classes-java
 */
public class LocalClass
{
    public static void main(String[] args) {
        TalkingClockLocal talkingClock = new TalkingClockLocal();
        talkingClock.start(1000, true);

        JOptionPane.showMessageDialog(null, "Выход?");
        System.exit(0);
    }
}

/**
 * Часы, выводящие время через регулярные промежутки времени.
 */
class TalkingClockLocal {

    // фиктивное поле, чтобы показать генерацию static boolean access$000(org.billing.jlab.inner.TalkingClockLocal);
    // прииспользовании переменной в локальном классе
    private boolean aBoolean = true;

    /**
     * Запуск часов с помощью локального класса.
     * При создании локального классы, компилятор отслеживает использование всех финальных локальных переменных и
     * во все его конструкторы неявным образом передаётся ссылка на внешний класс и используемые финальные локальные переменные,
     * которые копируются в финальные переменные локального класса, синтезируемые компилятором.
     *
     * Методы локального класса могут ссылаться только на локальные переменные, объявленные как final !
     * Этим гарантируется, что локальная переменная и её копия, созданная в локальном классе, всегда имеют одно и тоже значение.
     *
     */
    public void start(int interval, final boolean beep) {

        /*
         * Локальные классы всегда объявляются без модификатора доступа. Их область действия всегда ограничена блоком, в котором он объявлен
         * Они польностью скрыты от внешнего мира и даже от класса TalkingClock. О классе знает только метод start()
         */
        class TimePrinter implements ActionListener
        {
            // Синтезируемые компилятором поля
            // final boolean val$beep;  -- финальная переменная передаётся в конструктов и размещается в данном поле
            // final org.billing.jlab.inner.TalkingClockLocal this$0; -- ссылка на внешний класс, передаваемый через конструктор

            // Синтезируемый компилятором конструктор
            // TalkingClockLocal$1TimePrinter(TalkingClockLocal, boolean);

            @Override
            public void actionPerformed(ActionEvent e) {
                Date now = new Date();
                System.out.println("(use local class) The time is " + now);
                if (beep) Toolkit.getDefaultToolkit().beep();
                if (aBoolean) System.out.println("ok");
            }
        }

        ActionListener listener = new TimePrinter(); // создание экземпляра внутреннего локального класса
        Timer timer = new Timer(interval,listener);
        timer.start();
    }

}
