package org.billing.jlab.spring.ch4.annotation;

import org.billing.jlab.spring.ch4.MessageProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Пример использования аннотаций для внедрения через конструктор (Constructor Injection)
 * Для внедрения (для стиля аннотаций) используется @Autowired в методе конструкторе целевого бина (неподобие Setter Injection)
 * Аннотацию @Autowired можно применить только к одному методу конструктора
 * Дополнительно используется аннотация Value для определения значения внедряеемого в конструктор
 */

@Service("configurableMessageProvider")
public class ConfigurableMessageProvider implements MessageProvider
{
    private String message;

    // значение предназначенное для внедрения рекомендуется выносить за пределы кода
    // в конфигурации определяем бин с ид. message, а класс реализации указываем String

    @Autowired
    public ConfigurableMessageProvider(String message) {
        this.message = message;
    }


/*    public ConfigurableMessageProvider(@Value("90") int message) {
        this.message = "Число: " + Integer.toString(message);
    }*/


    // это вариант с определением значения, внедряемого в конструктор, прямо в коде
/*    public ConfigurableMessageProvider(@Value("This is a configurable message") String message) {
        this.message = message;
    }*/

    @Override
    public String getMessage() {
        return message;
    }
}
