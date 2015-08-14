package org.billing.jlab.spring.ch4.xml;

import org.billing.jlab.spring.ch4.MessageProvider;

/**
 * Класс возвращающий "Hello World". Компонент отвечающий за получение сообщения.
 */
public class HelloWorldMessageProvider implements MessageProvider
{
    public HelloWorldMessageProvider() {
        System.out.println("run construstor HelloWorldMessageProvider()");
    }

    @Override
    public String getMessage() {
        return "Пример Setter Injection в стиле XML-конфигурации. Привет из xml/HelloWorldMessageProvider";}
}
