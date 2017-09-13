package org.billing.jlab.oop.interfaces.printer;

public class ConsolePrinter implements IPrinter {

    @Override
    public void printer(final String str) {
        System.out.println(str);
    }
}
