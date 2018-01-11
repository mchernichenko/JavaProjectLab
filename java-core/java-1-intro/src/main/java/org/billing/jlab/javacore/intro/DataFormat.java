package org.billing.jlab.javacore.intro;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
//import org.apache.log4j.Layout;

/**
    Рассматривается форматирование данных String.format, используется в System.out.printf.<br/>
    Формат ИНСТРУКЦИИ: %[НомерАргумента][флаги][ширина][.разрядность] ТИП  , где [] - необязательный аргумент инструкции, т.е. ТИП обязателен, всё остальное опционально!<br/>
    Знак % говорит: Вставьте сюда аргумент и отформатируйте по инструкции.
    После % нельзя поместить что угодно, синтаксис подчинён вышеобозначенным правилам.<br/>
    <p>Инструкция - это всё что межну % и индикатором типа (d,s,c,tx и пр.), который обязателен
    Номер аргумента имеет смысл использовать когда нужно несколько раз отформатировать один и тотжа аргумент, например дату.
    </p>
    <img src="../../../../resources/Format_printf.png" alt="Символы преобразования, флаги" />
 */
public class DataFormat {
    public static void main(String[] args) {
        Calendar date = new GregorianCalendar();
        Date currentTime = new Date();
        double d = 3.114;
        String str = "Строка";

        System.out.printf("Какой-то текст до символа 'процента': %1s", str);
        System.out.printf("\n  %,(8.2f после символа типа можно писать текст", d); // ,( - это флаги, 8- ширина, .2 число зн. после запятой, f - тип
        System.out.printf("\n%1$s %2$tB %2$te, %2$tY", "Дата: ", new Date()); // пример использования НомерАргумента, в этом случае тип указывается после $

        System.out.printf("\nСимвол: %c", 42); // символ *
        System.out.printf("\nHEX: %x", 42); // Шестнадцатеричное число


        // Пример составления форматированной строки
        String frmString = String.format("Пример составления отформатированной строки без последующего вывода: %2$s  %1$f", d, str);
        System.out.println("\n"+frmString);

    }
}
