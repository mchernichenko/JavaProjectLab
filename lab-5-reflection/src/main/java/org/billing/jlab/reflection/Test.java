package org.billing.jlab.reflection;

import org.billing.jlab.oop.Employee;

/**
 * Для тестирования MANIFEST.MF
 */
public class Test {
    public static void main(String[] args) {
        Employee employee = new Employee("Имя Чувака", 75000, "15.12.1987");
        System.out.println("+++++++++++++++" +employee);
    }
}
