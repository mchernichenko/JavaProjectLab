package org.billing.jlab.spring.ch2;

import java.io.FileInputStream;
import java.util.Properties;

/**
 * Фабричный класс читающий имена классов реализации из файла свойств и создающий их экземпляры в интерфейсах приложения.
 * Решает проблему модификации кода при изменении реализации интерфейсов, т.к. достаточно изменить только классы реализации
 * интерфейсов и прописать новые классы в файле свойств и не изменять код, где создаются эти классы, в данном случае в классе HelloWorld
 */
public class MessageSupportFactory
{
    private static MessageSupportFactory instance;
    private Properties props;
    private MessageRenderer renderer;
    private MessageProvider provider;
    private static final String PATH = "spring-ch2/src/main/";

    // Статический блок выполняется при первичной загрузке класса (а не при создании объекта класса).
    static {
        instance = new MessageSupportFactory();
    }

    public MessageSupportFactory() {
        this.props = new Properties();
        try {
            props.load(new FileInputStream(PATH + "resources/msf.properties"));

            // Получаем классы релизации
            String rendererClass = props.getProperty("renderer.class");
            String providerClass = props.getProperty("provider.class");

            renderer = (MessageRenderer) Class.forName(rendererClass).newInstance();
            provider = (MessageProvider) Class.forName(providerClass).newInstance();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static MessageSupportFactory getInstance() {
        return instance;
    }

    public MessageProvider getMessageProvider() {
        return provider;
    }

    public MessageRenderer getMessageRenderer() {
        return renderer;
    }
}
