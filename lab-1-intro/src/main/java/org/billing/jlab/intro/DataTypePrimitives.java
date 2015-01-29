package org.billing.jlab.intro;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * Рассматриваются типы данных
 */
public class DataTypePrimitives {
    public static void main(String[] args) {
        byte b = 1;     // 1 байт, от -128 до 128
        short s = 1;    // 2 байта, от -32768 до 32767
        int i = 1;      // 4 байта, от -2147483648 до -2147483647
        long l = 10_000_000_000L; // 8 байт, длинные с суффиксом L

    //  0xFFFF = 0b1111_1111_1111_1111 = 65536 = 2 байта = 16 бит (UTF-16 - кодиуется 2-х байтовыми словами)
        int x16 = 0xCAFE; // 16-тиричное число
        int b2 = 0b1111_0100_0100_0000; // 2-ичное число

        float f = 3.14F; // 4 байта, 6-7 значащих десятичных цифр
        double d = 3.14; // 8 байт, 15 значащих дечятичных цифр

        /* Для финансовых рассчетов, для избежания ошибок округлание нужно использовать класс BigDecimal
        *  Над большими числами нельзя выполнять обычные мат. операции, нудно применять сообтествующие методы
        */
        BigInteger bigInteger = BigInteger.valueOf(100); // преобразование обычного числа в число с произвольной точностью
        BigDecimal bigDecimal = BigDecimal.valueOf(3.14);

        BigDecimal c = bigDecimal.add(bigDecimal); // c = bigDecimal+bigDecimal
        BigInteger a = bigInteger.multiply(BigInteger.valueOf(3).add(BigInteger.valueOf(4))); // a= bigInteger*(3+4)

        /* char - 2-х байтовый (от 0 до 65536) тип для хранения кода любого одного отдельного символа в стандарте UNICODE - унифицированный набор символов.
        Формально тип char целочисленный => можно выполнять арифметические операции.

        Каждому символу (который вообще может быть, хоть китайский иероглиф) в Unicode присвоено волшебное число (цифровое представление) - КОДОВАЯ ТОЧКА
        Все пространство кодовых точек это диапазон чисел: 0x000000-0x10FFFF, таким образом в переменной char можно хранить
        только символы ОСНОВНОЙ ЯЗЫКОВОЙ ПЛОСКОСТИ, т.е. с 0000 по FFFF. Какой-нибудь китайские иероглиф в char не сохранить,
        только в String или в двух char.

        */
        char ch = 'A';  // 2 байта, не путать с "A", в ch хранится код символа 'A' в unicode
        ch = '\u0410'; // тоже самое что и 'A', "u" - это признак того что это 16-тиричный код символа unicode
        ch = 1040; // 10-тичное представление 'A' 4*16^2+1*16
        System.out.println("ch=" + ch);


        charDemo();

        String str = "A"+ "\uD835\uDD0A" + "A𤴊Hello World"; //
        String str1 = getSpellingString(str);
        System.out.println(str);
        System.out.println(str1);
        //str = "𤴊";
        int codePointAt = str.codePointAt(1);
        System.out.println(codePointAt);
        System.out.println(Integer.toHexString(str.codePointAt(0))+ " " + Integer.toHexString(str.codePointAt(1)));

        System.out.println(String.valueOf(150794));
    }

    /**
     * Пример использования оболочки Character для char для определения типа символа.
     * Есть перегруженные методы для работы с числовым представлением символа.
     */
    public static void charDemo() {
        char a[] = {'a', 'b', '5', '?', 'A', ' '};
        for (int i = 0; i < a.length; i++) {
            if (Character.isDigit(a[i]))
                System.out.println(a[i] + " - Десятичное число.");
            if (Character.isLetter(a[i]))
                System.out.println(a[i] + " - буква.");
            if (Character.isWhitespace())
                System.out.println(a[i] + " - пробел.");
            if (Character.isUpperCase())
                System.out.println(a[i] + " - символ верхнего регистра.");
            if (Character.isLowerCase(a[i]))
                System.out.println(a[i] + " - символ нижнего региcтра.");
        }
    }

    /**
     * Демонстрация чем charAt(0) отличается от charPointAt(0) на примере перебора строки по буквам.
     */
    private static String getSpellingString(String strIn)
    {
        String str = "A" + "\uD835\uDD0A"+ "B" + "C"; // A@BC
        //str = strIn;

        // A@BC:  length- 5, codePointCount- 4.
        // В Unicode некоторые символы могут занимать 4 байта, и в кодировке UTF-16 состоят из 2-х символов (как букава "Й" состоит из 2-х символов: И, ~)
        // Для работы с ними нужно использовать методы работающие с codePoint
        System.out.print(str);
        System.out.print(" - length: "+str.length());
        System.out.print(" - codePointCount: "+str.codePointCount(0, str.length()));

        for (int j = 0; j < str.length(); j++) {
            int cp = str.codePointAt(j);
            // если кодовое зачение Unicode из дополнительного диапазона, то он представляется двумя кодовыми точками
            if (Character.isSupplementaryCodePoint(cp)) {
                /* выводим сурогантую пару, т.к. символьного представления кодовой точки из зарезервированного диапазова нет, выдаст "?" */
                str = str + "\"" + str.substring(j, j + 2) + "\" ";
                j += 1;
            } else {
                str = str + "\"" + str.substring(j, j + 1) + "\" ";
            }
        }
        return str;
    }
}
