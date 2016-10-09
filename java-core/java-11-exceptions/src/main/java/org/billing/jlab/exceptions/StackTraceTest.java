package org.billing.jlab.exceptions;

/**
 * Пример трассировки стека вызовов метода
 * В любой точке точке выполнения программы можно вывести стек вызова того или иного метода для последующего анализа.
 * Класс StackTraceElement содержит методы, позволяющие получить имя файла, номер исполняемой строки кода, имя класса и пр.
 *
 */
public class StackTraceTest {

    public static int factorial(int n) {
        System.out.println("factorial(" + n + ")");
        Throwable t = new Throwable();
        StackTraceElement[] traceElements;

        // трассировка стека во всех потоках
/*        Map<Thread, StackTraceElement[]> map = Thread.getAllStackTraces();
        for (Thread t : map.keySet()) {
            traceElements = map.get(t);
            // анализ фрейма
        }*/

            traceElements = t.getStackTrace(); // получение текстового описания трассировки стека в основном потоке
            for (StackTraceElement f : traceElements) {
                System.out.println(f);
            }

        int r;
        if (n <=1) r = 1;
        else r = n * factorial(n - 1);
        System.out.println("return " + r);
        return r;
    }

    public static void main(String[] args) {
        factorial(5);
    }
}
