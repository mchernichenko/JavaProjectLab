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
 * 2. создание exchange,
 * 3. создание очереди
 * 4. Публикация сообщений (сосбености  публикации в AMQP default). Включение поддержки Message durability (сохранения сообщений на диск - messages as persistent)
 * 5.
 */
public class Send {

    private static final String EXCHANGE_NAME = "X_HELLO";
    private static final String QUEUE_NAME = "Q_HELLO";
    private static final String ROUTING_KEY = "ps.pay";


    public static void main(String[] args) throws IOException {

        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        /*Scanner in = new Scanner(System.in); // чтение из консольного потока ввода
        System.out.println("Введите message?");
        while (true) {
            String message = in.nextLine();
            Sender(message);
        }*/
        String message = "msg";
        Sender(message);
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
        channel.exchangeDeclare(EXCHANGE_NAME, "direct");

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

        /* публикация в AMQP default. Он имеет следующую особенность, с помощью него можно сразу публиковать сообщение в очередь.
           "AMQP default" имеет тип direct и для него всегда ROUTING_KEY=QUEUE_NAME. Если есть очередь QUEUE_NAME, то сообщение придёт в неё
           никакого специального биндинга не требуется! Поэтому в параметре для routing_key достаточно указать имя QUEUE_NAME, а EXCHANGE_NAME = ""
        */
        //channel.basicPublish("", QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());

        /* публикация сообщения через exchange в очередь, определяемую воркером. Здесь очередь не указана => биндинга с очередью здесь нет.
           Если воркер не работает или он создаёт очереди не связанные с указанным exchange, то сообщение будет отброшено.
           Если EXCHANGE_NAME - fanout, то ROUTING_KEY не нужен, можно указать ""
        */
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, ROUTING_KEY);

        long time1 = System.currentTimeMillis();
        String str = message;
        System.out.println("Поехали...." + time1);
        for (int i = 0; i < 1000000; i++) {
            str = message + i;
            channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY, null, str.getBytes());
            //  channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY, null, msg.append(i).toString().getBytes());
            //Sender(message + i);
        }
        long time2 = System.currentTimeMillis();
        System.out.println("Приехали... за " + (time2 - time1) / 1000 + "сек");



       // System.out.println(" [x] Sent '" + message + "'");

        channel.close();
        connection.close();
    }
}
