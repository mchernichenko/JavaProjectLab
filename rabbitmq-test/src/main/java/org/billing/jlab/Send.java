package org.billing.jlab;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.MessageProperties;

import java.io.IOException;
import java.util.Scanner;

/**
 * Герерация сообщений и отправка их в rabbitMQ.
 * Рассматриваются:
 * 1. подключение к rabbitMQ
 * 2. создание exchange
 * 3. создание очереди
 * 4. Публикация сообщений (в дефолтный exchange). Включение поддержки Message durability (сохранения сообщений на диск - messages as persistent)
 * 5.
 */
public class Send {

    private static final String EXCHANGE_NAME = "X_HELLO";
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
        // 1. установка соединения с сервером. Соединение с брокером находящимся на локальной машине.
        //    можно указать IP кролика вместо localhost, если брокер работает на другой машине.
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        // 2. Создание exchange с указанием типа точки входа (direct, topic, headers, fanout)
        //    по дефолту используется AMQP default -  direct exchange
        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");

        // 3. Создание очереди. Этот шаг для отправителья не обязателен. Создание очередей и их Bindings забота получателя сообщений
        // Для отсылки сообщения необходимо создать очередь, чтобы посылать в неё. Очередь создаётся если её нет.
        // durable - признак того, что очередь физически сохраняется на диске и при падении rabbit все сообщения не пропадут
        // если очередь с таким именем уже есть, но свойство Durability другое, возникнет ошибка
        boolean durable = true;
        channel.queueDeclare(QUEUE_NAME, durable, false, false, null);

        /* 4. Публикация сообщений.
         По умолчанию сообщение публикуется в очередь через дефолтный direct exchange (если указать первый параметр ""): (AMQP default), где routing_key = имени очереди
         Чтобы само сообшение не потерялось, его требуется также записать на диск - message persistence
         Хотя это тоже не гарантирует сохранность сообщения в rabbit, есть лаг между отсылкой сообщения и записью его на диск
         Для гарантированного приема сообщений существует механим подтверждения publisher confirms: aka Publisher Acknowledgements
        */

        // связывание AMQP default с очередью QUEUE_NAME и публикация  сообщения в эту очередь через AMQP default
        channel.basicPublish("", QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());

        // публикация сообщения через exchange в очередь, определяемую воркером. Здесь очередь не указана => биндинга с очередью здесь нет.
        // Если воркер не работает или он создаёт очереди не связанные с указанным exchange, то сообщение будет отброшено.
        channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes()); //

       // System.out.println(" [x] Sent '" + message + "'");

        channel.close();
        connection.close();
    }
}
