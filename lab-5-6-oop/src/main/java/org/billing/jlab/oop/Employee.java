package org.billing.jlab.oop;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Класс - сотрудник
 */
public class Employee extends Person {
    //private String name;
    private double salary;
    private Date hireDay;

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
}
