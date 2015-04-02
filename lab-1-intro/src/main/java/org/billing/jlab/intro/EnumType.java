package org.billing.jlab.intro;

/**
 * Рассматривается использование перечислений. На самом деле перечисления это класс.
 * где вместо ключевого слова class используется ключевое слово enum
 *
 * http://www.quizful.net/post/java_enums
 */
public class EnumType {

    public static void main(String[] args) {



        Size s = Size.MEDIUM;
        System.out.println(s);

        String str = Size.MEDIUM.toString(); // возвращается символьная строка "MEDIUM"
        Size small = (Size) Enum.valueOf(Size.class, "SMALL"); // переменной будет присвоено Size.MEDIUM
        Size[] values = Size.values(); // массив вcех перечисляемых типов
        int ordinal = Size.MEDIUM.ordinal(); // = 1, возвращает позицию перечисляемой константы

        Size1 s1 = Size1.LARGE;
        System.out.println(s1.getAbbreviation());
    }
}

/**
 * Простое перечисление, не требующее конструктора. Это класс, на подобие class Size, только вместо class используется
 * ключевое слово enum
 */
enum Size {SMALL, MEDIUM, LARGE, EXTRA_LARGE}


/**
 Объявляем перечисляемый тип Size. На самом деле это класс.
 Допускаются существование, в данном случае, только четырёх экзампляров класса
 В данном классе требуется конструктор, который вызывается автоматически, когда вызывается , например, Size1.LARGE

 */
enum Size1 {
    SMALL("S"), MEDIUM("M"), LARGE("L"), EXTRA_LARGE("XL");

    private String abbreviation;
    private Size1(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getAbbreviation() {
        return abbreviation;
    }
}


