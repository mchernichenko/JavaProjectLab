package org.billing.jlab.spring.ch5.lifecycle;

import org.springframework.beans.factory.BeanCreationException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

/**
 * Пример проверки конфигурации бина и методе обратного вызова init(), который прописан в ApplicationContext
 * для бина прописывается атрибут init-method
 * Бин сконфигурирован верно если определено свойство - age
 */
public class SimpleBean {

    private static final String DEFAULT_NAME = "Дефолтное значение";

    private String name = null;

    private int age = Integer.MIN_VALUE;

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Метод инициализации
     * Проверяет установлено ли свойство name
     */
    public void init() {
        System.out.println("Инициализация бина");

       if (name == null) {
            System.out.println("Свойство бина \'name\' не определено. Будет использовано дефолтное значение");
            name = DEFAULT_NAME;
        }

        if (age == Integer.MIN_VALUE) {
            throw new IllegalArgumentException(
                    "Необходимо установить свойство age любого бина этого типа " + SimpleBean.class);
        }
    }

    public String toString() {
        return "Name: " + name + "\nAge: " + age;
    }


    public static void main(String[] args) {
    	
    	GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
    	ctx.load("classpath:META-INF/spring/lifecycle/initMethod.xml");
    	ctx.refresh();  // Refresh the ApplicationContext after XML config file loaded

        SimpleBean simpleBean1 = getBean("simpleBean1", ctx);    
        SimpleBean simpleBean2 = getBean("simpleBean2", ctx);
        SimpleBean simpleBean3 = getBean("simpleBean3", ctx);
    }

    /**
     * Метод получения бина и вывод его деталей, если он получен успешно
     * Если метод инициализации генерит исключение, то Spring помещает его в оболочку BeanCreationException
     * Мы его перехватывает и пишем в консоль информацию об ошибке
     *
     * @param beanName - имя бина
     * @param ctx - контекст бина
     * @return - экземпляр бина
     */
    private static SimpleBean getBean(String beanName, ApplicationContext ctx) {
        try {
            SimpleBean bean =(SimpleBean) ctx.getBean(beanName);
            System.out.println(bean);
            return bean;
        } catch (BeanCreationException ex) {
            System.out.println("В конфигурации бина обнаружена ошибка: "
                    + ex.getMessage());
            return null;
        }
    }

}