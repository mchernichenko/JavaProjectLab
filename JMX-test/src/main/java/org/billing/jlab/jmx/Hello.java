package org.billing.jlab.jmx;


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
 */

public class Hello extends NotificationBroadcasterSupport implements HelloMBean
{
    private String message = null;

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
}
