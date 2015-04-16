package org.billing.jlab.jmx;

/**
 * Обёртка к Hello
 */
public class HelloController implements HelloMBean {

    private Hello server;

    public HelloController(Hello server) {
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
    public void sayHello() {
        server.sayHello();
    }
}
