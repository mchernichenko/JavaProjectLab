package org.billing.jlab.jmx;


import javax.management.AttributeChangeNotification;
import javax.management.MBeanNotificationInfo;
import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;

/**
 * MBeans – это Java-объекты, которые реализуют определенный интерфейс. В данном случае HelloBean.
 * Интерфейс включает:
 * 1) некие величины, которые могут быть доступны;
 * 2) операции, которые могут быть вызваны;
 * 3) извещения, которые могут быть посланы;
 * 4) конструкторы.
 *
 * В методах set/get просто строка, но  может быть реально ценное значение.
 * Например: свободное место на диске, количество подключаемых пользователей, системное время, новый URL к базе данных для статистики и пр.
 *
 * -- Отправка УВЕДОМЛЕНИЙ --
 * MBeans может генерировать уведомления, например чтобы сигнализировать изменение состояния, обнаруженное событие, или проблему.
 * Для этого нужно релизовать NotificationBroadcaster
 */

    public class Hello extends NotificationBroadcasterSupport implements HelloMBean
{
    private String message = null;
    private int cacheSize = DEFAULT_CACHE_SIZE;
    private static final int DEFAULT_CACHE_SIZE = 200;
    private long sequenceNumber = 1; // порядковый номер сообщения

    public Hello() {
        message = "Default message";
    }

    public Hello(String message) {
        this.message = message;
    }

    @Override
    public void setMessage(String message) {
        this.message = message;
        System.out.println("message set now:" + this.message);
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public void sayMessage() {
        System.out.println(message);
    }

    @Override
    public int add(int x, int y) {
        return x + y;
    }

    @Override
    public int getCacheSize() {
        return this.cacheSize;
    }

    @Override
    public synchronized void setCacheSize(int size) {
        int oldSize = this.cacheSize;
        this.cacheSize = size;

        System.out.println("Cache size now " + this.cacheSize);

        // создание нотификационного сообщения, осображаемого в jconsole
        Notification n =
                new AttributeChangeNotification(this,
                        sequenceNumber++,
                        System.currentTimeMillis(),
                        "CacheSize changed: " + oldSize + "->" + this.cacheSize ,  // msg
                        "CacheSize",  // attributeName
                        "int", // attributeType
                        oldSize,
                        this.cacheSize);

        sendNotification(n);
    }

    @Override
    public MBeanNotificationInfo[] getNotificationInfo() {
        String[] types = new String[] {
                AttributeChangeNotification.ATTRIBUTE_CHANGE
        };
        String name = AttributeChangeNotification.class.getName();
        String description = "An attribute of this MBean has changed";
        MBeanNotificationInfo info =
                new MBeanNotificationInfo(types, name, description);
        return new MBeanNotificationInfo[] {info};
    }
}
