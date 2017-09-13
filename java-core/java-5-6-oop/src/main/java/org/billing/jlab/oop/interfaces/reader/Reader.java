package org.billing.jlab.oop.interfaces.reader;

public class Reader implements IReader {

    private final String str;

    public Reader(String str) {
        this.str = str;
    }

    @Override
    public String reader() {
        return this.str;
    }
}
