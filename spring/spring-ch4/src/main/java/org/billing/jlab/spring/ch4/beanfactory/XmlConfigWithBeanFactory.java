package org.billing.jlab.spring.ch4.beanfactory;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.FileSystemResource;

/**
 * Пример взаимодействия  с контейнером Spring DI через интерфейс BeanFactory (хотя на практике всегда нужно пользовать ApplicationContext)
 *
 * Рассматривается создание и внешнее конфигурирование (с помощью xml-конфига) фабрики бинов
 * т.е. инициализация BeanFactory и получения бина oracle для работы с ним
 * DefaultListableBeanFactory - одна из двух основных реализаций BeanFactory, поставляемый Spring
 * ApplicationContext - расширение BeanFactory и предоставляющий намного больше вариантов конфигурации чем интерфейс BeanFactory.
 * При разработке рекомендуется взаимодействовать с платформой Spring через ApplicationContext
 */
public class XmlConfigWithBeanFactory
{
    public static void main( String[] args ) {
        // 1. сначала нужно создать экземпляр класса реализующего интерфейс BeanFactory
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();

        // 2. Конфигурируем класс в соответствие с информацией о бине и его зависимостях, т.е.
        // читаем информацию из XML-файла с помощью XmlBeanDefinitionReader.
        // Также платформа Spring предоставляет класс PropertiesBeanDefinitionReader(factory), который позволяет управлять конфигурацией бина
        // c использоваинем свойств отличных от XML (обычно используется для тривиальных конфигов)

        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinitions(new FileSystemResource("spring-ch4\\src\\main\\resources\\META-INF\\spring\\xmlBeanFactory.xml"));

        // 3. после того как реализация BeanFactory создана и сконфигурирована можно получить доступ к бинам через BeanFactory
        // т.е. извлекаем бин Oracle, используя его Ид, которое настроено в XML конфигурации

        // Object oracle = (Oracle) factory.getBean("oracle");
        Oracle oracle = factory.getBean("oracle", Oracle.class);
        System.out.println(oracle.defineMeaningOfLife());

    }
}
