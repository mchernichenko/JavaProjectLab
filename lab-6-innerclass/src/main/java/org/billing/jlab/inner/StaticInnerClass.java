package org.billing.jlab.inner;

/**
 * Применение статического внетреннего класса
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
