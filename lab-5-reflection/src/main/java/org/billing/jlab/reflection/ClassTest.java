package org.billing.jlab.reflection;

import org.billing.jlab.oop.Employee;

import java.util.Date;

/**
 * Class описывает свойства контретного класса.
 * Объект типа Class фактически описывает тип, который необязатеольно является классом!
 * Получить объект Class можно:
 * 1. по экзампляру
 * 2. по текстовому представлению
 * 3. по T.class, где T некоторый тип
 */
public class ClassTest {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {

        // 1. Получить Class по экземпляру
        Employee employee = new Employee("Имя Чувака", 75000, "15.12.1987");;
        Class aClass = employee.getClass();
        System.out.println("Полное имя класса включая пакет: " + employee.getClass().getName());

        // 2. Получить Class по текстовому имени
        String className = "org.billing.jlab.oop.Employee";
        Class aClass1 = Class.forName(className);

        // 3. Получить по T.class
        Class employeeClass = Employee.class;
        Class integerClass = int.class;
        Class aClass2 = Double[].class;
        Class dateClass = Date.class;

        ReflectionTest.printClass("org.billing.jlab.oop.Employee");

        //JVM поддерживает однозначный объект Class для каждого типа => lkz сравнения можно использовать ==
        if (aClass == Employee.class) {
            System.out.println("О да, это действительно объект Employee.class ");
        }

        // можно cоздать экзампляр объекта Class
        Date date = (Date) dateClass.newInstance();
        System.out.println(date);
    }

     /*
               есть еще 2 способа:
                1. Class cl = e.getClass();
                2. Class cl = Date.class; , т.е. если T - некоторый тип, то T.class - объект соответствующего класса
                   Class cl = Double[].class;
                   Class cl = int.class;

               Объект типа Class фактически описывает тип, который необязатеольно является классом!
            */
}
