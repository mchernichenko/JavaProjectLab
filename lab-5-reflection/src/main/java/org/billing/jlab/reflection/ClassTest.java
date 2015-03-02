package org.billing.jlab.reflection;

import org.billing.jlab.oop.Employee;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * Рефлексия - это набор средств для получения полной и исчерпывающей информации о каком-либо классе во время выполнения программы.
 * Класс Class описывает свойства контретного класса. Объект типа Class фактически описывает тип, который необязатеольно является классом!
 * Можно делать следующее:
    - Определить класс объекта.
    - Получить информацию о модификаторах класса, полях, методах, конструкторах и суперклассах.
    - Создать экземпляр класса, имя которого неизвестно до момента выполнения программы.
    - Получить и установить значение свойства объекта.
    - Вызвать метод объекта.
 *
 * Получить объект Class можно:
 * 1. по экземпляру, а если экзамплчра нет, то
 * 2. по текстовому представлению
 * 3. по типу T.class, где T некоторый тип
 *
 * Неплохо по рефлексии расписано здесь:
 * <a href="http://www.quizful.net/post/java-reflection-api">Введение в Java Reflection API</a>
 */
public class ClassTest {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {

        // 1. Получить Class по его экземпляру. Полезно, когда не известно какого класса этот экземпляр
        Employee employee = new Employee("Имя Чувака", 75000, "15.12.1987");
        Class aClass = employee.getClass();
        System.out.println("Полное имя класса включая пакет: " + employee.getClass().getName());

        // 2. Получить Class по текстовому имени, например, если конкретного экземпляра нет
        String className = "org.billing.jlab.oop.Employee"; //org.billing.jlab.inner.TalkingClock
        Class aClass1 = Class.forName(className);

        // 3.  Если у нас есть класс, для которого в момент компиляции известен тип, то получить экземпляр класса ещё проще.
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

        // Создание объектов
        // 1. можно cоздать экзeмпляр объекта Class с помощью конструктора по умолчанию (без аргументов)
        // Метод Class.newInstance() предполагает, что у класса, экземпляром которого является заданный объект, есть конструктор по умолчанию
        Date date = (Date) dateClass.newInstance();
        System.out.println("Создание объекта через конструктрор по умолчанию: " + date);

        // 2. Если конструктора по умолчанию нет, то нужно использовать метод класса Constructor.newInstance()
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

        // доступ к private-полям объекта
        try {
            // получение ссылки на поле по его имени
            Field fldSalary = aClass.getDeclaredField("salary"); // если поле public, достаточно использовать метод getField, в нашем случае поле private
            fldSalary.setAccessible(true); // для получения доступа на чтение значения поля

            // чтение поля конкретного экземпляра объекта
            Double salary = (Double) fldSalary.get(employee);
            System.out.println("Прочитали private-поле salary: " + salary);

            fldSalary.set(employee, 700); // изменение private-поля
            System.out.println("\nEmployee с изменённый private-полем salary" + employee);

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        // вызов методов
        Class[] params = {double.class}; // массив типов аргументов
        try {
            Method raiseSalary = aClass.getMethod("raiseSalary", params);  // получаем ссылку на конкретный метод класса
            Object[] args_1 = new Object[]{new Double(10)}; // массив аргументов, т.е. увелчиваем з.п. на 10%
            Double newSalary = (Double) raiseSalary.invoke(employee, args_1);
            System.out.println("\nУваличили з.п. на 10% вызвав метод raiseSalary: " + employee);

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
