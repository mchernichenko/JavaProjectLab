package org.billing.jlab.oop;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Рассматривается:
 * - полиморфизм
 * - особенности работы с массивами объектов
 * - приведение типов
 * - абстрактные классы
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Employee[] staff = new Employee[3]; // массив созданный с помощью new может содержать только объекты типа Employee или его потомков
        Employee employee = new Employee("Имя Чувака", 75000, "15.12.1987");
        Manager manager = new Manager("Имя манагера", 15000, "15.11.1987");


        staff[0] = employee;
        staff[1] = manager; // объявленный тип массива Employee, но может сожержать всех его потомков, т.е. фактическим типом может быть как Employee так и Manager.
        staff[2] = new Employee("Имя Чувака_2", 99000, "15.12.1987");

        manager.setBonus(5000);
        for (Employee e : staff) {
            // JVM знает фактический тип e, поэтому с вызовом e.getSalary проблем не будет.
            System.out.println("Name: " + e.getName()+" Salary: " +e.getSalary());
        }

        Manager[] managers = new Manager[3]; // массив созданный с помощью new может содержать только объекты типа Managers или его потомков
        Employee[] employees = managers; // такое возможно

        // employees[0] = employee; // но здесь ошибка!! т.к. пытаемся пихнуть в массив Managers (а JVM знает с каким типом массив создавался) объект супертипа

        /* При извлечении даных из массива, для того чтобы использовать все функциональные возможности объекта необходимо
           принудительно привести его к фактическому типу.
           Перед приведение необходимо проверить его корректность, т.к. если мы обещаем больше положенного, т.е. присваиваем объекту
           суперкласса переменную подкласса, возникнет ошибка.
           Приведение типов возможно только в иерархии наслелования!
        */
        if (staff[1] instanceof Manager) {
            Manager boss = (Manager) staff[1]; // явное приведение типов
        }

        // Абстрактные классы
        Person[] persons = new Person[3];
        persons[0] = new Employee("Имя сотрудничка", 75000, "15.12.1987");
        persons[1] = new Manager("Имя манагера", 15000, "15.11.1987");
        persons[2] = new Student("Студент", "Физика");

        ((Manager) persons[1]).setBonus(1000);

        for (Person person : persons) {
            System.out.println(person.getDescriber());
        }

        // сравнение объектов
        Employee[] e1 = new Employee[2];
        Person[] p1 = new Person[2];
        e1[0] = new Employee("Имя1", 75000, "15.12.1990");
        e1[1] = new Manager("Имя2", 75000, "15.12.1987");

        p1[0] = new Manager("Имя1", 75000, "15.12.1990");
        p1[1] = new Manager("Имя2", 75000, "15.12.1987");

        // попарно сравнить объекты двух массивов
        if (Arrays.equals(e1, p1)) {
            System.out.println("Объекты в массивах равны");
        } else System.out.println("Объекты в массивах не равны");

    }
}
