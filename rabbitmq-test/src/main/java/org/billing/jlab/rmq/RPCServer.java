package org.billing.jlab.rmq;

import com.rabbitmq.client.*;
import org.slf4j.Logger;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * User: Evgeniy.Chibirev
 */
public class RPCServer implements Runnable{
    private static final String RPC_QUEUE_NAME = "rpc_queue";
    private static Logger logger = getLogger(RPCServer.class);

    @Override
    public void run() {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("SRV2-DRCS-TRC15");

        Connection connection = null;
        try {
            connection = factory.newConnection();

            Channel channel = connection.createChannel();

            channel.queueDeclare(RPC_QUEUE_NAME, false, false, false, null);

            channel.basicQos(1);

            QueueingConsumer consumer = new QueueingConsumer(channel);
            channel.basicConsume(RPC_QUEUE_NAME, false, consumer);

            logger.info(" [x] Awaiting RPC requests");

            while (true) {
                QueueingConsumer.Delivery delivery = consumer.nextDelivery();

                AMQP.BasicProperties props = delivery.getProperties();
                AMQP.BasicProperties replyProps = new AMQP.BasicProperties.Builder()
                        .correlationId(props.getCorrelationId())
                        .build();


                String message = new String(delivery.getBody());
                int n = Integer.parseInt(message);

                logger.info(" [.] triple(" + message + ")");
                String response = "" + triple(n);

                channel.basicPublish( "", props.getReplyTo(), replyProps, response.getBytes());

                channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int triple(int n) throws Exception {
        return n * 3;
    }

    public static void main(String[] args) {
        RPCServer server = new RPCServer();
        new Thread(server).start();
    }
}
