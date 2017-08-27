package org.billing.jlab.javacore.intro;

/**
 * Описание
 *
 * @author DKachnov
 * @version 1.0
 * @date 28.10.14
 * @since 1.0
 */

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import org.slf4j.Logger;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class DateOperations {

//********************************************************************************************************
//// Перевод даты из строки без тайм-зоны в заданную тайм-зону (если tz = null => tz = tz сервера)
//// На выходе строка с тайм-зоной
////********************************************************************************************************
//  public static String getDateStringTzFromString(String inputDateSt, String userTimezone) throws Exception{
//    userTimezone = (userTimezone == null ? TestConstHttpDetails.SERVER_TIME_ZONE : userTimezone);
//    inputDateSt  = (inputDateSt == null | inputDateSt == "" ? null : getDateAsStringForTimeZone(getDateFromObject(inputDateSt, TestConf.DATE_FORMAT_WITH_MILLISECs),
//            userTimezone, userTimezone, TestConf.JODA_DATE_FORMAT_MILLISEC_TIMEZONE));
//    return inputDateSt;
//  }
//
//********************************************************************************************************
// Перевод даты из строки без тайм-зоны в заданную тайм-зону (если tz = null => tz = tz сервера)
// На выходе Date с тайм-зоной
//********************************************************************************************************
    public static Date getDateTzFromString(String inputDateSt, String userTimezone) throws Exception{
        userTimezone    = (userTimezone == null ? Constants.SERVER_TIME_ZONE : userTimezone);
        Date returnDate = (inputDateSt == null | inputDateSt == "" ? null : getDateForTimeZone(getDateFromObject(inputDateSt, Constants.DATABASE_DATE_FORMAT),
                userTimezone, userTimezone).toDate());
        return returnDate;
    }

    //********************************************************************************************************
// Конвертирует объект в дату типа GregorianCalendar
//********************************************************************************************************
    public static GregorianCalendar getGregorianCalendarDateFromObject(Object inputDate, String inputFormat) throws Exception{
        GregorianCalendar gCalendar = new GregorianCalendar();
        Date intDate = getDateFromObject(inputDate, inputFormat);
        gCalendar.setTime(intDate);
        return gCalendar;
    }

    //********************************************************************************************************
// Конвертирует объект в дату типа Date
//********************************************************************************************************
    public static Date getDateFromObject(Object inputDate, String inputFormat) throws Exception{
        Date intDate;
        if(inputDate.getClass()==Timestamp.class){
            intDate=(Timestamp)inputDate;
        }else if (inputDate.getClass()==java.sql.Date.class){
            intDate=(java.sql.Date)inputDate;
        }else if (inputDate.getClass()==java.util.Date.class){
            intDate=(java.util.Date)inputDate;
        }else{
            // строка
            String format = (inputFormat == null) ? Constants.DATE_FORMAT_WITH_TIME : inputFormat;
            SimpleDateFormat dateFormat = new SimpleDateFormat(format);
            intDate = dateFormat.parse((String)inputDate);
        }
        return intDate;
    }

    //*****************************************************************************************************
//  Конвертирует строку в дату типа DateTime
//*****************************************************************************************************
    public static DateTime getDateTimeFromString(String inputDate, String inputFormat) throws Exception{
        DateTimeFormatter format = DateTimeFormat.forPattern(inputFormat);
        DateTime dateTime = format.parseDateTime(inputDate);
        return dateTime;
    }

    //*****************************************************************************************************
//  Конвертирует строку в дату типа DateTime
//*****************************************************************************************************
    public static Date getDateFromString(String inputDate, String inputFormat) throws Exception{
        SimpleDateFormat inputDateFormat = new SimpleDateFormat(inputFormat);
        Date outputDate = inputDateFormat.parse(inputDate);
        return outputDate;
    }


    //********************************************************************************************************
// Переводит дату (String) в формат нужный формат
//********************************************************************************************************
    public static String convertDateStringToAnotherFormat(String inputDateStr, String inputFormat, String outputFormat) throws Exception{
        SimpleDateFormat inputDateFormat = new SimpleDateFormat(inputFormat);
        SimpleDateFormat outputDateFormat = new SimpleDateFormat(outputFormat);
        Date outputDate = inputDateFormat.parse(inputDateStr);
        String outputDateStr = outputDateFormat.format(outputDate);
        return outputDateStr;
    }

//********************************************************************************************************
//  Сравнение дат с заданной точностью ("H" - с точностью до часа, "D" - с точностью до дня)
//********************************************************************************************************
// Метод последовательно сравнивает год, месяц, день и час (в зависимости от выбранного режима) для двух дат.
// delay - допустимая задержка на возможный переход через час/день/месяц/год в процессе выполнения теста (если тест был запущен в пограничное время).

    public static void compareDates(GregorianCalendar targetDate, String targetDateNameForLog, String mode, GregorianCalendar checkDate, Logger LOG) throws Exception {

        final long delay = 5 * 60 * 1000; // допустимая задержка при смене часа (в миллисекундах)

        // определяем следующий год (для сравнения при переходе через год во время выполнения теста)
        GregorianCalendar nextYear = new GregorianCalendar();
        nextYear.setTimeInMillis(checkDate.getTimeInMillis());
        nextYear.add(GregorianCalendar.YEAR, 1);

        // определяем следующий месяц (для сравнения при переходе через месяц во время выполнения теста)
        GregorianCalendar nextMonth = new GregorianCalendar();
        nextMonth.setTimeInMillis(checkDate.getTimeInMillis());
        nextMonth.add(GregorianCalendar.MONTH, 1);

        // определяем следующий день (для сравнения при переходе через сутки во время выполнения теста)
        GregorianCalendar nextDay = new GregorianCalendar();
        nextDay.setTimeInMillis(checkDate.getTimeInMillis());
        nextDay.add(GregorianCalendar.DAY_OF_MONTH, 1);

        // определяем следующий час (для сравнения при переходе через час во время выполнения теста)
        GregorianCalendar nextHour = new GregorianCalendar();
        nextHour.setTimeInMillis(checkDate.getTimeInMillis());
        nextHour.add(GregorianCalendar.HOUR, 1);

        LOG.debug("*** (start) Method: compareDates");

        // тайм-зона
        assertEquals(targetDate.getTimeZone(), checkDate.getTimeZone(), "-> " + targetDateNameForLog + " TimeZone is incorrect!");

        // год                                                                                             // (пояснение на примере года)
        assertTrue(((targetDate.get(GregorianCalendar.YEAR) == checkDate.get(GregorianCalendar.YEAR)) ||   // год совпадает
                        ((targetDate.get(GregorianCalendar.YEAR) == nextYear.get(GregorianCalendar.YEAR)) &&   // или следующий год, но при этом разница
                                (targetDate.getTimeInMillis() - checkDate.getTimeInMillis() <= delay))),              // во времени меньше или равна допустимой задержке
                "-> " + targetDateNameForLog + " Year is incorrect!");
        // месяц
        assertTrue(((targetDate.get(GregorianCalendar.MONTH) == checkDate.get(GregorianCalendar.MONTH)) ||
                        ((targetDate.get(GregorianCalendar.MONTH) == nextMonth.get(GregorianCalendar.MONTH)) &&
                                (targetDate.getTimeInMillis() - checkDate.getTimeInMillis() <= delay))),
                "-> " + targetDateNameForLog + " Month is incorrect!");
        // день
        assertTrue(((targetDate.get(GregorianCalendar.DAY_OF_MONTH) == checkDate.get(GregorianCalendar.DAY_OF_MONTH)) ||
                        ((targetDate.get(GregorianCalendar.DAY_OF_MONTH) == nextDay.get(GregorianCalendar.DAY_OF_MONTH)) &&
                                (targetDate.getTimeInMillis() - checkDate.getTimeInMillis() <= delay))),
                "-> " + targetDateNameForLog + " Day of month is incorrect!");
        // AM/PM
        if (mode == "H") {assertTrue(((targetDate.get(GregorianCalendar.AM_PM) == checkDate.get(GregorianCalendar.AM_PM)) ||
                        (targetDate.getTimeInMillis() - checkDate.getTimeInMillis() <= delay)),
                "-> " + targetDateNameForLog + " AM/PM is incorrect!");
        }
        // час
        if (mode == "H") {assertTrue(((targetDate.get(GregorianCalendar.HOUR) == checkDate.get(GregorianCalendar.HOUR)) ||
                        ((targetDate.get(GregorianCalendar.HOUR) == nextHour.get(GregorianCalendar.HOUR)) &&
                                (targetDate.getTimeInMillis() - checkDate.getTimeInMillis() <= delay))),
                "-> " + targetDateNameForLog + " Hour is incorrect!");
        }

        LOG.debug("*** (end) Method: compareDates");
    }

//********************************************************************************************************
//  Проверка дат NAVI_DATE => сравнение с текущей датой в TZ UBS (передается в параметре)
//********************************************************************************************************
// Сравнение дат по сдвигу в миллисекундах с учетом тайм-зоны

    public static void checkNaviDate(GregorianCalendar naviDate, String dateNameForLog, long delay, GregorianCalendar curDate,
                                     org.slf4j.Logger LOG) throws Exception {
        LOG.debug("*** (start) Method: checkNaviDate");

        // тайм-зона
        assertEquals(naviDate.getTimeZone(), curDate.getTimeZone(), "-> " + dateNameForLog + " TimeZone is incorrect!");

        // допустимая разность дат
        assertTrue(Math.abs(curDate.getTimeInMillis() - naviDate.getTimeInMillis()) <= delay, "-> " + dateNameForLog + " is incorrect! (" + naviDate.getTime().toString() + ")");

        LOG.debug("*** (end) Method: checkNaviDate");
    }

    //***********************************************************************************************************
// Перевод переданной даты в указанную тайм-зону.
// Тайм зона должна быть определена в бибилиотеке org.joda.time.DateTimeZone.
// Список можно посмотреть http://joda-time.sourceforge.net/timezones.html
//***********************************************************************************************************
    public static DateTime getDateForTimeZone(Date inputDate, String oldTimeZone, String newTimeZone){
        DateTime convertDate=null;
        String format=Constants.DATE_FORMAT_WITH_MILLISECs;
        DateTimeFormatter dateFormat = DateTimeFormat.forPattern(format);
        SimpleDateFormat dateDBFormat = new SimpleDateFormat(format);
        // DateTime origDate = new DateTime(inputDate.getTime());
        DateTime origDate= DateTime.parse(dateDBFormat.format(inputDate), dateFormat.withZone(DateTimeZone.forID(oldTimeZone)));
        convertDate = origDate.withZone(DateTimeZone.forID(newTimeZone));
        return convertDate;
    }

    //****************************************************************************************************************
// Конвертация переданной даты (DateTime) в строку
//****************************************************************************************************************
    public static String convertDateTimeToString(DateTime inputDate, String format){
        DateTimeFormatter fmt = DateTimeFormat.forPattern(format);
        return inputDate.toString(fmt);
    }

    //*****************************************************************************************************************
// Перевод переданной даты в указанную тайм-зону.
// Тайм зона должна быть определена в бибилиотеке org.joda.time.DateTimeZone.
// Список можно посмотреть http://joda-time.sourceforge.net/timezones.html
// Сконвертированная дата возвращается в строке в указанном формате
//*****************************************************************************************************************
    public static String getDateAsStringForTimeZone(Date inputDate, String oldTimeZone, String newTimeZone, String outputFormat){
        DateTime convertDate=getDateForTimeZone(inputDate, oldTimeZone, newTimeZone);
        return convertDateTimeToString(convertDate, outputFormat);
    }
}
