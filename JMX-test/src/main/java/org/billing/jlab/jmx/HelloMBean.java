package org.billing.jlab.jmx;

/**
 * MBean является управляемым объектом Java и может представить устройство, приложение, или любой ресурс, которым нужно управлять.
 * По принятым соглашениям интерфейс должен называться InterfaceNameMBean
 *
 * MBean определяет операции и атрибуты которыми требуется управвлять
 * В данном случае предоставляет полный доступ (get/set) к переменной - message, и предоставляет метод sayHello, который, естественно, можно вызывать
 */
public interface HelloMBean {

    // методы для чтения-записи атрибутов
    public void setMessage(String message);
    public String getMessage();

    // действия
    public void sayMessage();
}
