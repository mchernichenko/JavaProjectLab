package org.billing.jlab.spring.ch4;

import org.springframework.context.support.GenericXmlApplicationContext;

/**
 * Вариант конфигурации Spring никак не влияет на способ получения бинов из ApplicationContext
 * Вместо DefaultListableBeanFactory создаётся экземпляр GenericXmlApplicationContext.
 * Этот класс реализует ApplicationContext и способен выполнить начальную загрузку контекста из конфигураций, проеделённых в XML
 * Если заменить один файл конфигурации другим, в данном случае файл ковфигурации в стиле XML на файл конфигурации в стиле
 * аннотаций, то результат будет одинаков.
 *
 */
public class DeclareSpringComponents {
    public static void main(String[] args) {

        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:app-context-xml.xml");
      //  ctx.load("classpath:app-context-annotation.xml");
        ctx.refresh();

     // MessageProvider messageProvider = ctx.getBean("messageProvider", MessageProvider.class);
     // System.out.println(messageProvider.getMessage());

        MessageRenderer messageRenderer = ctx.getBean("messageRenderer", MessageRenderer.class);
        messageRenderer.render();

        // пример использования внедрения через конструктор
        MessageRenderer configurableMessageRenderer = ctx.getBean("configurableMessageRenderer", MessageRenderer.class);
        configurableMessageRenderer.render();

    }

}
