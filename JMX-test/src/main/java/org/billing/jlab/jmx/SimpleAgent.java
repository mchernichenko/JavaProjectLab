package org.billing.jlab.jmx;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.lang.management.ManagementFactory;

/**
 * создадим сервер и сделаем его локальным, т.е. он будет доступен только на нашей машине и с нашей машины.
 * Самые главные стоки здесь
 *  1. Получение экземпляра MBeanServer: mbs = ManagementFactory.getPlatformMBeanServer();
 *     Интерфейс MBeanServer содержит немалое число методов для управления MBean’ами, которые на нем зарегистрированы.
 *  2. Регистрация MBean (экземпляра класса HelloJmx) на платформе MBeanServer: mbs.registerMBean(helloBean, helloName);
 */
public class SimpleAgent
{
    private MBeanServer mbs = null;

    public SimpleAgent()
    {
        // Получить экземпляр MBeanServer.
        mbs = ManagementFactory.getPlatformMBeanServer();

        // Создаем наш MBean
        HelloJmx helloBean = new HelloJmx();
        ObjectName helloName = null;

        try {
            // И регистрируем его на платформе MBeanServer
            helloName = new ObjectName("SimpleAgent:name=hellothere");
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
        SimpleAgent agent = new SimpleAgent();
        System.out.println("SimpleAgent is running...");
        SimpleAgent.waitForEnterPressed();
    }
}
