package org.billing.jlab;

/**
 * Created by Mikhail.Chernichenko on 25.03.2015.
 */
public class Util {
    public static String msgRabbitBodyCreate(int cntPay) {

        String msg="";
        StringBuilder messageBuilder = new StringBuilder();
        String templateMsg = "{" +
                "\"receiptNumber\": \"%1\",\n " +
                "\"paymentId\": 7777777,\n" +
                "\"cashRegistryId\": 20,\n" +
                "\"customerId\": 777,\n" +
                "\"phoneNumber\": \"79217777777\",\n" +
                "\"accountNumber\": \"777\",\n" +
                "\"paymentCategory\": 1,\n" +
                "\"amount\": 777.77,\n" +
                "\"operationDate\": \"20140812T201148\"\n" +
                "}";

        messageBuilder.append("[");
        for (int i = 0; i < cntPay; i++) {
            messageBuilder.append(templateMsg.replace("%1", Integer.toString(i+1)));
            if (i != cntPay-1) { messageBuilder.append(",\n"); }
        }

        messageBuilder.append("]");

        return messageBuilder.toString();
    }
}
