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

import static org.junit.Assert.assertEquals;
import static org.slf4j.LoggerFactory.getLogger;

public class RabbitSimpleTest {
    private static String QUEUE_NAME;
    private static String EXCHANGE_NAME;
    private static String ROUTING_KEY;
    private static String RABBIT_HOST;

    private static byte[] body = (" ^ Message Body ^ " + Calendar.getInstance().getTime()).getBytes();
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

        //Опубликовать сообщение
        // вместо null м.б. указан MessageProperties.PERSISTENT_TEXT_PLAIN - т.е. сохранять сообщения на диске
        channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY, null, body);
        channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY, null, body);

        // Создаём очередь, а т.к. она создана, возвращается ссылка на объект типа DeclareOk с данными об очереди.
        Thread.sleep(1000); //засыпаем на 1 секунду, чтобы данные об очереди успели обновиться
        AMQP.Queue.DeclareOk queueDeclareResult = channel.queueDeclare(QUEUE_NAME, true, false, false, null);

        // Получить данные об очереди: Имя и кол-во сообщений в очереди.
        logger.info("Имя очереди: " + queueDeclareResult.getQueue());
        int count = queueDeclareResult.getMessageCount();
        logger.info("Количество сообщений в очереди: " + count);

        // Проверяем что очередь содержит 2 сообщения
        assertEquals(2, count);
        logger.info(" ~~~ testPublishDemo end ~~~");
    }

    /*
        Сравнить производительность публикации 10000 сообщений
         (1) без подтверждения
		 (2) транзакция на каждое сообщение, т.е. если мы не хотим потерять сообщение при публикации (persistent message) в случае падения кролика,
		     и гарантированно доставить его до кролика, то мы должны получить подтверждение, что сообщение записалось на диск
		     Самое простое решение - делать commit на каждое сообщение.
		     Как работают подтверждения см.: http://www.rabbitmq.com/blog/2011/02/10/introducing-publisher-confirms/
         (3) в одной большой транзакции
         (4) confirmSelect
    */

    @Test
    public void testPublishTransactionProductivity() throws IOException {
        logger.info(" ~~~ testPublishTransactionProductivity begin ~~~");

        //(1) без подтверждения
        long time1 = System.currentTimeMillis();
        for (int i = 0; i < BATCH_SIZE; i++ ) {
            channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY, null, body);
        }
        logger.info("(1) Отправлено " + BATCH_SIZE + " сообщений без транзакций за :" + (System.currentTimeMillis() - time1) + " ms");

        //(2) транзакция на каждое сообщение
        long time2 = System.currentTimeMillis();
        for (int i = 0; i < BATCH_SIZE; i++ ) {
            channel.txSelect();
            channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY, null, body);
            channel.txCommit();
        }
        logger.info("(2) Отправлено " + BATCH_SIZE + " сообщений c транзакцией на каждое сообщения :" +
                (System.currentTimeMillis() - time2) + " ms");

        //(3) в одной большой транзакции
        long time3 = System.currentTimeMillis();
        channel.txSelect();
        for (int i = 0; i < BATCH_SIZE; i++ ) {
            channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY, null, body);
        }
        channel.txCommit();
        logger.info("(3) Отправлено " + BATCH_SIZE + " сообщений в одной транзакции :" +
                (System.currentTimeMillis() - time3) + " ms");

        logger.info(" ~~~ testPublishTransactionProductivity end ~~~");
    }

    @Test
    public void testPublishTransactionProductivityConfirmSelect() throws IOException, InterruptedException {
        logger.info(" ~~~ testPublishTransactionProductivityConfirmSelect begin ~~~");
        //(4) confirmSelect
        final SortedSet<Long> unconfirmedSet =
                Collections.synchronizedSortedSet(new TreeSet());

        //callback для подтверждения сообщений
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

        channel.confirmSelect();

        long time1 = System.currentTimeMillis();
        for (int i = 0; i < BATCH_SIZE; i++ ) {
            unconfirmedSet.add(channel.getNextPublishSeqNo());
            channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY, null, body);
        }

        channel.waitForConfirms();
        while (unconfirmedSet.size() > 0){
            Thread.sleep(10);
        }

        logger.info("(4) Отправлено " + BATCH_SIZE + " сообщений c использованием confirmSelect :" +
                (System.currentTimeMillis() - time1) + " ms");

        logger.info(" ~~~ testPublishTransactionProductivityConfirmSelect end ~~~");
    }

    /*
        Сравнить производительность получения 10000  сообщений
         (1) nextDelivery автоматическое подтверждение
         (2) nextDelivery явное подтверждение
         (3) влияние prefetchCount
         (4) с использование handleDelivery
    */
    @Test
    public void testConsumeDemoAutoAck() throws IOException, InterruptedException {
        logger.info(" ~~~ testConsumeDemoAutoAck begin ~~~");
        //(1) nextDelivery автоматическое подтверждение

        for (int i = 0; i < BATCH_SIZE; i++ ) {
            channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY, null, body);
        }

        Thread.sleep(1000);

        channel.close();
        channel = connection.createChannel();

        long time1 = System.currentTimeMillis();
        final CountDownLatch receivedMessageCount = new CountDownLatch(BATCH_SIZE);

        QueueingConsumer consumerGet = new QueueingConsumer(channel);
        //Автоматическое подтверждение
        boolean autoAck = true;
        channel.basicConsume(QUEUE_NAME, autoAck, consumerGet);

        while (receivedMessageCount.getCount() > 0) {
            receivedMessageCount.countDown();
            QueueingConsumer.Delivery delivery = consumerGet.nextDelivery();

        }
        logger.info("(1) Получено " + BATCH_SIZE + " сообщений c автоматическим подтверждением за " + (System.currentTimeMillis() - time1) + " ms");

        logger.info(" ~~~ testConsumeDemoAutoAck end ~~~");
    }

    @Test
    public void testConsumeDemoExplicitAck() throws IOException, InterruptedException {
        logger.info(" ~~~ testConsumeDemoExplicitAck begin ~~~");
        //(2) nextDelivery явное подтверждение

        for (int i = 0; i < BATCH_SIZE; i++ ) {
            channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY, null, body);
        }

        Thread.sleep(1000);

        channel.close();
        channel = connection.createChannel();

        long time1 = System.currentTimeMillis();
        final CountDownLatch receivedMessageCount = new CountDownLatch(BATCH_SIZE);

        QueueingConsumer consumerGet = new QueueingConsumer(channel);
        //Выключение автоподтверждения
        boolean autoAck = false;
        channel.basicConsume(QUEUE_NAME, autoAck, consumerGet);

        while (receivedMessageCount.getCount() > 0) {
            receivedMessageCount.countDown();
            QueueingConsumer.Delivery delivery = consumerGet.nextDelivery();
            //Явное подтверждение
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
        }
        logger.info("(2) Получено " + BATCH_SIZE + " сообщений c явным подтверждением за " + (System.currentTimeMillis() - time1) + " ms");

        logger.info(" ~~~ testConsumeDemoExplicitAck end ~~~");
    }

    @Test
    public void testConsumeDemoPrefetchSize() throws IOException, InterruptedException {
        logger.info(" ~~~ testConsumeDemoPrefetchSize begin ~~~");
        //(3) влияние prefetchCount
        for (int i = 0; i < BATCH_SIZE; i++ ) {
            channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY, null, body);
        }

        Thread.sleep(1000);

        final int prefetchSize = 10;
        channel.close();
        channel = connection.createChannel();
        channel.basicQos(prefetchSize);

        long time1 = System.currentTimeMillis();
        final CountDownLatch receivedMessageCount = new CountDownLatch(BATCH_SIZE);

        QueueingConsumer consumerGet = new QueueingConsumer(channel);
        channel.basicConsume(QUEUE_NAME, false, consumerGet);

        while (receivedMessageCount.getCount() > 0) {
            receivedMessageCount.countDown();
            QueueingConsumer.Delivery delivery = consumerGet.nextDelivery();
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
        }

        logger.info("(3) Получено " + BATCH_SIZE + " сообщений с использованием prefetchCount за " + (System.currentTimeMillis() - time1) + " ms");
        logger.info(" ~~~ testConsumeDemoPrefetchSize end ~~~");
    }

    @Test
    public void testConsumeDemoHandleDelivery() throws IOException, InterruptedException {
        logger.info(" ~~~ testConsumeDemoHandleDelivery begin ~~~");
        //(4) с использование handleDelivery
        for (int i = 0; i < BATCH_SIZE; i++ ) {
            channel.basicPublish(EXCHANGE_NAME, ROUTING_KEY, null, body);
        }

        Thread.sleep(1000);

        channel.close();
        channel = connection.createChannel();

        long time1 = System.currentTimeMillis();
        final CountDownLatch receivedMessageCount = new CountDownLatch(BATCH_SIZE);


        DefaultConsumer consumer = new DefaultConsumer(channel) {
            int msgCount = 0;
            @Override
            public void handleDelivery(String consumerTag,
                                       Envelope envelope,
                                       AMQP.BasicProperties properties,
                                       byte[] body) throws IOException {

                receivedMessageCount.countDown();
                String routingKey = envelope.getRoutingKey();
                String contentType = properties.getContentType();
                long deliveryTag = envelope.getDeliveryTag();
                msgCount++;
                this.getChannel().basicAck(deliveryTag, false);


            }
        };

        boolean autoAck = false;

        channel.basicConsume(QUEUE_NAME, autoAck, consumer);

        receivedMessageCount.await(60000, TimeUnit.MILLISECONDS);


        logger.info("(4) Получено " + BATCH_SIZE + " сообщений с использованием handleDelivery за " + (System.currentTimeMillis() - time1) + " ms");
        logger.info(" ~~~ testConsumeDemoHandleDelivery end ~~~");
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
