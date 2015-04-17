package org.billing.jlab.jmx;

/**
 *
 */
public class SimpleClass {
    private String message = null;

    public SimpleClass() {
        message = "Hello there";
    }

    public SimpleClass(String message) {
        this.message = message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void sayHello() {
        System.out.println(message);
    }
}
