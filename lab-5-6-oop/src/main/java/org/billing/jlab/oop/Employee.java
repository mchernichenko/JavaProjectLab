package org.billing.jlab.oop;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

/**
 * Класс - сотрудник
 */
public class Employee extends Person {
    //private String name;
    private double salary;
    private Date hireDay;
    protected String strProtect = "Protected string";

    /**
     * @param name   Имя сотрудника, определено в Person
     * @param salary Зарплата
     * @param hireDay Дата приёма на работу
     */
    public Employee(String name, double salary, String hireDay) {
        super(name);
        //this.name = name;
        this.salary = salary;

        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        try {
            this.hireDay = formatter.parse(hireDay);
        } catch (ParseException e) {
        }
    }

    /**
     * @return Возвращает дату приёма сотрудника на работу
     */
    public Date getHireDay() {
        return hireDay;
    }

    public String getName() {
        return super.getName();
    }

    public double getSalary() {
        return salary;
    }

    /**
     * Увеличивает ЗП сотрудников на %
     *
     * @param byPercent % увеличения ЗП (например, 10 = 10%)
     * @return Величина, на которую повышается ЗП
     */
    public void raiseSalary(double byPercent) {
        double raise = salary * byPercent / 100;
        salary += raise;
    }

    /**
     * Требуется определеить т.к. определён в контракте класса Person
     * @return Описание объекта
     */
    @Override
    public String getDescriber() {
        return "Сотрудничек \"" + super.getName() + "\" имеет з.п. " + getSalary();
    }

    /**
     * ВАЖНО! метод equals суперклассе финальный, чтобы его нельзя переопределить в подклассах
     * и тем самым нарушить принцип симметричности равнозначных объектов
     * Если его делать не финальным, то тогда требуется использовать getClass вместо instanceof и в этом случае на равнозначность
     * можно сравнивать объекты ТОЛЬКО равных классов, т.е. Employee можно сравнить только с Employee, а Managers только с Managers
     * Т.к. при сравнении объектов используюся только общие поля объектов, мы выбираем вариант с instanceof и final
     * Также следует обратить внимание на тип явного параметра Object!!!, если будет другой, то это будет не переопределение метода!!!
     */
    @Override
    public final boolean equals(Object o) {  // явный параметр должен иметь тип Object!
        // 1. быстрая проверка на идентичность
        if (this == o) return true;

        // 2. если явный параметр пустой
        if (o == null) return false;

        // 3. т.к. проверка производится средствами суперкласса, то можно использовать instanceof.
        // это позволит сравнить объекты разных типов Employee и Manager в одной иерархии наследования.
        if (!(o instanceof Employee)) return false;

        // 3. этот вариант, если сементика проверка на равнозначность может измениться в подклассе
        //if (o == null || getClass() != o.getClass()) return false;

        // 4. теперь известно, что объект "o" относится к типу Employee, можно сделать преобразоваине типов.
        Employee employee = (Employee) o;

        // 5. проверить, хранятся ли в полях объктов одинаковые значения
        // Для сравнения объектов удобно сипользовать Objects.equals - возвращает true, если оба объекта null, false - если какойто-то объект null
        // иначе x.equals(y)


        return Objects.equals(this.getName(), employee.getName())
                && (Double.compare(employee.salary, this.salary) == 0)
                && Objects.equals(this.hireDay, employee.hireDay);
    }

    @Override
    public int hashCode() {
        long temp = Double.doubleToLongBits(salary);
        return (int) (temp ^ (temp >>> 32));
    }
}
