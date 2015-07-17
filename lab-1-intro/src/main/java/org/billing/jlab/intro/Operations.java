package org.billing.jlab.intro;

/**
 * Рассматривается:<br/>
 * - объявление и инициализация и область видимости переменных<br/>
 * - константы<br/>
 * - инкремент i++<br/>
 * - тернарная операция: условие ? выражение_1 : выражение_2<br/>
 * - логические булевы операции: ! && ||<br/>
 * - поразрядные операции: & | ^ ~ >> << >>>    (см. <a href="http://habrahabr.ru/post/187606/">ссылка на habrahabr</a>)<br/>
 * - практика использования битовых операций: битовая маска, права доступа, быстрое умножение/деление
 * - приведение типов
 */
public class Operations {
    public static final String STROKA = "Глобальная костанта класса"; // можно использовать в любых методах класса. Public позволяет использовать её везде.

    public static void main(String[] args) {
        /* объявление переменных, инкремент, ?: , булевы операции && ||*/
        declareVar();

        /* Побитовые операции  */
        bitwiseOperations();

        /*  Приведение типов */
        castingType();
    }

/** Объявление переменных.
    Должны начинаться с букв, а далее буквы и цифры. Регистр учитывается. Длина не ограничиваеся. <br/>
    Нельзя использовать в имени переменной символы '+', пробел и пр. т.к. это не буквы!, b зарезервированные слова.
    Что Java считает буквой можено определить из метода Character.isJavaIdentifierPart('+');
    Не рекомендуется использовать символ $, хотя он считается допустимым идентификатором.
    Принять объявлять локальные переменны рядом с местом их использования.
    <p>
    Перед использованием локальных переменых всегда требуется её инициализировать!!!
    Область видимости локальной переменной это блок {}. Во вложенных блоках использовать переменные с одинаковым именем нельзя.
    Хотя если блоки не вложенные, то можно.
    </p>
*/
    public static void declareVar() {
        int i, j; // так можно, но не рекомендуется
        i = j = 0;  // многократное присваивание возможно, выполняется справа на лево
        j = 17; i = 6;   // перед использованием всегда требуется инициализировать!!!
        String str = "";
        char yesChar = 'Y';
        final int CONST = 3; // локальная константа метода, требуется инициализировать.

//      Операции
        System.out.println("17/6 = 2 т.е. дробная часть обрезается, округления нет!: " + j/i); // (2,8)
        System.out.println("17%6 = 5 - остаток от деления целых чисел: " + j % i);
        if (Double.isNaN(Double.NaN)) {
            System.out.println("Делить на 0 целые числа нельзя, а с плавающей точкой можно. Результат Double.NaN: " + 15.0/0);
        }

//      инкремент
        i = 1;
        j = 2 * ++i;
        System.out.println("\nИнкремент ++i сначала увеличивает затем применяется новое значение i: " + j);
        i = 1;
        j = 2 * i++;
        System.out.println("Инкремент i++ сначала вычисляется выражение, затем увеличивается i: " + j);

//      && и || задают порядок вычисления по сокращённой схеме: если первый операнд определяет значение всего выражения, то остальные не выличляются
        // c &, | это не работает, поэтому пользуются в порязрядных операциях !!!
        if (i != 0 && 1/i >= 0) {
            System.out.println("\nвыражежие_1 && выражение_2: если выражежие_1 ложно, то выражение_2 не вычисляется");
            System.out.println("Таким образом деления на 0 не производится!!!");
        }

        i = 0;
        if (i + 1 > 0 || 1 / i > 2) {
            System.out.println("\nвыражежие_1 || выражение_2: если выражежие_1 истинно, то выражение_2 не вычисляется");
            System.out.println("Таким образом деления на 0 не производится!!!");
        }

//      Тернарная опрерация: условие ? выражение_1 : выражение_2
        i = 1;
        j = 2;
        j = i < j ? i : j;
        System.out.println("\nусловие ? выражение_1 : выражение_2: если условие истинно, то выполняется выражение_1, иначе выражение_2: " + j);

    }

    /**
       Поразразные операции: &, |, ^, ~, >>, <<, >>> - используются для побитовых операций  (см. http://habrahabr.ru/post/187606/)<br/>
           00000000 00000000 00000000 01111011 (123) <br/>
           00000000 00000000 00000001 11001000 (456) <br/>
или  | =   00000000 00000000 00000001 11111011 (507) <br/>
  и  & =   00000000 00000000 00000000 01001000 (72)   - используется в битовой маске. Маска позволяет получать значения только определенных битов в последовательности.<br/>
                                                        Например, у нас есть маска 00100100, она позволяет нам получать из последовательности только те биты, которые в ней установлены.<br/>
 xOR ^ =   00000000 00000000 00000001 10110011 (435) Выставляет значение в 1, если установлен соответствующий бит или в первой или во второй во второй последовательности, но не одновременно.<br/>
не ~123 =  11111111 11111111 11111111 10000100 (-124) -  Превращает каждый бит в противоположный.<br/>
 123<<1 = 00000000 00000000 00000000 11110110 (246)  - Все биты смещаются влево на 1 бит, число справа дополняется нулем. Если нужно сдвинуть на 2 бита, то 123<<2. число<<32 = исходному числу!!<br/>
 123>>1 = 00000000 00000000 00000000 00111101 (246)  - Все биты смещаются вправо на 1 бит. Число слева дополняется нулем, если число положительное и единицей, если отрицательное.<br/>
 123>>>1=  Беззнаковый оператор сдвига >>>           - Все биты смещаются вправо, число слева дополняется нулем, даже если операция выполняется с отрицательными числами.<br/>
          , а операции <<< в Java нет
     */
    public static void bitwiseOperations() {

        System.out.println("Побитовая операция 123&456: " + (123&456));
        System.out.println("Побитовая операция 123|456: " + (123|456));
        System.out.println("Побитовая операция 122^456: " + (123^456));
        System.out.println("Побитовая операция ~123: " + (~123));
        System.out.println("Побитовая операция 123 << 1, для быстрого *2 целого числа: " + (123 << 1));
        System.out.println("Побитовая операция 123 >> 1, для быстрого /2 целого числа: " + (123 >> 1));
        System.out.println("Побитовая операция 123 >>> 1: " + (123 >>> 1));

/*      Практика использования битовых операций:
        - Обмен двух переменных без создания временной переменной
        - Битовая маска
        - Работа с правами доступа
        - Быстрое умножение и деление */
        xorSwap(5, 2);
    }

    /** Приведение типов. <img src="../../../../resources/ДопустимыеПреобразованияЧисловыхТипов.png" alt="Схемка из Хорстманна" />
        Перед выполнением бинарной операции оба операнда преобразуются в числовое значение одинакового типа:<br/>
            - если один double, то второй приводится к double<br/>
            - иначе, если один float, то второй приводится к float<br/>
            - иначе, если один long, то второй приводится к long<br/>
            - иначе оба приводятся к int<br/>
        Но в ряде случаев требуется double рассматривать как целое. В этом случае требуется явное приведение типов. <br/>
        Приведение булевых значений к целым и наоборот невозможно. Только вручную с помощью условного выражения ?:
     */
    public static void castingType() {
        double x = 9.97;
        int nx = (int) x; // nx = 9 т.к. дробная часть отсекается
        nx = (int) Math.round(x); // nx = 10, приведение к int требуется т.к. round() возвращает long и оно может быть приведено к ште только с явным приведением, иначе существует вероятность потери данных.

        boolean bl = true;
        nx = bl ? 1 : 0; // приведение булевого значение к int
    }

    /**
     * Исключающее ИЛИ может быть использовано для обмена двух переменных без создания временной переменной.
     * Правда, в реальной жизни способ с временной переменной работает быстрее.
     */
    public static void xorSwap(int x, int y) {
        System.out.println("Обмен двух переменных без создания временной переменной:");
        System.out.printf("x = %d , y = %d", x, y);
        x = x ^ y;
        y = y ^ x;
        x = x ^ y;
        System.out.printf("\nx = %d , y = %d", x, y);
    }
}
