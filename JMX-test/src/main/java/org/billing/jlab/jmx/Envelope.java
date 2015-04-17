package org.billing.jlab.jmx;

/**
 * Можно написать обёртку к произвольному классу/классам реализующему MBean (EnvelopeMBean)
 */
public class Envelope implements HelloMBean {

    private SimpleClass server;

    // в обёркту передаём класс, который собственно и будем оборачивать
    public Envelope(SimpleClass server) {
        this.server = server;
    }

    @Override
    public void setMessage(String message) {
        server.setMessage(message);
    }

    @Override
    public String getMessage() {
        return server.getMessage();
    }

    @Override
    public void sayMessage() {
        server.sayHello();
    }
}
