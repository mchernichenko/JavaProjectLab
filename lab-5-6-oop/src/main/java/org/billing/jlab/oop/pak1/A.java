package org.billing.jlab.oop.pak1;

/**
 * Разбираемся с уровнем доступа protected.
 * Пример взят из http://www.sql.ru/forum/163813/prostoy-vopros-novichka-po-protected
 */
public class A {
    public int x, y;

    /**
     *  protected: члены класса доступны внутри пакета и в наследниках;
     *
     * @param dx
     * @param dy
     */
    protected void protectedMethod(int dx, int dy)
    {
        x += dx;
        y += dy;
        System.out.println("Вызов protectedMethod из класса A.");
    }

    /**
     * метод без модификатора.
     * default (package-private) (модификатор, по-умолчанию): члены класса видны внутри пакета
     */
    void packagePrivateMethod(int dx, int dy) {
        x += dx;
        y += dy;
        System.out.println(("Вызов packagePrivateMethod из класса A пакета pak1, т.к. + " +
                "определённый packagePrivateMethod в классе B пакета pak2 на самом деле не переопределяет packagePrivateMethod из класса A +" +
                "т.к. он не доступен в другом пакете и => d классе B"));
    }

    public void runProtectedMethod(int dx, int dy) //
    {
        // данный метод переопределён в подклассе и если runProtectedMethod вызывается экземпляром подкласса, то
        // и protectedMethod будет вызван тот который определён в подклассе согласно правилам динамического связывания.
        protectedMethod(dx, dy);
    }

    public void runPrivateMethod(int dx, int dy)
    {
        // данный метод не имеет модификатора, в классе В он не доступен => при вызове runPrivateMethod предком
        // метод packagePrivateMethod будет вызван из класса A, а не его двойник из класса B
        packagePrivateMethod(dx, dy);
    }
}

class X {
    public int x, y;
}

