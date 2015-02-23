package org.billing.jlab.reflection;

import org.billing.jlab.oop.Employee;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;

/**
 * Рефлексия - это набор средств для получения полной и исчерпывающей информации о каком-либо классе во время выполнения программы.
 * Класс Class описывает свойства контретного класса.
 * Объект типа Class фактически описывает тип, который необязатеольно является классом!
 *
 * Получить объект Class можно:
 * 1. по экзампляру
 * 2. по текстовому представлению
 * 3. по T.class, где T некоторый тип
 *
 * Неплохо по рефлексии расписано здесь:
 * <a href="http://www.quizful.net/post/java-reflection-api">Введение в Java Reflection API</a>
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

        System.out.println("======= Получение информации о классе Emploeee ======= ");
        ReflectionTest.printClass("org.billing.jlab.oop.Employee");

        System.out.println("===========Сравнение объектов и создание объектов через рефлексию=======================");
        //JVM поддерживает однозначный объект Class для каждого типа => для сравнения можно использовать ==
        if (aClass == Employee.class) {
            System.out.println("О да, это действительно объект Employee.class ");
        }

        // можно cоздать экзeмпляр объекта Class с помощью конструктора по умолчанию (без аргументов)
        // Метод Class.newInstance() предполагает, что у класса, экземпляром которого является заданный объект, есть конструктор по умолчанию
        Date date = (Date) dateClass.newInstance();
        System.out.println("Создание объекта через конструктрор по умолчанию: " + date);

        // Если конструктора по умолчанию нет, то нужно использовать метод класса Constructor.newInstance()
        try {
            // получаем конструктор с определённой сигнатурой
            Class[] paramTypes = new Class[] { String.class, double.class, String.class};
            Constructor constructor = employeeClass.getConstructor(paramTypes);
            try {

                // для создание объекта нужно передать конструктору явные параметры
                Employee emp = (Employee)constructor.newInstance("Имя Чувака", 75000, "15.12.1987");
                System.out.println("Вау, создали объект через Constructor.newInstance():" + emp.getName());

            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
