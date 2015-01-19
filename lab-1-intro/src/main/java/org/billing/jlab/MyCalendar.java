package org.billing.jlab;

import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *   Пример работы с датами. В Java время и его представление разнесены по разным классам.
 *   Класс Date() отражает только конкретный момент времени sysdate, но для работы с датами он не используется!
 *   Необходимо использовать абстрактный класс Calendar, описывающий свойства календаря в целом.
 *   Пример:
        Пн  Вт  Ср  Чт  Пт  Сб  Вс
                     1   2   3   4
         5   6   7   8   9  10  11
        12  13  14  15  16  17  18
        19* 20  21  22  23  24  25
        26  27  28  29  30  31
 */
public class MyCalendar {

    public static void my_calendarik() {
        // построить объект текущей датой
        Calendar d = new GregorianCalendar();

        int today = d.get(Calendar.DAY_OF_MONTH); // дата, хранящаяся в объекте d, т.е. текущая дата
        int month = d.get(Calendar.MONTH);  // месяц, хранящийся в объекте d, т.е текущий месяц

        // установить объект d в начало месяца
        d.set(Calendar.DAY_OF_MONTH, 1);

        int weekday = d.get(Calendar.DAY_OF_WEEK); // 1..7, день недели первого дня месяца (1- Вс, 2-Пн и т.д), т.к. дату установили в начало месяца

        int firstDayOfWeek = d.getFirstDayOfWeek(); // первый день недели, для России неделя начинается с пн-ка, т.е. с 2

        // определить отступ в первой строке
        int indent = 0;
        while (weekday != firstDayOfWeek) {
            indent++;
            d.add(Calendar.DAY_OF_MONTH, -1);
            weekday = d.get(Calendar.DAY_OF_WEEK);
        }

        // считываем в массиви названия дней недели ([Вс, Пн, Вт, Ср, Чт, Пт, Сб])
        String[] weekdayNames = new DateFormatSymbols().getShortWeekdays();

        System.out.println("\n---  Календарик на текущий месяц ----");
        // выводим названия недель, начиная с Пн-ка (а не с Вс как в массиве)
        do {
            System.out.printf("%4s", weekdayNames[weekday]); // начинаем с ширина 4
            d.add(Calendar.DAY_OF_MONTH, 1);
            weekday = d.get(Calendar.DAY_OF_WEEK);
        }
        while (weekday != firstDayOfWeek);
        System.out.println();
        for (int i = 0; i < indent; i++) {
            System.out.printf("%4s", " ");  // устанавливаем курсор под первый день недели
        }

        d.set(Calendar.DAY_OF_MONTH, 1);
        do {
            // выввести день недели
            int day = d.get(Calendar.DAY_OF_MONTH);
            System.out.printf("%3d", day);

            // пометить текущий день *
            if (day == today) System.out.print("*");
            else System.out.print(" ");

            // продвинуть объект d к следующему дню
            d.add(Calendar.DAY_OF_MONTH, 1);
            weekday = d.get(Calendar.DAY_OF_WEEK);

            // начать очередную неделю с новой строки
            if (weekday == firstDayOfWeek) System.out.println(); // если день недели Пн, то выводим его с новой строки
        }
        while (d.get(Calendar.MONTH) == month); // пока текущий месяц
        // завершить цикл когда в объекте d установится 1 день след. месяца

        // перевести строку если требуется
        if (weekday != firstDayOfWeek) System.out.println();
    }
}
