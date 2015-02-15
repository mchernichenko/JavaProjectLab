package org.billing.jlab.oop.pak1;

/**
 * В данном классе доступны для переопределение как protected так и методы без модификатора.
 * Область действия переопределённых метод можно только расширить до public, т.е. уменьшить никак
 */
public class C extends A {


    @Override
    public void protectedMethod(int dx, int dy) {
        super.protectedMethod(dx, dy);
    }

    @Override
    public void packagePrivateMethod(int dx, int dy) {
        super.packagePrivateMethod(dx, dy);
    }
}
