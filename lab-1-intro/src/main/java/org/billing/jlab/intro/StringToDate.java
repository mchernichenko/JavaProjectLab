package org.billing.jlab.intro;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * http://www.mkyong.com/java/how-to-convert-string-to-date-java/
 * http://docs.oracle.com/javase/7/docs/api/java/text/SimpleDateFormat.html
 *
 * Преобразование текстовой даты (например, Timestamp) в объект Date и далее в Calendar
 * и наоборот преобразование даты в String нужного формата.
 *
 * Примеры форматов дат в виде строки:
 * "dd/MM/yyyy" - 07/06/2013
 * "MMM dd, yyyy" - Jun 07, 2013
 * "E, MMM dd yyyy" - Fri, Jun 07 2013
 * "EEEE, MMM dd, yyyy HH:mm:ss a" - Friday, Jun 07, 2013 12:10:56 PM
 * "yyyy-MM-dd'T'HH:mm:ss.SSSXXX" - 2001-07-04T12:08:56.235-07:00   -- Timestamp
 */
public class StringToDate {

    public static Date getDate(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        String dateInString = "7-Jun-2013"; // = strDate

        try {

            Date date = formatter.parse(dateInString);
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
}
