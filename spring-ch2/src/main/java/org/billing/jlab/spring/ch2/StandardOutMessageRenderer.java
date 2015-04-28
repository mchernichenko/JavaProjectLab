package org.billing.jlab.spring.ch2;

/**
 * Компонент отвечающий за визуализацию
 */
public class StandardOutMessageRenderer implements MessageRenderer
{
    private MessageProvider messageProvider;

    @Override
    public MessageProvider getMessageProvider() {
        return this.messageProvider;
    }

    /**
     * Метод возвращает "HelloWorld"
     */
    @Override
    public void render() {
        if (messageProvider==null) {
            throw new RuntimeException("Должно быть установлено свойство messageProvider класса: " + StandardOutMessageRenderer.class.getName());
        }
        System.out.println(messageProvider.getMessage());
    }

    @Override
    public void setMessageProvider(MessageProvider provider) {
        this.messageProvider = provider;
    }
}
