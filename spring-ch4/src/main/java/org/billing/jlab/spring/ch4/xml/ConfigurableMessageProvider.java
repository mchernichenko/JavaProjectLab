package org.billing.jlab.spring.ch4.xml;

import org.billing.jlab.spring.ch4.MessageProvider;

/**
 * Использование внедрения через конструктор (Constructor Injection).
 * В отличие HelloWorldMessageProvider, где сообщение явно закодивано, то здесь сообщения берётся из контекста и внедряется через конструктор
 */

public class ConfigurableMessageProvider implements MessageProvider
{
    private String message;

    public ConfigurableMessageProvider(String msg) {
        this.message = "Инициализация в конструкторе свойства 'message' значением: " + msg;
    }

    // еще один конструктор
    public ConfigurableMessageProvider(int msg) {
        this.message = "Инициализация в конструкторе свойства 'message' значением: " + Integer.toString(msg);
    }

    @Override
    public String getMessage() {
        return "Пример Constructor Injection в стиле XML-конфигурации. " + message;
    }

}
