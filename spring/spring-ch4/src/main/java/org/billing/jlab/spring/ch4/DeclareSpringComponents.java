package org.billing.jlab.spring.ch4;

import org.springframework.context.support.GenericXmlApplicationContext;

/**
 * Вариант конфигурации Spring никак не влияет на способ получения бинов из ApplicationContext
 * Вместо DefaultListableBeanFactory, при использовании BeanFactory, создаётся экземпляр GenericXmlApplicationContext.
 * Этот класс реализует ApplicationContext и способен выполнить начальную загрузку контекста из конфигураций, проеделённых в XML
 * Если заменить один файл конфигурации другим, в данном случае файл ковфигурации в стиле XML на файл конфигурации в стиле
 * аннотаций, то результат будет одинаков.
 *
 */
public class DeclareSpringComponents {
    public static void main(String[] args) {

        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();

        // выбираем вариант конфигурации, либо XML-файлом, либо Java-аннотациями. На результат не влияет.

        //ctx.load("classpath:META-INF/spring/app-context-xml.xml");
        ctx.load("classpath:META-INF/spring/app-context-annotation.xml");

        //создание бинов и внедрение зависимостей согласно загруженному контексту приложения
        ctx.refresh();

     // MessageProvider messageProvider = ctx.getBean("messageProvider", MessageProvider.class);
     // System.out.println(messageProvider.getMessage());

        // пример использования внедрения через метод установки (Setter Injection)
        MessageRenderer messageRenderer = ctx.getBean("messageRenderer", MessageRenderer.class);
        messageRenderer.render();

        // тоже самое, только дополнительно используем внедрение через конструктор (constructor injection)
        // для инициализации текстового сообщения. Теперь "HelloWorld" можно прописать в контексте app-context-xml.xml
        MessageRenderer configurableMessageRenderer = ctx.getBean("configurableMessageRenderer", MessageRenderer.class);
        configurableMessageRenderer.render();


    }

}
