package org.billing.jlab;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;

import java.io.IOException;

/**
 * Подключение к RabbitMQ и отправка сообщения
 */
public class Send {
    private final static String QUEUE_NAME = "hello";

    public static void main(String[] args) throws IOException {

        // установка соединения с сервером. Соединение с брокером находящимся на локальной машине.
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost"); // здесь можно указать IP кролика, если брокер работает на другой машине.
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        // создание канала. Для отсылки сообщения необходимо создать очередь, чтобы посылать в неё.
        // очередь создаётся если её нет.
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        String message = "Hello World!_1";

        // публикуем сообщение в очередь через дефолтный direct exchange: (AMQP default) и routing_key = имени очереди
        channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
        System.out.println(" [x] Sent '" + message + "'");

        channel.close();
        connection.close();
    }
}
