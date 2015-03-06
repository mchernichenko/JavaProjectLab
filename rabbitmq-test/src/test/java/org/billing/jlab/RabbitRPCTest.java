package org.billing.jlab;

import org.junit.Ignore;
import org.junit.Test;
import org.slf4j.Logger;

import static org.junit.Assert.assertEquals;
import static org.slf4j.LoggerFactory.getLogger;

/**
 * User: Evgeniy.Chibirev
 */
@Ignore
public class RabbitRPCTest {
    private static Logger logger = getLogger(RabbitRPCTest.class);
    private static int BATCH_SIZE;

    /*
        Создать два процесса, реализующих rpc взаимодействие
        Проверить производительность на 10000 вызовах
    */
    @Test
    public void testRPCSimple() throws Exception {
        logger.info(" ~~~ testRPCSimple begin ~~~");

        RPCServer server = new RPCServer();
        new Thread(server).start();


        RPCClient client = new RPCClient();

        BATCH_SIZE = 10000;
        long time1 = System.currentTimeMillis();
        for (int i=1; i<= BATCH_SIZE; i++) {
            String msg = String.valueOf(i);
            String result = client.call(msg);


            logger.info("Получено сообщение: " + result);

            assertEquals(RPCServer.triple(Integer.parseInt(msg)), Integer.parseInt(result));
        }
        logger.info("Выполнено " + BATCH_SIZE + " RPC вызовов за :" + (System.currentTimeMillis() - time1) + " ms");

        client.close();
        logger.info(" ~~~ testRPCSimple end ~~~");
    }

}
