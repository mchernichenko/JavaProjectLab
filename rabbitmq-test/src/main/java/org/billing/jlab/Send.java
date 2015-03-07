package org.billing.jlab;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.MessageProperties;

import java.io.IOException;
import java.util.Scanner;

/**
 * Подключение к RabbitMQ и отправка сообщения
 */
public class Send {
    private final static String QUEUE_NAME = "Q_HELLO";

    public static void main(String[] args) throws IOException {

        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        /*Scanner in = new Scanner(System.in); // чтение из консольного потока ввода
        System.out.println("Введите message?");
        while (true) {
            String message = in.nextLine();
            Sender(message);
        }*/
        String message = "msg";
        for (int i = 0; i < 10000; i++) {
            Sender(message + i);
        }

    }

    private static void Sender(String message) throws IOException
    {
        // установка соединения с сервером. Соединение с брокером находящимся на локальной машине.
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost"); // здесь можно указать IP кролика, если брокер работает на другой машине.
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        // создание канала. Для отсылки сообщения необходимо создать очередь, чтобы посылать в неё. Очередь создаётся если её нет.
        // durable - признак того, что очередь физически сохраняется на диске и при падении rabbit все сообщения не пропадут
        // если очередь с таким именем уже есть, но свойство Durability другое, возникнет ошибка
        boolean durable = true;
        channel.queueDeclare(QUEUE_NAME, durable, false, false, null);
        //String message = "Hello World!_21";

        /* публикуем сообщение в очередь через дефолтный direct exchange: (AMQP default) и routing_key = имени очереди
         чтобы само сообшение не потерялось, его требуется также записать на диск - message persistence
         Хотя это тоже не гарантирует сохранность сообщения в rabbit, есть лаг между отсылкой сообщения и записью его на диск
         Для гарантированного приема сообщений существует механим подтверждения publisher confirms: aka Publisher Acknowledgements
        */
        channel.basicPublish("", QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());
       // System.out.println(" [x] Sent '" + message + "'");

        channel.close();
        connection.close();
    }
}
