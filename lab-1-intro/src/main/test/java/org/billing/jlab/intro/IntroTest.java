package org.billing.jlab.intro;

import org.junit.*;
import org.slf4j.Logger;

import static org.billing.jlab.intro.StringType.msgRabbitBodyCreate;
import static org.slf4j.LoggerFactory.getLogger;

/**
 *
 */
public class IntroTest {

    private static final int BATCH_SIZE = 4;
    private static Logger logger = getLogger(IntroTest.class);
    /*
        Генерилка тела сообщений для кролика
     */
    @Test
    public void testName() throws Exception {

        long time = System.currentTimeMillis();
        String msgBody = msgRabbitBodyCreate(450); // сгенерить пачку из 4-х платежей
//        logger.info("Создана пачка из " + BATCH_SIZE + " платежей за :" + (System.currentTimeMillis() - time) + " ms");
        System.out.println("Создана пачка из " + BATCH_SIZE + " платежей за: " + (System.currentTimeMillis() - time) + " ms");

        byte[] body = msgBody.getBytes();
        System.out.println("Длина сообщения(Кб):" + body.length/1024.00);
        System.out.println("\nmsgBody=" + msgBody);
    }
}
