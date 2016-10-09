package org.billing.jlab.spring.ch4.annotation;

import org.billing.jlab.spring.ch4.MessageProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Пример использования аннотаций для внедрения через конструктор (Constructor Injection)
 * Для внедрения (для стиля аннотаций) используется @Autowired в методе конструкторе целевого бина (неподобие Setter Injection)
 * Аннотацию @Autowired можно применить только к одному методу конструктора, т.е. при использовании аннотация
 * при создании бина можно задействовать только определённый конструктор, помеченный аннотацией @Autowired
 * Более того, при внедрении через конструктор, если имя аргумента одинаковое в разных конструкторах, то
 * в конфигурации можно определить только один бин, в данном случае message с определённым типом, а в остальных конструкторах
 * требуется дополнительно использовать аннотацию Value для определения значения внедряеемого в конструктор
 *
 * Итого: использование аннотаций для внедрения бинов имеет определённые неудобства, лучше уж xml
 */

@Service("configurableMessageProvider")
public class ConfigurableMessageProvider implements MessageProvider
{
    private String message;
    private String message1;

    // значение предназначенное для внедрения рекомендуется выносить за пределы кода
    // в конфигурации определяем бин с ид. message, а класс реализации указываем String

    @Autowired
    public ConfigurableMessageProvider(String message, String message1) {
        this.message = "Инициализация в конструкторе свойства 'message' значением: " + message;
        this.message1 = "Инициализация в конструкторе свойства 'message' значением: " + message1;
    }

    // это вариант с определением значения, внедряемого в конструктор, прямо в коде
/*    public ConfigurableMessageProvider(@Value("This is a configurable message") String message) {
        this.message = message;
    }*/

    @Override
    public String getMessage() {
        return "Пример Constructor Injection c конфигурацией в стиле Java-annotation. " + message + message1;
    }
}
