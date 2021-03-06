package org.billing.jlab.spring.ch2;

/**
   Данный класс предусматривает выделение логики получения и визуализации в отдельные компоненты, которые в свою очередь реализуют интерфейсы.
   С помощью интерфейсов определяются взаимные зависимости между компонентами и запускающим классом.
 */
public class HelloWorld
{
    public static void main( String[] args ) {

        // требуется взаимодействовать в коде только с методами представлеными интерфейсами, поэтому используем типы интерфейсов
        //MessageRenderer messageRenderer = new StandardOutMessageRenderer();
        //MessageProvider helloWorldMessageProvider = new HelloWorldMessageProvider();

        /* Изменение реализации обного из интерфейсов потребует модификации кода. Для обхода этой проблемы читать имена классов
           реализации интерфейсов будем из файла свойств и создавать их экзампляры в фабричном классе
        */
        MessageRenderer messageRenderer = MessageSupportFactory.getInstance().getMessageRenderer();
        MessageProvider helloWorldMessageProvider = MessageSupportFactory.getInstance().getMessageProvider();

        messageRenderer.setMessageProvider(helloWorldMessageProvider);
        messageRenderer.render();
    }
}
