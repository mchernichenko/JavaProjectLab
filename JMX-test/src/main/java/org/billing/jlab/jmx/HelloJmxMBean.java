package org.billing.jlab.jmx;

/**
 *
 */
public interface HelloJmxMBean {
    // чтение-запись атрибутов
    public void setMessage(String message);
    public String getMessage();

    // операции
    public void sayHello();
}
