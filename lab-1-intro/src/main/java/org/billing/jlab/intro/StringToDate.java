package org.billing.jlab.intro;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

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
public class StringToDate {

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

}
