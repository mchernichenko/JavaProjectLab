package org.billing.jlab.spring.ch4.xml;

import org.billing.jlab.spring.ch4.MessageProvider;

/**
 * Класс возвращающий "Hello World". Компонент отвечающий за получение сообщения.
 */
public class HelloWorldMessageProvider implements MessageProvider
{
    @Override
    public String getMessage() {
        return "Привет из xml/HelloWorldMessageProvider";
    }
}
