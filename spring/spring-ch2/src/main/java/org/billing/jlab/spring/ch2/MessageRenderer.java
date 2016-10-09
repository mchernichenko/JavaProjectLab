package org.billing.jlab.spring.ch2;

/**
 * Интерфейс визуализация сообщения
 */
public interface MessageRenderer {

    public void render();

    public void setMessageProvider(MessageProvider provider);

    public MessageProvider getMessageProvider();
}
