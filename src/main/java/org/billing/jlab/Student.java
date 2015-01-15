package org.billing.jlab;

import java.util.Objects;

public class Student extends Person {

    private String major;

    /**
     * @param name  Имя студента
     * @param major Специализация студента
     */
    public Student(String name, String major) {
        /* передаём строку name конструктору суперкласса */
        super(name);
        this.major = major;
    }


    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) return false;
        Student student = (Student) o;
        return Objects.equals(this.major, student.major);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + Objects.hashCode(major);
        return result;
    }

    @Override
    public String toString() {
        return super.toString() + "Student{" +
                "major='" + major + '\'' +
                '}';
    }

    @Override
    public String getDescription() {
        return "Студент изучает " + major;
    }
}
