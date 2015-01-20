package org.billing.jlab.intro;

import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *   Пример работы с датами. В Java время и его представление разнесены по разным классам.
 *   Класс Date() отражает только конкретный момент времени sysdate, но для работы с датами он не используется!
 *   Необходимо использовать абстрактный класс Calendar, описывающий свойства календаря в целом
 */
public class MyCalendar {

    public static void my_calendarik() {
        // построить объект текущей датой
        Calendar d = new GregorianCalendar();

        int today = d.get(Calendar.DAY_OF_MONTH); // дата, хранящаясы в объекте d, т.е. текущая дата
        int month = d.get(Calendar.MONTH);  // месяц, хранящийся в объекте d, т.е текущий месяц

        // установить объект d в начало месяца
        d.set(Calendar.DAY_OF_MONTH, 1);

        int weekday = d.get(Calendar.DAY_OF_WEEK); // день недели первого дня месяца (1- Вс, 2-Пн и т.д), т.к. дату установили в начало месяца

        int firstDayOfWeek = d.getFirstDayOfWeek(); // первый день недели, для России неделя начинается с пн-ка, т.е. с 2

        System.out.println("weekday = " + weekday + " " + firstDayOfWeek);
        // определить отступ в первой строке
        int indent = 0;
        while (weekday != firstDayOfWeek) {
            indent++;
            d.add(Calendar.DAY_OF_MONTH, -1);
            weekday = d.get(Calendar.DAY_OF_WEEK);
            System.out.println("weekday = " + weekday + " " + d.get(Calendar.DAY_OF_MONTH));
        }

        // считываем в массиви названия дней недели ([Вс, Пн, Вт, Ср, Чт, Пт, Сб])
        String[] weekdayNames = new DateFormatSymbols().getShortWeekdays();
        //System.out.println("weekdayNames = " + Arrays.toString(weekdayNames));

        // выводим названия недель, начиная с Пн-ка (а не с Вс как в массиве)
        do {
            System.out.printf("%4s", weekdayNames[weekday]); // начинаем с ширина 4
            d.add(Calendar.DAY_OF_MONTH, 1);
            weekday = d.get(Calendar.DAY_OF_WEEK);
        }
        while (weekday != firstDayOfWeek);
        System.out.println();
        for (int i = 0; i < indent; i++) {
            System.out.println(" ");
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

            // ночать очередную неделю с новой строки
            if (weekday == firstDayOfWeek) System.out.println();
        }
        while (d.get(Calendar.MONTH) == month);
        // завершить цикл когда в объекте d установится 1 денб след. месяца

        // перевести строку если требуется
        if (weekday != firstDayOfWeek) System.out.println();
    }

    /**
     * метод на поиграться с датами
    */
    public static void testCalendar() {
        Date sysdate = new Date();
        //
        System.out.printf("Format1 = %1$tc, \nFormat2 = %1$tT", sysdate);
        System.out.println();

        Calendar calendar = Calendar.getInstance(); // получение ссылки на созданный по умолчанию объект GregorianCalendar

//        можно его создать явно
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
    }
}
