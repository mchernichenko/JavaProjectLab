package org.billing.jlab.intro;

import java.util.Arrays;

/**
 * Массивы
 */
public class ArraysType {
    public static void main(String[] args) {

        // объявление массивов. При создании инициализируются 0,false,null
        int[] a = new int[100]; // более правильный способ.
        int b[] = new int[100];

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

        System.out.println("\nБолее простой способ вывести все элементы массива:" + Arrays.toString(b));

    }


}
