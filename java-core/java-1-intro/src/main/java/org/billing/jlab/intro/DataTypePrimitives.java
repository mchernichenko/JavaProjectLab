package org.billing.jlab.intro;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;

/**
 * Рассматриваются типы данных: byte, short, int, long, float, double, boolean, char
 * Объектные оболочки и автоупаковка: Integer, Long, Float, Double, Short, Byte - общий суперкласс Number;
 *                                    Character, Void, Boolean.
 *   Классы объектных оболочек не изменяемы.
 *   Широко применяются при преобразовании String -> DataType
 *   <img src="../../../../resources/IntegerEnvelope.png" alt="API работы с объектными оболочками" />
 */
public class DataTypePrimitives {
    public static void main(String[] args) {
        byte b = 1;     // 1 байт, от -128 до 128
        short s = 1;    // 2 байта, от -32768 до 32767
        int i = 1;      // 4 байта, от -2147483648 до -2147483647
  //      long l = 10_000_000_000L; // 8 байт, длинные с суффиксом L

    //  0xFFFF = 0b1111_1111_1111_1111 = 65536 = 2 байта = 16 бит (UTF-16 - кодиуется 2-х байтовыми словами)
        int x16 = 0xCAFE; // 16-тиричное число
     //   int b2 = 0b1111_0100_0100_0000; // 2-ичное число

        float f = 3.14F; // 4 байта, 6-7 значащих десятичных цифр
        double d = 3.14; // 8 байт, 15 значащих дечятичных цифр

        boolean bl = false; // true/false - преобразовать в целочисленног и наоборот нельзя!

        /* Для финансовых рассчетов, для избежания ошибок округлание нужно использовать класс BigDecimal
        *  Над большими числами нельзя выполнять обычные мат. операции, нужно применять соответсвующие методы
        */
        BigInteger bigInteger = BigInteger.valueOf(100); // преобразование обычного числа в число с произвольной точностью
        BigDecimal bigDecimal = BigDecimal.valueOf(3.14);

        BigDecimal c = bigDecimal.add(bigDecimal); // c = bigDecimal+bigDecimal
        BigInteger a = bigInteger.multiply(BigInteger.valueOf(3).add(BigInteger.valueOf(4))); // a= bigInteger*(3+4)

        /* char - 2-х байтовый (от 0 до 65536) тип для хранения кода любого одного отдельного символа в стандарте UNICODE - унифицированный набор символов.
        Формально тип char целочисленный => можно выполнять арифметические операции.

        КОДОВАЯ ТОЧКА - Каждому символу (который вообще может быть, хоть китайский иероглиф) в Unicode присвоено волшебное число (цифровое представление)
        Все пространство кодовых точек это диапазон чисел: 0x000000-0x10FFFF, таким образом в переменной char можно хранить
        только символы ОСНОВНОЙ ЯЗЫКОВОЙ ПЛОСКОСТИ, т.е. с 0000 по FFFF. Какой-нибудь китайские иероглиф в char не сохранить,
        только в String или в двух char или двух КОДОВЫХ ЕДИНИЦАХ
        Здесь толковая статья про кодировки и кодовые точки: http://strongexperts.narod.ru/ru/articles/archive/java2/2006/nov2006-001/nov2006-001.htm
        */

        char ch = 'A';  // 2 байта, не путать с "A", в ch хранится код символа 'A' в unicode
        ch = '\u0410'; // тоже самое что и 'A', префикс "u" - предваряет кодовую единицу (16-тиричный код символа unicode)
        ch = 1040; // 10-тичное представление 'A' 4*16^2+1*16
        System.out.println("ch=" + ch);

//      класс Character - оболочка для удобной работы с символами.
//      Как правило используются только его статические методы. Пример определения категории символа
        Character chCh = 'A'; // автоматичиская упаковка в Character
        getCategoryChar();


//      Управляющие последовательности
//      Преназначены для удобства, чтобы не помнить значения в unicode. Могут использоваться в символьных константах и строках
//      Например, символа перехода на новую строку на клавиатуре нет, но как символ в unicode он есть и можно указать \n, вместо \u000a
        System.out.println("Пример \"использоваиния\" управляющих последовательностей\n");

        // конвертация char <-> int в зависимости от основания числа (м.б. 2,8,10,16- ричное число)
        System.out.println("Представление числа 14 в виде символа в 10-ной системе: " + Character.forDigit(14, 10));
        System.out.println("Представление числа 14  в виде символа в 16-ной системе: " + Character.forDigit(14, 16));
        int xx = Character.digit('A', 16);  // будет 10, char -> int, где 'A' символ в виде цифры, 16 - основание

// пример записи кодовой точки 0x1D546 (мат. символ обозначающий множество октионов) в виде двух кодовых единиц
        String str = "\uD835\uDD46";
        int cp = str.codePointAt(0); // чтение первой буквы, а не символа!!! 1-й символ это первая кодовая единица - D832
        System.out.println("\\uD835\\uDD46 в unicode: " + Integer.toHexString(cp)); // в unicode это код 0x1D546
        char[] chars = Character.toChars(cp); // преобразуем кодовую точку обратно в массив кодовых единиц
        for (char ch1 : chars) {
            System.out.println("Кодовые единицы 0x1D546: \\u" + (Integer.toHexString(ch1)));
        }

//      Пример использование 32-х битных кодовых точек, на примере подсчета символов в строке.
        String str1 = getSpellingString(str);
        System.out.println("Разбили слово на буковки: " + str1);

        //str = "𤴊";
        System.out.println("Конец");
    }


    /**
     * Пример использования оболочки Character для char для определения категории символа
     * (например, является ли символ числом или буквой и в каком регистре и т.д.).
     * Есть перегруженные методы для работы с числовым представлением символа. (см. Шилдт стр. 432)
     */
    public static void getCategoryChar() {
        char a[] = {'a', 'b', '5', '?', 'A', ' '};
        for (int i = 0; i < a.length; i++) {
            if (Character.isDigit(a[i]))
                System.out.println(a[i] + " - Десятичное число.");
            if (Character.isLetter(a[i]))
                System.out.println(a[i] + " - буква.");
            if (Character.isWhitespace(a[i]))
                System.out.println(a[i] + " - пробел.");
            if (Character.isUpperCase(a[i]))
                System.out.println(a[i] + " - символ верхнего регистра.");
            if (Character.isLowerCase(a[i]))
                System.out.println(a[i] + " - символ нижнего региcтра.");
        }
    }

    /**
     * Демонстрация чем charAt(0) отличается от charPointAt(0) на примере перебора строки по буквам.<br/>
     charAt(0) - первый символ строки, <br/> charPointAt(0) - первая буква (кодовая точка) указанной строки.
     <p/>
     <a href="http://www.it-rem.ru/char-with-codepoint.html">Пример взят отсюда</a> <br />
     <p/>
     */
    public static String getSpellingString(String strIn)
    {
        String str = "A" + "\uD835\uDD0A"+ "B" + "C"; // A@BC
        String strSpell = "";
        //str = strIn;

        // A@BC:  length- 5, codePointCount- 4.
        // В Unicode некоторые символы могут занимать 4 байта, и в кодировке UTF-16 состоят из 2-х символов (как букава "Й" состоит из 2-х символов: И, ~)
        // Для программа должна работать с кодировкий UTF-16 нужно использовать методы работающие с codePoint !!!!
        // иначе, можно использовать обычные методы для работы с char
        System.out.print(str);
        System.out.print(" - length: "+str.length());
        System.out.println(" - codePointCount: "+str.codePointCount(0, str.length()));

        for (int j = 0; j < str.length(); j++) {
            int cp = str.codePointAt(j);
            // если кодовое зачение Unicode из дополнительного диапазона, то он представляется двумя кодовыми точками
            if (Character.isSupplementaryCodePoint(cp)) {
                /* выводим сурогантую пару, т.к. символьного представления кодовой точки из зарезервированного диапазова нет, выдаст "?" */
                strSpell = strSpell + "\"" + str.substring(j, j + 2) + "\" ";
                j += 1;
            } else {
                strSpell = strSpell + "\"" + str.substring(j, j + 1) + "\" ";
            }
        }
        return strSpell;
    }

    /**
     * Объектные оболочки:
     * - автоупаковка/ автораспаковка. Отвечает компилятор, а не JVM
     * - сравнение
     * - преобразование из строки в число
     */
    public static void dataTypeEnvelope() {
        // списочный массив может содержать только объекты, поэтому объекты простых типов упаковываются в объект
        ArrayList<Integer> list = new ArrayList<Integer>();

        list.add(3); // здесь происходит автоматическая автоупаковка
        list.add(new Integer(3)); // можно и так

        int i = list.get(1); // автоматическая распаковка
        i = list.get(2).intValue(); // это тоже самое что и строчка выше

        Integer n = 3;
        n++;  // здесь происходит автоматическая распаковка, увеличение на единицу и автоматическая запаковка.

        // при сравнении объектов оболочек нужно использовать equals()
        Integer n1 = 3;
        if (n.equals(n1)) {
            System.out.println("3=3");
        }

        // преобазование из строки в число
        String str = "3";
        n = Integer.parseInt(str);
    }
}
