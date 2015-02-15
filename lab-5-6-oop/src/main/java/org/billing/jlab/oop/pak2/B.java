package org.billing.jlab.oop.pak2;

import org.billing.jlab.oop.pak1.A;

/**
 * Разбираемя в protected.
 * В данном классе можно переопределить только protected метод.
 */
public class B extends A {

    @Override
    public void protectedMethod(int dx, int dy) {
        runProtectedMethod(dx, dy);
        System.out.println("Вызов protectedMethod из класса B.");
    }

    /**
     * Это не переопределение, а новый метод класса!!!
     * Важно, что если аналогичный метод есть у предка с аналогичной сигнатурой, но невидимый в подклассе, т.е.
     * c уровнем доступа private или например без указания модификатора, тогда для класса в другом пакете он не видим, то
     * на самом деле никакого переопределения метода здесь нет!!! Т.е. полиморфизм работать не будет.
     * @param dx
     * @param dy
     */
    public void packagePrivateMethod(int dx, int dy) {
        runPrivateMethod(dx, dy);
        System.out.println("Вызов packagePrivateMethod из класса B.");
    }
}
