package org.billing.jlab.obj;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Миша on 20.01.2015.
 */
public class Employer
{
    private String name;
    private int salary;
    private Date hireDay;

    public Date getHireDay() {
        return hireDay;
    }

    public String getName() {
        return name;
    }

    public int getSalary() {
        return salary;
    }

    private Employer(String name, int salary, String hireDay) {
        this.name = name;
        this.salary = salary;

        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        try {
            this.hireDay = formatter.parse(hireDay);
        } catch (ParseException e) {
        }
    }
}
