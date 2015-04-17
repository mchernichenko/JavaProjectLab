package org.billing.jlab.jmx;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

/**
 * Агент JMX - место где регистрируются MBeans - объекты, управляющие определёнными ресурсами, которые они предлставляют
 *
 * Создадим сервер в котором регистрируются MBeans и сделаем его локальным, т.е. он будет доступен только на нашей машине и с нашей машины.
 * Самые главные стоки здесь
 *  1. Получение экземпляра MBeanServer: mbs = ManagementFactory.getPlatformMBeanServer();
 *     Интерфейс MBeanServer содержит немалое число методов для управления MBean’ами, которые на нем зарегистрированы.
 *  2. Регистрация MBean (экземпляра класса HelloJmx) на платформе MBeanServer: mbs.registerMBean(helloBean, helloName);
 */
public class HelloSimpleAgent
{
    private MBeanServer mbs = null;

    public HelloSimpleAgent()
    {
        // Получить экземпляр MBeanServer.
        mbs = ManagementFactory.getPlatformMBeanServer();

        // Создаем наш MBean
        //Hello helloBean = new Hello();
        HelloController helloBean = new HelloController(new Hello());
       // ObjectName helloName = null;

        try {
            // определяем имя объекта для экземпляра MBean.
            // У каждого JMX MBean должно быть имя объекта соответствующее синтаксису, определенному спецификацией JMX,
            // а именно, имя должно включать ДОМЕН, и СПИСОК ключевых СВОЙСТВ
            // В нашем случае дамен это пакет, в котором содержится MBean: org.billing.jlab.jmx
            // Ключевое свойство: тип объекта, как правило класс реализующий интерфейс MBean
            ObjectName helloName = new ObjectName("org.billing.jlab.jmx:type=SimpleAgent");

            // регистрируем MBean c предопределённым именем на платформе MBeanServer
            mbs.registerMBean(helloBean, helloName);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    // Это вспомогательный метод – он позволяет нашей программе остановиться и ждать
    private static void waitForEnterPressed()
    {
        try {
            System.out.println("Press  to continue...");
            System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String argv[])
    {
        HelloSimpleAgent agent = new HelloSimpleAgent();
        System.out.println("SimpleAgent is running...");
        HelloSimpleAgent.waitForEnterPressed();
    }
}
