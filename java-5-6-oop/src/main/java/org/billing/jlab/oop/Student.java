package org.billing.jlab.oop;

/**
 * Расширение абстрактного класса
 */
public class Student extends Person {
    private String major;

    /**
     * @param name  Имя сотрудника, определяется в Person
     * @param major Специализация студента
     *
     */
    public Student(String name, String major) {
        super(name);
        this.major = major;
    }

    @Override
    public String getDescriber() {
        return "Студент \"" + super.getName() + "\" изучает " + this.major;
    }

}
