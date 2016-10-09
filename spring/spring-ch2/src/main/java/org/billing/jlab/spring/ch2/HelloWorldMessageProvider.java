package org.billing.jlab.spring.ch2;

/**
 * Класс возвращающий "Hello World". Компонент отвечающий за получение сообщения.
 */
public class HelloWorldMessageProvider implements MessageProvider
{
    @Override
    public String getMessage() {
        return "Hello World";
    }
}
