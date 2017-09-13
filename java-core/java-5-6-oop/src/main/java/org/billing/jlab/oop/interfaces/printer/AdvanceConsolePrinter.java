package org.billing.jlab.oop.interfaces.printer;

/**
 * Created by Mikhail.Chernichenko on 13.09.2017.
 */
public class AdvanceConsolePrinter implements IPrinter {

    @Override
    public void printer(String str) {
        System.out.println("==== Продвинутый вывод на консоль ====");
        System.out.println(str + " length:" + str.length());
    }
}
