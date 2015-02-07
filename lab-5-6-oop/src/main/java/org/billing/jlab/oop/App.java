package org.billing.jlab.oop;

/**
 * Рассматривается:
 * - полиморфизм
 * - особенности работы с массивами объектов
 * - преведение типов
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Employee[] staff = new Employee[3];
        Employee employee = new Employee("Имя Чувака", 75000, "15.12.1987");
        Manager manager = new Manager("Имя манагера", 15000, "15.11.1987");

        staff[0] = employee;
        staff[1] = manager; // объявленный тип Employee, но фактическим типом может быть как Employee так и Manager.
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
    }
}
