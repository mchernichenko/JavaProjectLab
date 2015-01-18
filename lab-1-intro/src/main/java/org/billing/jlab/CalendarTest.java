package org.billing.jlab;

import java.util.*;

/**
 * Класс Date устарел
 * http://jexp.ru/index.php/Java_Tutorial/Development/TimeZone
 */
public class CalendarTest {
    public static void main(String[] args) {
        Date currentTime = new Date(); // Класс Date устарел
        GregorianCalendar localCurrentTime = new GregorianCalendar(); // дата и момент времени создания объекта (Локальная таймзона)
        Calendar dateTimeZone = new GregorianCalendar(TimeZone.getTimeZone("Europe/Moscow")); // время по определённой таймзоне
        GregorianCalendar date = new GregorianCalendar(2015, Calendar.JANUARY, 1); // произвольная дата
        GregorianCalendar dateTime = new GregorianCalendar(2015, Calendar.JANUARY, 1, 23, 59, 59); // произвольная дата и время

//      Получение отдельных атрибутов даты из объекта GregorianCalendar
        System.out.println("Текущая дата объекта Date: " + currentTime);
        System.out.println("Месяц: " + localCurrentTime.get(Calendar.MONTH));
        System.out.println("День недели: " + localCurrentTime.get(Calendar.DAY_OF_WEEK));
        System.out.println("Номер недели в году: " + localCurrentTime.get(Calendar.WEEK_OF_YEAR));
        System.out.println("Кол-во часов: " + localCurrentTime.get(Calendar.HOUR_OF_DAY));
        System.out.println("Смещение таймзоны: " + localCurrentTime.get(Calendar.ZONE_OFFSET) / (60 * 60 * 1000));
        System.out.println("DST_OFFSET: " + localCurrentTime.get(Calendar.DST_OFFSET));
        System.out.printf("Время по выбранной таймзоне: %04d-%02d-%02d %02d:%02d:%02d\n",
                dateTimeZone.get(Calendar.YEAR),
                dateTimeZone.get(Calendar.MONTH), //0..11
                dateTimeZone.get(Calendar.DAY_OF_MONTH),
                dateTimeZone.get(Calendar.HOUR_OF_DAY),
                dateTimeZone.get(Calendar.MINUTE),
                dateTimeZone.get(Calendar.SECOND)
        );

//      Изменение даты, т.е. изменение состояния объекта GregorianCalendar
        date.set(Calendar.YEAR, 2014);
        date.set(Calendar.DAY_OF_WEEK, 15);
        date.set(2015, Calendar.APRIL, 15); // либо можно изменить сразу несколько атрибутов
        System.out.printf("Изменили дату: %04d-%02d-%02d %02d:%02d:%02d\n ",
                date.get(Calendar.YEAR),
                date.get(Calendar.MONTH)+1, //0..11
                date.get(Calendar.DAY_OF_MONTH),
                date.get(Calendar.HOUR_OF_DAY),
                date.get(Calendar.MINUTE),
                date.get(Calendar.SECOND)
        );

//      Смещение даты
        date.add(Calendar.MONTH, 3); // месяц для даты "date" сдвинуть на 3
        System.out.println("Сдвинули месяц на 3: " + date.get(Calendar.MONTH));

//      Преобразование объектов GregorianCalendar в Date и обратно.
        Date time = date.getTime();
        System.out.println("Преобразовали GregorianCalendar в Date: " + time);
        dateTime.setTime(time);
        System.out.println("Преобразовали Date в GregorianCalendar: " + dateTime);

        TimeZone timeZone = TimeZone.getDefault();
        System.out.println("getRawOffset: " + timeZone.getRawOffset() / (60 * 60 * 1000));

//      проверка таймзон
        Calendar cal = new GregorianCalendar();
        System.out.printf("Local time: %04d-%02d-%02d %02d:%02d:%02d\n", cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal.get(Calendar.DAY_OF_MONTH),cal.get(Calendar.HOUR_OF_DAY),cal.get(Calendar.MINUTE),cal.get(Calendar.SECOND));

        cal = new GregorianCalendar(TimeZone.getTimeZone("Europe/Moscow"));
        System.out.printf("Moscow time: %04d-%02d-%02d %02d:%02d:%02d\n", cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), cal.get(Calendar.SECOND));

        cal = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
        System.out.printf("UTC time: %04d-%02d-%02d %02d:%02d:%02d\n", cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), cal.get(Calendar.SECOND));

//        -----
        String[] ids = TimeZone.getAvailableIDs(2*60*60*1000);
        SimpleTimeZone simpleTimeZone = new SimpleTimeZone(2 * 60 * 60 * 1000, ids[0]);
        Calendar date1 = new GregorianCalendar(simpleTimeZone);
        System.out.println("ids: " + date1.getTime());
        Date trialTime1 = new Date();
        date1.setTime(trialTime1);
        System.out.println("ids: " + date1.getTime());

    }
}
