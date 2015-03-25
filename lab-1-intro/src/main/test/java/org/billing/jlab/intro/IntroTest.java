package org.billing.jlab.intro;

import org.junit.*;

import static org.billing.jlab.intro.StringType.msgRabbitBodyCreate;

/**
 * Created by Mikhail.Chernichenko on 25.03.2015.
 */
public class IntroTest {

    /*
        Генерилка тела сообщений для кролика
     */
    @Test
    public void testName() throws Exception {

        String msgBody = msgRabbitBodyCreate(4);
        byte[] body = msgBody.getBytes();
        System.out.println("Длина (Кб):" + body.length/1024.00);
        System.out.println("\nmsgBody=" + msgBody);
    }
}
