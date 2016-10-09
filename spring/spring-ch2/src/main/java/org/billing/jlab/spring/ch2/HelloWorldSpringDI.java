package org.billing.jlab.spring.ch2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Демонстрация внедрения зависимостей с помощью Spring контекста
 */
public class HelloWorldSpringDI
{
    public static void main(String[] args) {

        // Инициализация ApplicationContext
        ApplicationContext context = new ClassPathXmlApplicationContext("app-context-xml.xml");

        /*
            Во время инициализации ApplicationContext метод main() получает бин MessageRenderer, используя безопасный к типам метод getBean
            с передачей ему ид. и ожидаемого возвращаемого типа, которым является интейфейс MessageRenderer и вызывает render().
            Spring создаёт реализацию MessageProvider и внедряет её в реализацию MessageRenderer

            (!) Важно то, что мы не должны вносить какие-либо изменения в классы, которые связаны вместе с использованием Spring
         */
        MessageRenderer renderer = context.getBean("renderer", MessageRenderer.class);
        renderer.render();
    }
}
