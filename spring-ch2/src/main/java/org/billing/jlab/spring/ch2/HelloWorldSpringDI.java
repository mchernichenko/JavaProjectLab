package org.billing.jlab.spring.ch2;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 */
public class HelloWorldSpringDI
{
    public static void main(String[] args) {

        // Инициализация ApplicationContext
        ApplicationContext context = new ClassPathXmlApplicationContext("META-INF/spring/app-context.xml");
        MessageRenderer renderer = context.getBean("renderer", MessageRenderer.class);
        renderer.render();
    }
}
