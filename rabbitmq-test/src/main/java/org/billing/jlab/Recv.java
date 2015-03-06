package org.billing.jlab;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.QueueingConsumer; // для буфферизации сообщений

/**
 * сосальщик, запускается как процесс и слушает очередь, считывает сообщения и выводит их.
 * определяется аналогично рассыльщику, т.е.
 * открывает коннект и канал, объявляет очередь на которую собираемся подписаться.
 */
public class Recv {
    private final static String QUEUE_NAME = "hello";

    public static void main(String[] arg) throws java.io.IOException, java.lang.InterruptedException {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        // объявляем очередь, т.к. необходимо убедиться что такая очередь существует, прежде чем мы попытаемч из неё получить сообщения.
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        QueueingConsumer consumer = new QueueingConsumer(channel);
        channel.basicConsume(QUEUE_NAME, true, consumer);

        while (true) {
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());
            System.out.println(" [x] Received '" + message + "'");
        }
    }
}

