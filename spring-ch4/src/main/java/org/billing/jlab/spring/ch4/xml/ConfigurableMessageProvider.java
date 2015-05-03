package org.billing.jlab.spring.ch4.xml;

import org.billing.jlab.spring.ch4.MessageProvider;

/**
 * Использование внедрения через конструктор (Constructor Injection).
 */

public class ConfigurableMessageProvider implements MessageProvider
{
    private String message;

    public ConfigurableMessageProvider(String msg) {
        this.message = msg;
    }

    // еще один конструктор
    public ConfigurableMessageProvider(int msg) {
        this.message = "Число: " + Integer.toString(msg);
    }

    @Override
    public String getMessage() {
        return message;
    }

}
