package org.billing.jlab.obj;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        Employer[] staff = new Employer[3];
        staff[0] = new Employer("Имя работничка", 15000, "15.12.1987");
        staff[1] = new Employer("Имя работничка_1", 15000, "15.12.1997");
        staff[2] = new Employer();;

        Employer employer = new Employer("Имя работничка", 75000, "15.12.1987");
        System.out.println("Hello World!");

        System.out.println(staff[2].getFinalName());
    }
}
