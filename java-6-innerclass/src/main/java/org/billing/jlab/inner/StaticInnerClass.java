package org.billing.jlab.inner;

/**
 * Применение статического внутреннего класса
 * Применяется когда:
 * 1. доступ к объекту внутреннего класса не требуется, т.е. компилятор не будет синтезировать статические get-методы во внешнем классе или передавать ссылку в конструктор внутреннего класса
 * 2. требуется скрыть внутри другого класса, например, как показано ниже, класс Pair в большом проекте может уже существовать или слишком распространено или
 *    делать такой класс смысла нет, то сделав его внутри класса ArrayAlg объекты такого класса будут иметь тип ArrayAlg.Pair, а не Pair
 *
 * Т.е. статический внутренний класс только для удобства, например:
 * требуется найти min и max значение в массиве. Вместо написания двух методов можно обойтись одним, но потребуется возвращать два значения и это можно сделать
 * определив класс Pair с двумя полями.
 * 1. Это имя класса может уже где-то использоваться, то можно сделать его внутрения
 * 2. этому классу не требуется ссылка на внешний класс. Подавить её можно сделав класс static
 *
 * Внутренние классы в интерфейсах автоматически static и public
 *
 */
public class StaticInnerClass {
    public static void main(String[] args) {
        double[] doubles = new double[20];
        for (int i = 0; i < doubles.length; i++) {
            doubles[i] = 100 * Math.random();
        }
        ArrayAlg.Pair pair = ArrayAlg.minmax(doubles);
        System.out.println("min=" + pair.getFirst());
        System.out.println("max=" + pair.getSecond());
    }
}

class ArrayAlg {

    /**
     * Пара чисел с плавающей точкой
     */
    public static class Pair {
        private double first;
        private double second;

        /**
         * Составляет пару из двух чисел
         *
         * @param first  Первое число
         * @param second Второе число
         */
        public Pair(double first, double second) {
            this.first = first;
            this.second = second;
        }

        public double getFirst() {
            return first;
        }

        public double getSecond() {
            return second;
        }
    }

    /**
     * Определяет MAX и MIN числа в массиве
     *
     * @param values Массив чисел  с плавающей точкой
     * @return Пара, первым элементом которой является MIN число, а вторым - MAX
     */
    public static Pair minmax(double[] values) {
        double min = Double.MAX_VALUE;
        double max = Double.MIN_VALUE;
        for (double v : values) {
            if (min > v) min = v;
            if (max < v) max = v;
        }
        return new Pair(min, max);
    }
}
