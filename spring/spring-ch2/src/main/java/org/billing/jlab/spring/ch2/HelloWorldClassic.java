package org.billing.jlab.spring.ch2;

/**
 * Проблемы стандартного HelloWorld: Компонент (класс HelloWorldClassic) отвечающий за визуализацию сообщения также отвечает и за получение этого сообщения
   Т.е. не расширяем, например, если требуется изменить сообщение или выводить сообщение разными способами, то без изменения кода никак
 */
public class HelloWorldClassic
{
    public static void main(String[] args) {
        System.out.println("Hello World");
    }
}
