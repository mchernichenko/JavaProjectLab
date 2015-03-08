package org.billing.jlab;

import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.QueueingConsumer; // для буфферизации сообщений

/**
 * сосальщик, запускается как процесс и слушает очередь, считывает сообщения и выводит их.
 * определяется аналогично рассыльщику, т.е.
 * открывает коннект и канал, объявляет очередь на которую собираемся подписаться.
 * Рассматриваются:
 * 1. подключение к rabbitMQ
 * 2. создание очереди
 *     пример определения временной очереди.
 * 3. связывание очередии с exchange.
 * 4. Настройка балансировки при отправки сообщенией нескольким worker`м
 * 5. создание подписчика
 * 6. автоподтверждение подписки
 * 7. чтение сообщений из очереди
 * 8. подтвержедние обработки сообщений
 * 9.
 */
public class Recv {

    private static final String EXCHANGE_NAME = "X_HELLO";
    private final static String QUEUE_NAME = "Q_HELLO";

    public static void main(String[] arg) throws java.io.IOException, java.lang.InterruptedException {

        // 1. Установка соединения с сервером. Соединение с брокером находящимся на локальной машине.
        //    можно указать IP кролика вместо localhost, если брокер работает на другой машине.
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();


        // 2. Создание очереди.
        //    Т.к. необходимо убедиться что такая очередь существует, прежде чем мы попытаемся из неё получить сообщения. Очередь создаётся если её нет.
        //    durable - признак того, что очередь физически сохраняется на диске и при падении rabbit все сообщения не пропадут
        //    если очередь с таким именем уже есть, но свойство Durability другое, возникнет ошибка
        boolean durable = true;
        channel.queueDeclare(QUEUE_NAME, durable, false, false, null);
        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        // 2.1. Создание временной очереди.
        // Имеет рандомное имя, например, amq.gen-JzTY20BRgKO-HjmUJj0wLg, а также является non-durable, autodelete queue
        // Таким образом, каждый новый запущенный woker будет создавать свою временную очередь
        String queueName = channel.queueDeclare().getQueue();

        // создание exchange. Должен создаваться сендером, но если его нет, то создаём, т.к. без него не сделать биндинг
        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");
        // 3. связываем очередь и exchange. т.к. exchange fanout, то routing_key не указываем.
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "");

        /* 4. балансировка. Отправке сообщений из очереди на обработку нескольким воркерам (worker) происходит равномерно, даже если
          один работает больше другого, т.е. обрабатывает более "тяжелые" сообщения. Это не справедливо.
          Можно запретить посылать сообщение worker`у, если он занят и не обработат текущее сообщение. Сообщение пошлётся менее занятому worker`у
          Иначе каждой N-ое сообщение посылается N-му подписчику.
          prefetchCount = 1 говорит rabbit не давать больше чем одно сообщение worker`у на время, т.е. пока worker не обработает сообщение и не подтвердит его
          новое сообщение он не получит. сообщение получит свободный worker
        */
        int prefetchCount = 1;
        channel.basicQos(prefetchCount);

        // 5. Создание подписчика и говорим серверу, что мы подписываемся на получение сообщений из очереди
        QueueingConsumer consumer = new QueueingConsumer(channel);

        /* 6. Чтобы не потерять собщение, подписчик подтверждает успешное чтение сообщения и только тогда сообщение удаляется из очереди
           если подписчик умирает без отправки подтверждения (ACK), то rabbitMQ понимает что сообщение не обработано полностью и
            перенаправляет его на дркгого подписчика.
            Каких либо таймаутов нет, перенаправление происходит если теряется соединение с подписчиком
            Подтверждения включены по дефолту
        */
        boolean autoAck = false;
        channel.basicConsume(QUEUE_NAME, autoAck, consumer);

        // 7. после этого RMQ будет доставлять в асинхронном режиме сообщения подписчику в виде объекта, которое считываем
        // QueueingConsumer.nextDelivery() последовательно считывает сообщения и блокирует его для считывания другими процессами
        while (true) {
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());
            System.out.println(" [x] Received '" + message + "'");
            // фейковая обработка сообщения
            doWork(message);
            System.out.println(" [x] Done");

            // 8. подтверждение успешной обработки сообщения. Только после этого сообщение удалится из очереди.
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

