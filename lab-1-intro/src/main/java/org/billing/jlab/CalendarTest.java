package org.billing.jlab;

import java.util.*;

/**
 * http://jexp.ru/index.php/Java_Tutorial/Development/TimeZone
 */
public class CalendarTest {
    public static void main(String[] args) {
        String months[] = {"Январь", "Февраль", "Март", "Апрель",
                            "Май", "Июнь", "Июль", "Август",
                            "Сентябрь", "Октябрь", "Ноябрь", "Декабрь"};

// Класс Date устарел и не позволяет получать индивидуальные компоненты даты и времени. Нужно использовать Calendar
        Date currentTime = new Date();
        System.out.printf("Format1 = %1$tc, \nFormat2 = %1$tT", currentTime);
        System.out.println();

        GregorianCalendar localCurrentTime = new GregorianCalendar(); // дата и момент времени создания объекта (Локальная таймзона)
        Calendar dateTimeZone = new GregorianCalendar(TimeZone.getTimeZone("Europe/Moscow")); // время по определённой таймзоне
        GregorianCalendar date = new GregorianCalendar(2015, Calendar.JANUARY, 1); // произвольная дата
        GregorianCalendar dateTime = new GregorianCalendar(2015, Calendar.JANUARY, 1, 23, 59, 59); // произвольная дата и время

//      Получение отдельных атрибутов даты из объекта GregorianCalendar
        System.out.println("Текущая дата объекта Date: " + currentTime);
        System.out.println("Месяц: " + months[localCurrentTime.get(Calendar.MONTH)]);
        System.out.println("День недели: " + localCurrentTime.get(Calendar.DAY_OF_WEEK));
        System.out.println("Номер недели в году: " + localCurrentTime.get(Calendar.WEEK_OF_YEAR));
        System.out.println("Кол-во часов: " + localCurrentTime.get(Calendar.HOUR_OF_DAY));
        System.out.println("Смещение локальной таймзоны: " + localCurrentTime.get(Calendar.ZONE_OFFSET) / (60 * 60 * 1000));
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
                date.get(Calendar.MONTH) + 1, //0..11
                date.get(Calendar.DAY_OF_MONTH),
                date.get(Calendar.HOUR_OF_DAY),
                date.get(Calendar.MINUTE),
                date.get(Calendar.SECOND)
        );

//      Смещение даты
        date.add(Calendar.MONTH, 3); // месяц для даты "date" сдвинуть на 3
        System.out.println("Сдвинули месяц на 3: " + date.get(Calendar.MONTH));


// Преобразование объектов GregorianCalendar в Date и обратно.
        Date time = date.getTime();
        System.out.println("Преобразовали GregorianCalendar в Date: " + time);
        dateTime.setTime(time);
        System.out.println("Преобразовали Date в GregorianCalendar: " + dateTime);

        TimeZone timeZone = TimeZone.getDefault(); //TimeZone абстрактный, через new не создать. Настройки беруться из текущих настроек ОС
        System.out.println("Смещение которое д.б. добавлено к GMT для получения локального времени: " +
                timeZone.getRawOffset() / (60 * 60 * 1000));

// Сравнение дат
        System.out.println("\n --- Сравнение дат --- ");
        if (localCurrentTime.after(date))   // или before
            System.out.println(localCurrentTime.getTime() + "<" + date.getTime());
        else System.out.println(localCurrentTime.getTime() + " >= " + date.getTime());

//      проверка таймзон
        Calendar cal = new GregorianCalendar();
        System.out.println("\n-----Проверка тайм-зон------");
        System.out.println("Текущая тайм-зона: " + cal.getTimeZone().getDisplayName() +": " + cal.getTimeZone().getID());
        System.out.printf("Local time: %04d-%02d-%02d %02d:%02d:%02d\n", cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), cal.get(Calendar.SECOND));

        cal = new GregorianCalendar(TimeZone.getTimeZone("Europe/Moscow"));
        System.out.printf("Moscow time: %04d-%02d-%02d %02d:%02d:%02d\n", cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), cal.get(Calendar.SECOND));

        cal = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
        System.out.println("Текущая тайм-зона: " + cal.getTimeZone().getDisplayName());
        System.out.printf("UTC time: %04d-%02d-%02d %02d:%02d:%02d\n", cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), cal.get(Calendar.SECOND));


//      Конвертация времени между тайм-зонами
        Calendar localTime = Calendar.getInstance();
        localTime.set(Calendar.HOUR, 17);
        localTime.set(Calendar.MINUTE, 15);
        localTime.set(Calendar.SECOND, 20);
        System.out.println("\n-----Конвертация времени между тайм-зонами------");
        System.out.printf("Какое-то локальное время:  %04d-%02d-%02d %02d:%02d:%02d\n", localTime.get(Calendar.YEAR), localTime.get(Calendar.MONTH) + 1, //0..11
                localTime.get(Calendar.DAY_OF_MONTH), localTime.get(Calendar.HOUR), localTime.get(Calendar.MINUTE), localTime.get(Calendar.SECOND));
        Calendar germanyTime = new GregorianCalendar(TimeZone.getTimeZone("Germany"));
        System.out.printf("Текущее Germany time: %02d:%02d:%02d\n", germanyTime.get(Calendar.HOUR), germanyTime.get(Calendar.MINUTE), germanyTime.get(Calendar.SECOND));
        germanyTime.setTimeInMillis(localTime.getTimeInMillis());
        System.out.printf("Время относительно тайм-зоны 'Germany': %02d:%02d:%02d\n", germanyTime.get(Calendar.HOUR), germanyTime.get(Calendar.MINUTE), germanyTime.get(Calendar.SECOND));

//      Получение всех идентификаторов тайм-зон
        String[] ids = TimeZone.getAvailableIDs();
        cal = Calendar.getInstance(); // берём локальное время
        System.out.println("\n--- Ид. тайм-зоны и её смещение ---");
        for (String id : ids) {
            cal.setTimeZone(TimeZone.getTimeZone(id)); // меняем TZ, где id =, например, "Europe/Moscow"
            // выводим смещение "Europe/Moscow"
            if (id.equals("Europe/Moscow")) {
                System.out.println(padr(id, 30) + "  UTC:" + cal.get(Calendar.ZONE_OFFSET) / (60 * 60 * 1000));
            }
        }

        // возвращаем массив тайм-зон со смещением +4 относительно GMT
        ids = TimeZone.getAvailableIDs(4 * 60 * 60 * 1000);
        cal = Calendar.getInstance(); // берём локальное время
        System.out.println("\n---Все тайм-зоны со смещением +4 ---");
        for (String id : ids) {
            TimeZone tz = TimeZone.getTimeZone(id);
            System.out.println(padr(id, 15) + "  UTC:" + tz.getRawOffset() / (60 * 60 * 1000));
        }

//      Пример вывода календарика на текущий месяц
        MyCalendar.my_calendarik();
    }

    /*
    Вывод строки фиксированной длины. Остальное заполняется пробелами.
    Удобно для вывода столбцов
     */
    public static String padr(String str,int len){
        if(len - str.length() > 0){
            char[] buf = new char[len - str.length()];
            Arrays.fill(buf,' ');
            return str + new String(buf);
        }else{
            return str.substring(0,len);
        }
    }

}
