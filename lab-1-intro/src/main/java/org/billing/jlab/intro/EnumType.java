package org.billing.jlab.intro;

/**
 * Рассматривается использование перечислений
 */
public class EnumType {
    /**
        Объявляем перечисляемый тип Size
     */
    public enum Size {SMALL, MEDIUM, LARGE, EXTRA_LARGE}

    public static void main(String[] args) {
        Size s = Size.MEDIUM;
        System.out.println(s);

    }
}
