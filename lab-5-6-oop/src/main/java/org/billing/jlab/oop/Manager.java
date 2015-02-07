package org.billing.jlab.oop;

/**
 * Класс - менеджер
 */
public class Manager extends Employee {
    private double bonus; //

    /**
     * @param name   Имя сотрудника
     * @param salary Зарплата
     * @param hireDay Дата приёма на работу
     */
    public Manager(String name, double salary, String hireDay) {
        // эта строка должна быть первой в конструкторе. По дефолту вызывается пустой конструктор super() и он д.б. в этом случае.
        super(name, salary, hireDay); // private поля Employee не доступны, для инициализации требуется вызов конструктова суперкласа.
        bonus = 0;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    /**
     * Возвращает ЗП менеджера.
     *
     * @return Величина ЗП + бонус
     */
    @Override
    public double getSalary() {
        double baseSalary = super.getSalary(); // обратиться к private полю salary суперкласса Employee нельзя, только через getter.
        return baseSalary + this.bonus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Manager manager = (Manager) o;

        if (Double.compare(manager.bonus, bonus) != 0) return false;

        return true;
    }

    @Override
    public int hashCode() {
        long temp = Double.doubleToLongBits(bonus);
        return (int) (temp ^ (temp >>> 32));
    }
}
