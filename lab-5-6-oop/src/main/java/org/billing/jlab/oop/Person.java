package org.billing.jlab.oop;

/**
 * Абстрактный класс.
 * Создать экземпляр абстрактного класса нельзя.
 * Абстрактный класс может не содержать ни одного абстрактного метода!
 */
public abstract class Person {
    private String name; // общие поля и методы всегда следует помещать в суперкласс, каким бы он ни был: абстрактным или конкрентым

    /**
     * @param name   Имя сотрудника
     */
    public Person(String name) {
        this.name = name;
    }

    // может содержать конкретные метода
    public String getName() {
        return name;
    }

    //
    public abstract String getDescriber();
}
