package org.billing.jlab.intro;

import java.util.Scanner;

/**
 * Рассматриваются элементы управляющей логики:
 * <p>
 * - условные операторы (if)<br/>
 * - неопеделённые циклы (while, do-while)<br/>
 * - определённые циклы - for<br/>
 * - многовариантный выбор - switch<br/>
 * - операторы прерывания break/continue логики управления - использовать не желательно! плохой стиль<br/>
 *   при break выходим их текущего цикла, continue - уходим на начало текущего вложенного цикла
 *   можно использовать метку: в начале условия для указания из какого цикла выходить, если используется много вложенных условий
 *   </p>
 */
public class ControlLogic {
    enum Size {SMALL, LARGE}

    public static void main(String[] args) {
        ifControl();
        whileControl();
        forControl();
        switchControl();
    }

    /**
     *  Оператор switch для многовариантного выбора. Заменяет if/else.
     *  Чтобы компилятор предупреждал о забытом break нужно компилировать с ключом
     *  javac -Xlint:fallthrough Lab.java
     *  А если требуется выполнение кода по нескольким ветвям, то помечаем метод аннотацией
     *  @SuppressWarnings("fallthrough")
     */
    public static void switchControl() {

        Scanner in = new Scanner(System.in);
        System.out.println("");
        System.out.println("Выбери 1,2,3,4,5");
        int choice = in.nextInt();
        switch (choice) {
            case 1: // в качестве метки м.б. char, byte, short, int, String, Enum и соответствующие оболочки
                System.out.println("Выбрали: " + choice);
                break;  // если не ввести break, то код будет выполняться case по нескольким ветвям.
            case 2:
                System.out.println("Выбрали: " + choice);
                break;
            case 3:
                System.out.println("Выбрали: " + choice);
                break;
            case 4:
                System.out.println("Выбрали: " + choice);
                break;
            case 5:
                System.out.println("Выбрали: " + choice);
                break;
            default:    // метка dafoult не обязательна
                System.out.println("Выбрали не из списка: " + choice);
                break;
        }

        // пример с перечисляемыми константами
        Size sz = Size.SMALL;
        switch (sz) {
            case SMALL:                         // здесь н нужно указывать sz.SMALL, т.к. имя перечисления уже есть в условии
                System.out.println(sz.SMALL);
                break;
            case LARGE:
                System.out.println(sz.LARGE);
                break;
        }
    }

    /**
     * Определённые циклы.
     */
    public static void forControl() {
        for (int i = 0; i < 10; i++) { // область действия переменнаой i только до конца тела цикла, иначе её нужно объявить выше
            System.out.println(i);
        }

        metka:  // можно использовать метку в начале условия, для указания оператору break из какого цикла выходить. Имеет смысл при многовложености.
        for (double i = 0; i != 10; i += 0.1) { // переменную i, т.е. с темже именем можно переопеделить.
            // Этот цикл может вообще не завершиться, т.к. i - double! и из-за ошибок округления результат ниикогда не будет достигнут
            break metka; // оператор прерывания логики управления программой
        }
    }

    /**
     *  Применение неопеделённых циклов (while, do-while)
     */
    public static void whileControl() {
        int x = 1;

        // неопеделённые циклы (while)
        while (x < 20) x++;
        System.out.println("x = " + x); //x = 20

        do {
            x++;
            System.out.println("Цикл do-while выполнится хоть один раз");
        }
        while (x < 21);

        String input;  // объявлять нужно вне блока т.к. используется в условии
        Scanner in = new Scanner(System.in);
        System.out.println("Введите 'Y' для выхода");
        do {
            input = in.next();
            if (input.equals("q")) continue;      // если ввели 'q', то переходим на начало цикла, т.е. Input выведено не будет
            System.out.println("Input: " + input);
        } while (!input.equals("Y"));
    }

    /**
     *  Применение условного оператора  - if
     */
    public static void ifControl() {

        int x = 5;
//       условный оператор с одним оператором
        if (x>4) System.out.println("x>4");
        else System.out.println("x<4");     // else не обязателен

        // условный оператор с несколькими операторами
        if (x>4) {
            x++;
            System.out.println("x>4");
        }
        else {
            x--;
            System.out.println("x<4");
        }

        x = 1;
        // повторяющиеся условные операторы
        if (x > 4) {
            System.out.println("x>4");
        }
        else if (x > 3) {
            System.out.println("x>3");
        }
        else if (x > 2) {
            System.out.println("x>2");
        }
        else System.out.println("x<2");

    }
}
