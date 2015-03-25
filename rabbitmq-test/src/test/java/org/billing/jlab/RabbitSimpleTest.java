package org.billing.jlab;

import com.rabbitmq.client.*;
import org.junit.*;
import org.slf4j.Logger;

import java.io.IOException;
import java.util.Calendar;
import java.util.Collections;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.billing.jlab.Util.msgRabbitBodyCreate;
import static org.junit.Assert.assertEquals;
import static org.slf4j.LoggerFactory.getLogger;

import org.billing.jlab.Util;

public class RabbitSimpleTest {
    private static String QUEUE_NAME;
    private static String EXCHANGE_NAME;
    private static String ROUTING_KEY;
    private static String RABBIT_HOST;

    private static byte[] body = (" ^ Message Body ^ " + Calendar.getInstance().getTime()).getBytes();
    private static byte[] body1 = ("Message Body").getBytes();

    private static int BATCH_SIZE;

    private static Connection connection;
    private static Channel channel;

    private static Logger logger = getLogger(RabbitSimpleTest.class);

    // Выполняется в начале всех тестов
    @BeforeClass
    public static void setUp() throws IOException {
        // 1. установка соединения с сервером. Соединение с брокером находящимся на локальной машине.
        //    можно указать IP кролика вместо localhost, если брокер работает на другой машине.

        RABBIT_HOST = "172.20.112.157"; //"172.20.112.141" - Юрин кроль
        EXCHANGE_NAME = "ps.demo.exchange";
        ROUTING_KEY = "ps.pay";
        QUEUE_NAME = "ps.demo.queue";

        BATCH_SIZE = 10_000;

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(RABBIT_HOST);
        factory.setUsername("test");
        factory.setPassword("test");
        factory.setVirtualHost("/");
        factory.setPort(5672);

        connection = factory.newConnection();
      //  channel = connection.createChannel();
     }

    //Выполняется перед каждым тестом
    @Before
    public void createChannelAndDeclareExchangeQueueBind() throws IOException {
        // создание канала - т.е. настройка трубы (создание очереди, связывание её с exchange) на которую затем вешается подписчик
        // для вычитки сообщений из очереди

        channel = connection.createChannel();
        // callback для сообщения о закрытии канала
        channel.addShutdownListener(new ShutdownListener() {
            @Override
            public void shutdownCompleted(ShutdownSignalException cause) {
                //logger.info(cause.getReason().toString());
            }
        });
        // Объявить exchange, queue, binding
        boolean durable = true;  // durable - признак того, что очередь физически сохраняется на диске и при падении rabbit все сообщения не пропадут
        boolean exclusive = false;
        boolean autodelete = false;

        channel.exchangeDeclare(EXCHANGE_NAME, "direct", durable); // a durable, non-autodelete exchange of "direct" type
        channel.queueDeclare(QUEUE_NAME, durable, exclusive, autodelete, null); // a durable, non-exclusive, non-autodelete queue
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, ROUTING_KEY);
    }

    /*  Соединиться с rabbitmq
        Опубликовать сообщение
        Получить данные об очереди
        Удалить очередь и exchange
    */
    @Test
    public void testPublishDemo() throws IOException, InterruptedException {
        logger.info(" ~~~ testPublishDemo begin ~~~");

        // генерилка тела сообщения из платежей
        byte[] msgBody = msgRabbitBodyCreate(450,0).getBytes();

        //Опубликовать сообщение
        // вместо null м.б. указан MessageProperties.PERSISTENT_TEXT_PLAIN - т.е. сохранять сообщения на диске
        channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY, null, msgBody);
      //  channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY, null, body);

        // Создаём очередь, а т.к. она создана, возвращается ссылка на объект типа DeclareOk с данными об очереди.
        Thread.sleep(1000); //засыпаем на 1 секунду, чтобы данные об очереди успели обновиться
        AMQP.Queue.DeclareOk queueDeclareResult = channel.queueDeclare(QUEUE_NAME, true, false, false, null);

        // Получить данные об очереди: Имя и кол-во сообщений в очереди.
        logger.info("Имя очереди: " + queueDeclareResult.getQueue());
        int count = queueDeclareResult.getMessageCount();
        logger.info("Количество сообщений в очереди: " + count);
        logger.info("Длина одного сообщения: " + msgBody.length);


        // Проверяем что очередь содержит 2 сообщения
        assertEquals(4, count);
        logger.info(" ~~~ testPublishDemo end ~~~");
    }

    /*
        Сравнить производительность публикации 10000 сообщений
         (1) без подтверждения
		 (2) транзакция на каждое сообщение, т.е. если мы не хотим потерять сообщение при публикации (persistent message) в случае падения кролика,
		     и гарантированно доставить его до кролика, то мы должны получить подтверждение, что сообщение записалось на диск
		     Самое простое решение - делать commit на каждое сообщение. Здесь проблемы с производительностью из=за блокировок. Publisher должен ждать ответа от брокера на каждое сообщение.
		     Как работают подтверждения см.: http://www.rabbitmq.com/blog/2011/02/10/introducing-publisher-confirms/
         (3) в одной большой транзакции
         (4) confirmSelect - легковесная публикация сообщений с подтверждением
    */

    @Test
    public void testPublishTransactionProductivity() throws IOException {
        logger.info(" ~~~ Пример гарантированной доставки. Begin testPublishTransactionProductivity ~~~");

        //(1) без подтверждения
        long time1 = System.currentTimeMillis();
        for (int i = 0; i < BATCH_SIZE; i++ ) {
            channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY,  MessageProperties.PERSISTENT_BASIC, body);
        }
        logger.info("(1) Отправлено " + BATCH_SIZE + " сообщений без транзакций за :" + (System.currentTimeMillis() - time1) + " ms");

        //(2) транзакция на каждое сообщение
        long time2 = System.currentTimeMillis();
        for (int i = 0; i < BATCH_SIZE; i++ ) {
            channel.txSelect(); // перевод канала в транзакционный режим
            channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY, null, body);
            channel.txCommit();
        }
        logger.info("(2) Отправлено " + BATCH_SIZE + " сообщений c транзакцией на каждое сообщения :" +
                (System.currentTimeMillis() - time2) + " ms");

        //(3) в одной большой транзакции
        long time3 = System.currentTimeMillis();
        
        // перевод канала в транзакционный режим
        AMQP.Tx.SelectOk selectOk = channel.txSelect(); // #method<tx.select-ok>()
        logger.info("selectOk = " + selectOk);

        for (int i = 0; i < BATCH_SIZE; i++ ) {
            channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY,  MessageProperties.PERSISTENT_BASIC, body);
        }
        channel.txCommit();
        logger.info("(3) Отправлено " + BATCH_SIZE + " сообщений в одной транзакции :" +
                (System.currentTimeMillis() - time3) + " ms");

        logger.info(" ~~~ Пример гарантированной доставки. End testPublishTransactionProductivity~~~");
    }

    /*
        (4) confirmSelect - Пример более производительной публикации сообщений с подтверждением (более в 100 раз)
     */
    @Test
    public void testPublishTransactionProductivityConfirmSelect() throws IOException, InterruptedException {
        logger.info(" ~~~ Пример асинхронной гарантированной доставки. testPublishTransactionProductivityConfirmSelect begin ~~~");

        // определяем множество для хранения id неподтверждённых сообщений
        final SortedSet<Long> unconfirmedSet =
                Collections.synchronizedSortedSet(new TreeSet());

        // вешаем callback на канал при подтверждении сообщений для удаления подтверждённых сообщений из множества unconfirmedSet
        channel.addConfirmListener(new ConfirmListener() {
            public void handleAck(long deliveryTag, boolean multiple) throws IOException {
                if (multiple) {
                    unconfirmedSet.headSet(deliveryTag + 1).clear();
                } else {
                    unconfirmedSet.remove(deliveryTag);
                }
            }

            public void handleNack(long deliveryTag, boolean multiple) throws IOException {
                logger.info("NACK");
            }
        });

        // перевод канала в режим подтверждения (acknowledgements)
        AMQP.Confirm.SelectOk selectOk = channel.confirmSelect();
        logger.info("selectOk = " + selectOk); // #method<confirm.select-ok>(); protocolMethodName = confirm.select-ok

        // суём пачку сообщений в очередь
        long time1 = System.currentTimeMillis();
        for (int i = 0; i < BATCH_SIZE; i++) {
            // в режиме подтверждения getNextPublishSeqNo возвращает номер (deliveryTag) следующего публикуемого сообщения (что-то типа sequence.NEXTVAL в Oracle)
            unconfirmedSet.add(channel.getNextPublishSeqNo());
            channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY, null, body);
        }

        // Переводим канал в режм ожидения до тех пор пока по всём сообещиям от брокера не будет получен ответ, либо ack, либо nack .
        channel.waitForConfirms();
        while (unconfirmedSet.size() > 0){
            Thread.sleep(10);
        }

        logger.info("(4) Отправлено " + BATCH_SIZE + " сообщений c использованием confirmSelect :" +
                (System.currentTimeMillis() - time1) + " ms");

        logger.info(" ~~~ Пример асинхронной гарантированной доставки. testPublishTransactionProductivityConfirmSelect end ~~~");
    }

    /*
        Сравнить производительность получения 10000 сообщений
         (1) nextDelivery автоматическое подтверждение (320ms)
         (2) nextDelivery явное подтверждение          (540ms)
         (3) влияние prefetchCount                     (10 увеличивает време чтения (900ms). По дефолту 100, т.е. сколько сообщений worker сразу забирает из очереди на чтение.)
         (4) с использование handleDelivery
    */
    @Test
    public void testConsumeDemoAutoAck() throws IOException, InterruptedException {
        logger.info(" ~~~ Чтение сообщений с автоматическим подтверждением: Begin testConsumeDemoAutoAck ~~~");
        //(1) nextDelivery автоматическое подтверждение

        // предварительное наполнение очереди. Закомментить, если очередь заполнена
        for (int i = 0; i < BATCH_SIZE; i++ ) {
            channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY, null, body);
        }
        Thread.sleep(1000);

        channel.close();
        channel = connection.createChannel();


        long time1 = System.currentTimeMillis();
        final CountDownLatch receivedMessageCount = new CountDownLatch(BATCH_SIZE);

        // создаём подписчика
        QueueingConsumer consumerGet = new QueueingConsumer(channel);
        // Автоматическое подтверждение, т.е. удачение сообщения после чтения сообщения
        boolean autoAck = true;
        // непосредственный запуск подписчика
        channel.basicConsume(QUEUE_NAME, autoAck, consumerGet);

        while (receivedMessageCount.getCount() > 0) {
            receivedMessageCount.countDown();
            // чтение сообщения
            QueueingConsumer.Delivery delivery = consumerGet.nextDelivery();
            String message = new String(delivery.getBody());
       //     System.out.println(" [x] Received '" + message + "'");

        }
        logger.info("(1) Получено " + BATCH_SIZE + " сообщений c автоматическим подтверждением за " + (System.currentTimeMillis() - time1) + " ms");

        logger.info(" ~~~ Чтение сообщений с автоматическим подтверждением. End testConsumeDemoAutoAck ~~~");
    }

    @Test
    public void testConsumeDemoExplicitAck() throws IOException, InterruptedException {
        logger.info(" ~~~ Чтение сообщений c явным подтверждением: Begin testConsumeDemoExplicitAck  ~~~");
        //(2) nextDelivery явное подтверждение

        // предварительное наполнение очереди. Закомментить, если очередь заполнена
        for (int i = 0; i < BATCH_SIZE; i++ ) {
            channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY, null, body);
        }
        Thread.sleep(1000);

        channel.close();
        channel = connection.createChannel();

        long time1 = System.currentTimeMillis();
        final CountDownLatch receivedMessageCount = new CountDownLatch(BATCH_SIZE);

        // создаём подписчика
        QueueingConsumer consumerGet = new QueueingConsumer(channel);
        //Выключение автоподтверждения
        boolean autoAck = false;
        // непосредственный запуск подписчика
        channel.basicConsume(QUEUE_NAME, autoAck, consumerGet);

        while (receivedMessageCount.getCount() > 0) {
            receivedMessageCount.countDown();
            QueueingConsumer.Delivery delivery = consumerGet.nextDelivery();
            //Явное подтверждение
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);

            String message = new String(delivery.getBody());
            // System.out.println(" [x] Received '" + message + "'");
        }
        logger.info("(2) Получено " + BATCH_SIZE + " сообщений c явным подтверждением за " + (System.currentTimeMillis() - time1) + " ms");

        logger.info(" ~~~ Чтение сообщений c явным подтверждением: End testConsumeDemoExplicitAck ~~~");
    }

    /*
        prefetchSize по дефолту 100.
     */
    @Test
    public void testConsumeDemoPrefetchSize() throws IOException, InterruptedException {
        logger.info(" ~~~ Чтение сообщений c использованием prefetchCount и явным подтверждением: testConsumeDemoPrefetchSize begin ~~~");
        //(3) влияние prefetchCount

        // предварительное наполнение очереди. Закомментить, если очередь заполнена
        for (int i = 0; i < BATCH_SIZE; i++ ) {
            channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY, null, body);
        }
        Thread.sleep(1000);
        channel.close();
        channel = connection.createChannel();

        /* балансировка. Отправке сообщений из очереди на обработку нескольким воркерам (worker) происходит равномерно, даже если
          один работает больше другого, т.е. обрабатывает более "тяжелые" сообщения. Это не справедливо.
          Можно запретить посылать сообщение worker`у, если он занят и не обработат текущее сообщение. Сообщение пошлётся менее занятому worker`у
          Иначе каждой N-ое сообщение посылается N-му подписчику.
          prefetchCount = 1 говорит rabbit не давать больше чем одно сообщение worker`у на время, т.е. пока worker не обработает сообщение и не подтвердит его
          новое сообщение он не получит. сообщение получит свободный worker */
        final int prefetchSize = 10;
        channel.basicQos(prefetchSize);

        long time1 = System.currentTimeMillis();
        final CountDownLatch receivedMessageCount = new CountDownLatch(BATCH_SIZE);

        QueueingConsumer consumerGet = new QueueingConsumer(channel);
        channel.basicConsume(QUEUE_NAME, false, consumerGet);

        while (receivedMessageCount.getCount() > 0) {
            receivedMessageCount.countDown();
            QueueingConsumer.Delivery delivery = consumerGet.nextDelivery();
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);

            String message = new String(delivery.getBody());
            // System.out.println(" [x] Received '" + message + "'");
        }

        logger.info("(3) Получено " + BATCH_SIZE + " сообщений с использованием prefetchCount за " + (System.currentTimeMillis() - time1) + " ms");
        logger.info(" ~~~ testConsumeDemoPrefetchSize end ~~~");
    }

    /*
        Чтение сообщений c явным подтверждением с использованием callback при доставке сообщений.
     */
    @Test
    public void testConsumeDemoHandleDelivery() throws IOException, InterruptedException {
        logger.info(" ~~~ Чтение сообщений c явным подтверждением с . testConsumeDemoHandleDelivery begin ~~~");
        //(4) с использование handleDelivery

        for (int i = 0; i < BATCH_SIZE; i++ ) {
            channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY, null, body);
        }
        Thread.sleep(1000);
        channel.close();
        channel = connection.createChannel();

        long time1 = System.currentTimeMillis();
        final CountDownLatch receivedMessageCount = new CountDownLatch(BATCH_SIZE);

        // Создание подписчика с обратным вызовом (call-back)
        // анонимный класс наследуемый от DefaultConsumer и переопределяющий handleDelivery
        // handleDelivery является пустышкой (No-op implementation), декларируется в интерфейсе Consumer
        // анонимный класс не имеет конструктора, все необходимые параметры передаются в конструктор суперкласса
        DefaultConsumer consumer = new DefaultConsumer(channel) {
            int msgCount = 0;
            @Override
            public void handleDelivery(String consumerTag, // customerTag: amq.ctag-PR7tol5OVYFKhGj0_a9fOA - уникальный идентификатор подписчика для канала
                                       Envelope envelope, // Envelope(deliveryTag=1, redeliver=false, exchange=ps.demo.exchange, routingKey=ps.pay)
                                       AMQP.BasicProperties properties,
                                       byte[] body) throws IOException {

                receivedMessageCount.countDown();
                String routingKey = envelope.getRoutingKey();
                String contentType = properties.getContentType();
                long deliveryTag = envelope.getDeliveryTag(); // deliveryTag - некий идентификатор сообщения
                msgCount++;
                this.getChannel().basicAck(deliveryTag, false);

                String message = new String(body);
               // System.out.println(" [x] Received '" + message + "'");
            }
        };

        boolean autoAck = false;
        // непосредственный запуск подписчика
        channel.basicConsume(QUEUE_NAME, autoAck, consumer);
        receivedMessageCount.await(2000, TimeUnit.MILLISECONDS); // нужно подождать, пока не считаем все сообщения, иначе сработает @After и закроет канал

        logger.info("(4) Получено " + BATCH_SIZE + " сообщений с использованием handleDelivery за " + (System.currentTimeMillis() - time1) + " ms");
        logger.info(" ~~~ testConsumeDemoHandleDelivery end ~~~");
    }

    /*  Заливка в RabbitMQ платежей пачками
     */
    @Test
    public void myTestPublishPayments() throws IOException {
        logger.info(" ~~~ Заливка платежей. Begin myTestPublishPayments ~~~");

        int BATCH_MSG = 1;  // кол-во платежей в сообщении
        int BATCH_SIZE = 100_000; // кол-во сообщений

        //(1) публикация без подтверждения
        long time1 = System.currentTimeMillis();
        for (int i = 0; i < BATCH_SIZE; i++) {
            // генерилка тела сообщения состоящей из пачки платежей с уникальным номером чека
            byte[] msgBody = msgRabbitBodyCreate(BATCH_MSG, i * BATCH_MSG).getBytes();
            channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY, MessageProperties.PERSISTENT_BASIC, msgBody);
        }
        logger.info("(1) Отправлено " + BATCH_SIZE + " сообщений без транзакций за :" + (System.currentTimeMillis() - time1) + " ms");
        logger.info(" ~~~ Заливка платежей. End myTestPublishPayments ~~~");
    }

    @After
    public void deleteExchangeQueueAndCloseChannel() throws IOException {
        //Удалить очередь и exchange
    //    channel.queueDelete(QUEUE_NAME);
     //   channel.exchangeDelete(EXCHANGE_NAME);
        channel.close();
    }

    @AfterClass
    public static void tearDown() throws IOException {
        connection.close();
    }









}
