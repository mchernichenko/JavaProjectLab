package org.billing.jlab.intro;

import java.util.Arrays;

/**
 * Массивы - это объект расширяющий Object, вне зависимости от того, элементы каких типов он содержит <br/>
 * - Объявление, инициализация <br/>
 * - перебор <br/>
 * - использвание API над массивами. <br/>
 * - методы с переменным числом параметров <br/>
 * <img src="../../../../resources/Arrays.png" alt="API работы с массивами" />
 */
public class ArraysType {
    public static void main(String... args) {

        // объявление массивов. При создании инициализируются 0,false,null
        int[] a = new int[100]; // более правильный способ.
        int b[] = new int[100]; // менее предпочтительный способ
        int[] с = {5, 2, 3, 1, 4}; // при инициализация массива, new не нужен в этом случае
        int[][] d = {{1, 2}, {3, 4}, {4, 5}}; // многомерный массив; это массив из 3-х элементов содержаших ссылки на 3 массива по 2 элемента
        Object object = new int[10]; // массив - это объект расширяющий Object

        for (int i = 0; i < 100; i++) {
            a[i] = i;  // пределы массива превышать нельзя, иначе исключение
        }

//      itar - перебор массива
        for (int i = 0; i < b.length; i++) {
            int i1 = b[i];
        }
//      iter - другой способ перебора массива в стиле for each
        for (int element : b) {
            System.out.printf(" %d", element);
        }
        // перевор двумерного массива
        for (int[] row : d) {
            for (int value : row) {
                System.out.println(value);
            }
        }

        System.out.println("\nБолее простой способ вывести все элементы массива:" + Arrays.toString(b));
        System.out.println("Вывод двумерного массива:" + Arrays.deepToString(d));

//      Разные операции над массивом
        int[] copyArray;
        copyArray = Arrays.copyOf(b, b.length); // копирование массива
        copyArray = Arrays.copyOf(copyArray, 2 * copyArray.length); // увеличение длины массива. Дополняется 0/false/null в зависимости от типа
        Arrays.sort(с);   // сортировка
        System.out.println("Отсортированный массив: " + Arrays.toString(с));

    }

    /**
     * Метод с переменым количеством параметров.
     * Компилятор передаёт методу параметры в виде массива, например, new double[] {1.0,2.0,3.0,4.0 ....}
     * параметр values может быть только последеним
     * @param values - наибольшее из переданных параметров.
     */
    public static double methodWithManyParameters(double... values) {
        double largest = Double.MAX_VALUE;
        for (double v : values) {
            if (v > largest) largest = v;
        }
        return largest;
    }

}
