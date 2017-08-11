package org.billing.jlab.javacore.javadoc;

import org.billing.jlab.javacore.intro.Server;

/**
 *
 */
public class Client
{
    public static void main( String[] args ) {

        // пример использования классов из другого модуля, в данном случае класс Server определён в модуле java-1-intro
        Server s = new Server();
        System.out.println("Сумма чисел: " + s.add(2,2));

        new JavaDocClass("Name", 10.0, "23.01.2015");
        System.out.println("Hello World!");
    }
}
