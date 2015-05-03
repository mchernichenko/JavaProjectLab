package org.billing.jlab.spring.ch4.xml;

import org.billing.jlab.spring.ch4.MessageProvider;

/**
 * Использование внедрения через конструктор (Constructor Injection).
 */

public class ConfigurableMessageProvider implements MessageProvider
{
    private String message;

    public ConfigurableMessageProvider(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
