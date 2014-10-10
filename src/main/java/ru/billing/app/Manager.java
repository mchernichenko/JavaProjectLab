package ru.billing.app;

/**
 * Описание к public классу<br />
 */
public class Manager extends Employee {
    /**
     * @param bonus   Бонус к ЗП для менеджера
     */
    private double bonus;

    public Manager(String name, double salary, int year, int month, int day) {
        super(name, salary, year, month, day);
        this.bonus = 0;
    }

    public Manager(String name, double salary, int year, int month, int day, double bonus) {
        super(name, salary, year, month, day);
        this.bonus = bonus;
    }

    public void setBonus(int bonus) {
        this.bonus = bonus;
    }

    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) return false;

        Manager manager = (Manager) o;

        if (Double.compare(manager.bonus, bonus) != 0) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        long temp;
        temp = Double.doubleToLongBits(bonus);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return super.toString() + "Manager{" +
                "bonus=" + bonus +
                '}';
    }

    /**
     * @since Version 0.77 Здесь пишем версию программы, в которой впервые был внедрён данный компонент
     */
    @Override
    public double getSalary() {
        return super.getSalary() + this.bonus;
    }
}
