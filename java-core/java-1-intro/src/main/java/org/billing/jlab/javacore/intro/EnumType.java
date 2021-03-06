package org.billing.jlab.javacore.intro;

/**
 * Рассматривается использование перечислений. На самом деле перечисления это класс.
 * где вместо ключевого слова class используется ключевое слово enum => как и класс может использоваться только с модификаторами public или default (если определяется не внутри класса)
 *
 * Наследуется от  java.lang.Enum, который имеет ряд полезных методов для работы с перечислениями.
 *
 * Основное предназначение: ограничить множество допустимых значений для некоторого типа данных
 * Например: 7 дней недели, 12 месяцев в году и пр.
 * Польза от этого следующая:
 * 1. при разработке нужно быть всегда лояльным к тому, что приходит на вход и строг к тому что отдаём.
 *    Если на вход передаётся какой-то трэш мы должны его всегда корректно обработать!
 *    Так вот можно сразу задать ограничения в входе и при надобности их расширять и не заниматься анализом входных значений внутри метода.
 *    не требуется документировать описание входных данных и изменять его если множество расширяется
 *    Если входная переменная ограничена каким-то множеством значений, при чем расширяемым , то это множество можно посмотреть в описании этого типа
 *    Enum позволяет контролировать значения, которые приходят на вход и это круто!
 * 2. Дополнительные плюшки: Enum в отличие от обычного класса можно
 *    - использовать в конструкции switch
 *    - есть встроенный метод конвертации string в экземпляр класса и обратно
 *    - доступен массив всех доступных значений перечисления
 *    - можно переопределять конструктор, что дает возможность расширять множеста без изменения кода, расмотрено ниже
 *
 * http://www.quizful.net/post/java_enums
 */
public class EnumType {

    public static final int STYLE_DOLLAR = 0;

    public static void main(String[] args) {

        String str = EStyle.MEDIUM.toString(); // конвертация класса в строку, в данном случае возвращается символьная строка "MEDIUM"

        EStyle small = (EStyle) Enum.valueOf(EStyle.class, "SMALL"); // конвертация из String и инстанс класса. Переменной будет присвоено Size.MEDIUM

        EStyle[] values = EStyle.values(); // доступен массив вcех доступных экзампляров инстанса
        for (EStyle eStyle : values) {
            System.out.println("Поддерживаемые стили: " + eStyle.toString());
        }

        int ordinal = EStyle.MEDIUM.ordinal(); // = 1, возвращает позицию перечисляемой константы

        EStyle_1 s1 = EStyle_1.DOLLAR_STYLE;
        System.out.println(s1.getAbbreviation());

        showText("TEXT", Style.A_STYLE, EStyle.DOLLAR_STYLE, EStyle_1.DOLLAR_STYLE);
        showText("TEXT", Style.A_STYLE, EStyle.valueOf("DOLLAR_STYLE"), EStyle_1.valueOf("DOLLAR_STYLE"));
    }

    // метод обрамления текста в зависимости от переданного стиля
    public static void showText(String text, Style style, EStyle eStyle, EStyle_1 eStyle_1) {

        // вариант использования без enum, т.е. существовавший до enum. Имеет минусы, см. класс Style
        if (style == Style.DOLLAR_STYLE)
            System.out.println("Вариант_1:" + "$" + text + "$");
        else if (style == Style.A_STYLE)
            System.out.println("Вариант_1:" +"@" + text + "@");

        // вариант использования с enum, но этот код не так хорош, т.к. при добавлении нового стиля нужно добавлять его обработку и здесь
        switch (eStyle) {  // enum можно использовать в switch
            case DOLLAR_STYLE:
                System.out.println("Вариант_2:" +"$" + text + "$");
                break;
            case A_STYLE:
            default:
                System.out.println("Вариант_2:" + "@" + text + "@");
        }

        // здесь полная нирвана, т.к. этот код не нуждается в изменении и работает при добавлении нового стиля.
        // при создании инстансов класса eStyle_1 сразу, в конструкторе передается и значение, которым нужно обрамлять, которое достается геттером
        System.out.println("Вариант_3:" + eStyle_1.getAbbreviation() + text + eStyle_1.getAbbreviation());

    }
}

/**
 * Способ создания ограниченного количества экземпляров класса до появления enum
 * Данный класс имеет всего 2 экземпляра
 * Минусы этого рашения:
 *   1. нельзя использовать инстанс этого класса внутри switch, вынуждены делать if
 *   2. сконвертировать string в инстанс этого класса не получится, хотя есть способ (см. fromName), но он ужасен.
 *   3. нет доступа ко всем инстансам, которые определяются в этом классе
 */
final class Style {

    public static Style DOLLAR_STYLE = new Style();

    public static Style A_STYLE = new Style();

    private Style() {}  //нельзя создавать экземпляр класса вне этого класса

    // проблема этого кода в том, что при добавлении нового инстанса нужно менять метод, а это потенциальные баги
    public static Style fromName(String name) {
        if ("DOLLAR_STYLE".equals(name))
            return  DOLLAR_STYLE;
        if ("A_STYLE".equals(name))
            return A_STYLE;
        return null;
    }
}

/**
 * Простое перечисление, не требующее конструктора. Это класс, на подобие class Size, только вместо class используется
 * ключевое слово enum
 * Нельзя создавать инстансы этого класса вне этого класса, поэтому все инстансы нужно перечислить вначале описания класса.
 * Все эти инстансы не изменяемые , т.е. public final static
 */
enum EStyle {
    SMALL,  //  это по сути создание класса, где вызывается конструктор без аргументов SMALL = new EStyle()
    MEDIUM,
    A_STYLE,
    DOLLAR_STYLE
}

/**
 Объявляем перечисляемый тип EStyle. На самом деле это класс.
 Допускаются существование, в данном случае, только четырёх экзампляров класса
 В данном классе требуется конструктор, который вызывается автоматически, когда вызывается , например, EStyle_1.LARGE
 конструкторы в enum не могут быть public, иначе можно было создавать другие инстансы классов.
 */
enum EStyle_1 {
    SMALL("S"),
    DOLLAR_STYLE("$"),
    A_STYLE("@"),
    EMPTY_STYLE("");  // этот инстанс будет создан с использованием пустого конструктора

    private final String abbreviation;

    private EStyle_1(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getAbbreviation() {
        return abbreviation;
    }
}


