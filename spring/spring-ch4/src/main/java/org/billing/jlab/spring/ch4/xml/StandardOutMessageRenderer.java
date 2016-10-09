package org.billing.jlab.spring.ch4.xml;

import org.billing.jlab.spring.ch4.MessageProvider;
import org.billing.jlab.spring.ch4.MessageRenderer;

/**
 * Компонент отвечающий за визуализацию
 */
public class StandardOutMessageRenderer implements MessageRenderer
{
    private MessageProvider msgProvider;

    @Override
    public MessageProvider getMessageProvider() {
        return this.msgProvider;
    }

    /**
     * Метод возвращает "HelloWorld"
     */
    @Override
    public void render() {
        if (msgProvider==null) {
            throw new RuntimeException("Должно быть установлено свойство messageProvider класса: " + StandardOutMessageRenderer.class.getName());
        }
        System.out.println(msgProvider.getMessage());
    }

    @Override
    public void setMessageProvider(MessageProvider provider) {
        this.msgProvider = provider;
    }
}
