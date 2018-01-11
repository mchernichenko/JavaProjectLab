package org.billing.jlab.javacore.intro;

import java.sql.Timestamp;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 <p>
 Рассматривается работа с датами :<br/>
  - представление даты: класс Calendar/Date<br/>
  - изменение/смещение даты: изменение состояния объекта GregorianCalendar<br/>
  - Конвертация  GregorianCalendar <-> Date<br/>
  - сравнение дат<br/>
  - тайм-заны: проверка TZ, конвертация между TZ<br/>
  - Конвертация и форматирование даты Date в String (см применение SimpleDateFormat в StringToDate ).<br/>
 </p>
  В Java время и его представление разнесены по разным классам.
  Класс Date() отражает только конкретный момент времени sysdate, но для работы с датами он не используется!
  Необходимо использовать абстрактный класс Calendar, описывающий свойства календаря в целом.
 <br/>
  <a href="http://jexp.ru/index.php/Java_Tutorial/Development/TimeZone"> Тайм зоны</a>
 */
public class DateOperationsJava7 {
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
        GregorianCalendar date = new GregorianCalendar(2015, Calendar.JANUARY, 1); // произвольная дата 01.01.2015
        GregorianCalendar calendar = new GregorianCalendar(2015, Calendar.JANUARY, 1, 23, 59, 59); // произвольная дата и время, 01.01.2015 23:59:59

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
        Date time = date.getTime(); // GregorianCalendar -> Date
        System.out.println("Преобразовали GregorianCalendar в Date: " + time);
        calendar.setTime(time); // Date -> GregorianCalendar
        System.out.println("Преобразовали Date в GregorianCalendar: " + convertCalendarToString(calendar));

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
        ids = TimeZone.getAvailableIDs(6 * 60 * 60 * 1000);
        cal = Calendar.getInstance(); // берём локальное время
        System.out.println("\n---Все тайм-зоны со смещением +10 ---");
        for (String id : ids) {
            TimeZone tz = TimeZone.getTimeZone(id);
            System.out.println(padr(id, 25) + "  UTC: " + tz.getRawOffset() / (60 * 60 * 1000));
        }

//      Пример вывода календарика на текущий месяц
        my_calendarik();
    }

    /*
    UTIL:
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

    /**
     * Преобразование текстовой даты (например, Timestamp) в объект Date и далее в Calendar
     * и наоборот преобразование даты в String нужного формата
     *<p>
     * Для форматирования даты и времени используется DateFormat и SimpledateFormat (подробнее см. Хорстмана стр.934)<br/>
     *
     * <a href="http://www.mkyong.com/java/how-to-convert-string-to-date-java/"> Конвертация строки в дату</a><br/>
     * <a href="http://docs.oracle.com/javase/7/docs/api/java/text/SimpleDateFormat.html">SimpleDateFormat</a>
     *</p>
     * Примеры форматов дат в виде строки:<br/>
     * <code>"dd/MM/yyyy" - 07/06/2013</code><br/>
     * <code>"MMM dd, yyyy" - Jun 07, 2013</code><br/>
     * <code>"E, MMM dd yyyy" - Fri, Jun 07 2013</code><br/>
     * <code>"EEEE, MMM dd, yyyy HH:mm:ss a" - Friday, Jun 07, 2013 12:10:56 PM</code><br/>
     * <code>"yyyy-MM-dd'T'HH:mm:ss.SSSXXX" - 2001-07-04T12:08:56.235-07:00   -- Timestamp</code><br/>
     */
    public static Date convertStringToDate(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        String strDate_1 = "7-Jun-2013"; // = strDate

        try {

            Date date = formatter.parse(strDate_1); // String -> Date
            System.out.println(date);                   // Fri Jun 07 00:00:00 MYT 2013
            System.out.println(formatter.format(date)); // 07-Jun-2013

            Timestamp timestamp = new Timestamp(date.getTime());
            System.out.println(timestamp.getTime()); // long - число миллисекунд с 1.01.1970

            return date;

        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * Пример форматирования даты в String c помощью SimpleDateFormat
     * <blockquote><pre>
     *      SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
     *      String str = formatter.format(calendar.getTime())
     * </pre></blockquote>
     */
    public static String convertCalendarToString(Calendar calendar) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");  // Формат для TimeStamp

        // задание предопределённого формата, относительно региона. Например: July 15, 2015
        // DateFormat formatter = DateFormat.getDateInstance(DateFormat.LONG, Locale.getDefault());

        return formatter.format(calendar.getTime());
    }

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
