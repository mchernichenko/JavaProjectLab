package org.billing.jlab.spring.ch4.annotation;

import org.billing.jlab.spring.ch4.MessageProvider;
import org.springframework.stereotype.Service;

/**
 * Пример объявления бинов Spring в стиле аннотаций
 * Аннотация @Service(имя бина) используется для указания на то, что этот бин предоставляет службы,
 * которые могут понадобиться другим службам
 */

@Service("messageProvider")
public class HelloWorldMessageProvider implements MessageProvider {

    public String getMessage() {

        return "Hello World!";
    }

}
