package org.billing.jlab.oop;

/**
 * Класс - менеджер
 */
public class Manager extends Employee {
    private double bonus; //
//    private String strProtected = "qq";

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

    /**
     * Данный пример закомментирован, т.к. мы сделали так, что перепределять его мы не можем.
     * equals в суперклассе сделан финальным, но если потребуется сравнивать объекты только одинаковых типов, то
     * сначала вызываем equals из суперкласса, и затем сравниваем поля, относящиеся только к классу Managers/
     * В данном случае считаем, что два менеджара равнозначны, если, кроме name/salary/hireDay их бонусы тоже равны.
     */
/*    @Override
    public boolean equals(Object o) {
        / определяя метод для подкласса, сначала следует вызвать одноимённый метод из суперкласса, если он конечно есть.
        / Если поля суперкласса совпадают, можно перейтик сравнению полей подкласа
        if (!super.equals(o)) return false;

        / при вызове super.equals(o) мы уже проверили, что объекты принадлежат одному классу, поэтому спокойно приводим тип
        Manager manager = (Manager) o;

        if (Double.compare(manager.bonus, bonus) != 0) return false;

        return true;
    }*/

    @Override
    public int hashCode() {
        long temp = Double.doubleToLongBits(bonus);
        return (int) (temp ^ (temp >>> 32));
    }

    @Override
    public String getDescriber() {
        return "Манагер \"" +super.getName() + "\" c з.п. " + super.getSalary() + " и бонусом " + this.bonus;
    }

    @Override
    public String toString() {
        return super.toString()
                + getClass().getName() + "{" + "bonus=" + bonus + '}';
    }
}

