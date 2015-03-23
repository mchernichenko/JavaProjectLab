package org.billing.jlab.oop;

/**
 * Абстрактный класс - единственная его цель это быть расширенным (т.е. он не может быть final).
 * Создать экземпляр абстрактного класса нельзя.
 * Абстрактный класс может не содержать ни одного абстрактного метода!
 * Если класс имеет хотябы один абстратный класс, то он должен быть помечен модификатором abstract
 * Абстратный метод заканчивается ; и не имеет тела
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

    @Override
    public String toString() {
        return getClass().getName()+"{" +
                "name='" + name + '\'' +
                '}';
    }
}
