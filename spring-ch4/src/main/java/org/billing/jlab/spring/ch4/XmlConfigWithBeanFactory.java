package org.billing.jlab.spring.ch4;

import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.FileSystemResource;

/**
 * Пример инициализации BeanFactory и получения бина oracle для работы с ним
 * DefaultListableBeanFactory - одна из двух основный реализаций BeanFactory, поставляемый Spring
 * ApplicationContext - расширение BeanFactory и предоставляющий намного больше вариантов конфигурации чем интерфейс BeanFactory.
 * При разработке рекомендуется взаимодействовать с платформой Spring через ApplicationContext
 */
public class XmlConfigWithBeanFactory
{
    public static void main( String[] args ) {
            DefaultListableBeanFactory factory = new DefaultListableBeanFactory();

        // читаем информацию из XML-файла с помощью XmlBeanDefinitionReader
        // плвтформа Spring предоставляет класс PropertiesBeanDefinitionReader(factory), которыйпозволяет управлять конфигурацией бина
        // c использоваинем свойств отличных от XML (обычно истользуется для тривиальных конфигов)

        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinitions(new FileSystemResource("xmlBeanFactory.xml"));

        // после того как реализация BeanFactory создана и сконфигурирована, извлекаем бин Oracle, используя его Ид, которое настроено в XML конфигурации

        // Object oracle = (Oracle) factory.getBean("oracle");
        Oracle oracle = factory.getBean("oracle", Oracle.class);
        System.out.println(oracle.defineMeaningOfLife());

    }
}
