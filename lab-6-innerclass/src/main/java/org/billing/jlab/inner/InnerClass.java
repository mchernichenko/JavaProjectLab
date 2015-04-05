package org.billing.jlab.inner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

/**
 * Рассматривается применение внутренних классов.
 * Самое важное то, что для JVM не существует внутренних классов. Компилятор все внутренние классы преобразует в обычные.
 * Для их обозначения используется разделитель $. Например, TalkingClockInner$TimePrinter.class
 *
 * Основные моменты:
 * - Объект внутреннего класса имеет доступ к данным объекта в котором он определён, а также имеет доступ к закрытым
 *   данным, но лишь того внешнего класса , в который он сам входит.
 *
 *   Это достигается за счет:
 *   1. неявной передачи во все конструкторы внутренних классов ссылки на внешний класс. Сама ссылка перезаётся при создании экземпляра внутреннего
 *      класса ОбъектВнешнегоКлаасса.new ВнутреннийКласс
 *        public TalkingClockInner$TimePrinter(TalkingClockInner);
 *   2. генерации дополнительного во внутреннем классе поля для сохранения ссылки на внешний класс
 *       final TalkingClockInner this$0;
 *   3. создания статического метода во внешем классе, т.е. геттера для закрытого поля.
 *       static boolean access$000(TalkingClockInner);
 *
 * - Внутренний класс можно скрыть от других классов того же пакета
 * - Если внутренний класс public, то его можно создать так:
 *    ВнешнийКласс yyy = new Внешнийкласс()
 *    ВнешнийКласс.ВнутреннийКласс xxx = yyy.new ВнутреннийКласс
 *  (!)Без внешнего класса внутренний не создать
 *
 * http://www.quizful.net/post/inner-classes-java
 */
public class InnerClass
{
    public static void main(String[] args) {
        TalkingClockInner talkingClock = new TalkingClockInner(1000, true);
        talkingClock.start();

        JOptionPane.showMessageDialog(null, "Выход?");
        System.exit(0);
    }
}

/**
 * Часы, выводящие время через регулярные промежутки времени.
 */
class TalkingClockInner {
    private int interval;
    private boolean beep;

    /**
     * @param beep     - признак включения звукового сигнала (true - включить звук)
     * @param interval - интервал между сообщениями (в миллисекундах)
     */
    public TalkingClockInner(int interval, boolean beep) {
        this.beep = beep;
        this.interval = interval;
    }

    // Неявный геттер для защищённого поля beep, который используется во внутреннем классе
    // static boolean access$000(TalkingClockInner);

    /**
     * Запуск часов c помощью внутреннего класса
     */
    public void start() {

        ActionListener listener = this.new TimePrinter(); // создание экземпляра внутреннего класса. this -ссылка на внешний класс, которая затем передаётся в конструктор
        Timer timer = new Timer(interval,listener);
        timer.start();
    }

    /* внутренний класс, экземпляры которого создаются внешним классом TalkingClock
       Компилятор передает во внутренний класс ссылку на объект внешнего класса. Делает он это с помощью видоизменения
       всех конструкторов внутреннего класса, добавляя параметр для ссылки на внешний класс.
       В данном случае, т.к. конструкторов нет, то используется конструктор по умолчанию следующего вида
        public TimerPrinter (TalkingClock clock) { outer = clock }

    */
    public class TimePrinter implements ActionListener {
        // ссылка на внешний класс добавляется неявно компилятором и инициализируется в конструкторе, в который передается ссылка на внешний объект
        //
        // final TalkingClockInner this$0;

        @Override
        public void actionPerformed(ActionEvent e) {
            Date now = new Date();
            System.out.println("The time is " + now);
            if (beep) Toolkit.getDefaultToolkit().beep(); // beep поле внешнего класса access$000(outer) к которому имеет доступ внутренний класс
        }
    }

}
