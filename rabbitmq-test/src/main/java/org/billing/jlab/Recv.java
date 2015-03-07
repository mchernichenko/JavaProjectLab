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
    private final static String QUEUE_NAME = "Q_HELLO";

    public static void main(String[] arg) throws java.io.IOException, java.lang.InterruptedException {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        // Здесь также объявляем очередь, т.к. необходимо убедиться что такая очередь существует, прежде чем мы попытаемч из неё получить сообщения.
        // durable - признак того, что очередь физически сохраняется на диске и при падении rabbit все сообщения не пропадут
        boolean durable = true;
        channel.queueDeclare(QUEUE_NAME, durable, false, false, null);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        /* балансировка. Отправке сообщений из очереди на обработку нескольким воркерам (worker) происходит равномерно, даже если
          один работает больше другого, т.е. обрабатывает более "тяжелые" сообщения. Это не справедливо.
          Можно запретить посылать сообщение worker`у, если он занят и не обработат текущее сообщение. Сообщение пошлётся менее занятому worker`у
          Иначе каждой N-ое сообщение посылается N-му подписчику.
          prefetchCount = 1 говорит rabbit не давать больше чем одно сообщение worker`у на время, т.е. пока worker не обработает сообщение и не подтвердит его
          новое сообщение он не получит. сообщение получит свободный worker
        */
        int prefetchCount = 1;
        channel.basicQos(prefetchCount);

        // Создание подписчика и говорим серверу, что мы подписываемся на получение сообщений из очереди
        QueueingConsumer consumer = new QueueingConsumer(channel);

        /* Чтобы не потерять собщение, подписчик подтверждает успешное чтение сообщения и только тогда сообщение удаляется из очереди
           если подписчик умирает без отправки подтверждения (ACK), то rabbitMQ понимает что сообщение не обработано полностью и
            перенаправляет его на дркгого подписчика.
            Каких либо таймаутов нет, перенаправление происходит если теряется соединение с подписчиком
            Подтверждения включены по дефолту
        */
        boolean autoAck = false;
        channel.basicConsume(QUEUE_NAME, autoAck, consumer);

        // после этого RMQ будет доставлять в асинхронном режиме сообщения подписчику в виде объекта, которое считываем
        // QueueingConsumer.nextDelivery() последовательно считывает сообщения и блокирует его для считывания другими процессами
        while (true) {
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());
            System.out.println(" [x] Received '" + message + "'");
            // фейковая обработка сообщения
            doWork(message);
            System.out.println(" [x] Done");

            // подтверждение успешной обработки сообщения. Только после этого сообщение удалится из очереди.
            // ВАЖНО: не пропустить подтверждение иначе в очереди будут накапливаться сообщения, которые повторно будут слаться подписчику
            // Для проверки наличия очередей без подтверждения: rabbitmqctl list_queues name messages_ready messages_unacknowledged
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
        }
    }

    /**
     * Эмулятор обработки сообщения в отдельном потоке. Время обработки в зависимости от точек в сообщении.
     * Например:
     *  - message. - будет обрабатываться 1 сек
     *  - message.. - будет обрабатываться 2 сек
     * @param task - Сообщение.
     * @throws InterruptedException
     */
    private static void doWork(String task) throws InterruptedException {
        for (char ch: task.toCharArray()) {
            if (ch == '.') Thread.sleep(1000); // секундная задержка
        }
    }
}

