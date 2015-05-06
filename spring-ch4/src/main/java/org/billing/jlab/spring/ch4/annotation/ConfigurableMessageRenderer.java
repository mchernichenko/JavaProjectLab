package org.billing.jlab.spring.ch4.annotation;

import org.billing.jlab.spring.ch4.MessageProvider;
import org.billing.jlab.spring.ch4.MessageRenderer;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Компонент отвечающий за визуализацию
 */

@Service("configurableMessageRenderer")
public class ConfigurableMessageRenderer implements MessageRenderer
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
            throw new RuntimeException("Должно быть установлено свойство messageProvider класса: " + org.billing.jlab.spring.ch4.xml.StandardOutMessageRenderer.class.getName());
        }
        System.out.println(msgProvider.getMessage());
    }

    @Resource(name="configurableMessageProvider")
    @Override
    public void setMessageProvider(MessageProvider provider) {
        this.msgProvider = provider;
    }
}
