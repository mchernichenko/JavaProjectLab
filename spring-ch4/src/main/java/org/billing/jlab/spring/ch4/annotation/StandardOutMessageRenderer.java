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
     * В данном случае мы точно указываем внедряемый бин т.е. configurableMessageProvider
     * Если указать аннотацию @Autowired, то внедряемым бином может быть класс, реализующий интерфейс MessageProvider
     * и помечен аннотацией @Service, а таких может оказаться несколько, например HelloWorldMessageProvider,
     * и Spring будет ругаться, т.к. не сможет однозначно
     * определить внедряемый объект. В этой ситуации требуется использовать @Resource
     * @param provider
     */
   // @Resource(name="configurableMessageProvider")  // Effect is the same as Autowired
    @Autowired
    public void setMessageProvider(MessageProvider provider) {
        this.messageProvider = provider;
    }

    @Override
    public MessageProvider getMessageProvider() {
        return this.messageProvider;
    }

}
