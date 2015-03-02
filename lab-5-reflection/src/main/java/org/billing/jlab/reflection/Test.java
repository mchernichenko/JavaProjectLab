package org.billing.jlab.reflection;

import org.billing.jlab.oop.Employee;

/**
 * Created by Миша on 03.03.2015.
 */
public class Test {
    public static void main(String[] args) {
        Employee employee = new Employee("Имя Чувака", 75000, "15.12.1987");
        System.out.println("+++++++++++++++" +employee);
    }
}
