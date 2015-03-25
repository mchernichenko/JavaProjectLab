package org.billing.jlab.intro;

import org.junit.*;

import static org.billing.jlab.intro.StringType.msgRabbitBodyCreate;

/**
 * Created by Mikhail.Chernichenko on 25.03.2015.
 */
public class IntroTest {

    @Test
    public void testName() throws Exception {

        String msgBody = msgRabbitBodyCreate(450);
        byte[] body = msgBody.getBytes();
        System.out.println("Длина (Кб):" + body.length/1024.00);
    }
}
