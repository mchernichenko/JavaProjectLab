package org.billing.jlab.spring.ch4.annotation;

import org.billing.jlab.spring.ch4.MessageProvider;
import org.billing.jlab.spring.ch4.MessageRenderer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Пример объявления бинов Spring в стиле аннотаций
 */

@Service("messageRenderer")
public class StandardOutMessageRenderer implements MessageRenderer {

    private MessageProvider messageProvider = null;

    @Override
    public void render() {
        if (messageProvider == null) {
            throw new RuntimeException(
                    "Должно быть установлено свойство messageProvider класса: "
                            + StandardOutMessageRenderer.class.getName());
        }

        System.out.println(messageProvider.getMessage());
    }

    /**
     * Для внедрения методом установки (для стиля аннотаций) используется @Autowired для set-метода внедрения
     * Можно использовать @Resource, которая поддерживает name для реализации более точных требований DI
     * @param provider
     */
    @Autowired
    //@Resource(name="messageProvider")  // Effect is the same as Autowired
    @Override
    public void setMessageProvider(MessageProvider provider) {
        this.messageProvider = provider;
    }

    @Override
    public MessageProvider getMessageProvider() {
        return this.messageProvider;
    }

}
