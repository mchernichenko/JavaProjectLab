package org.billing.jlab;

/**
 * Created by Mikhail.Chernichenko on 25.03.2015.
 */
public class Util {

    /**
     * Формирование пачки платежей в JSON для отправки в RabbitMQ
     * @param cntPay - количество сообщений в пачке
     * @param offset - смещение для формирования уникального номера чека
     * @return - возвращает тело сообщения в JSON для отправки в RabbitMQ  <br />
     * Пример:<code><br />
     * [{<br />
    "receiptNumber": "12",<br />
    "paymentId": 777,<br />
    "cashRegistryId": 20,<br />
    "customerId": 777,<br />
    "phoneNumber": "79112422116",<br />
    "accountNumber": "777",<br />
    "paymentCategory": 1,<br />
    "amount": 777.77,<br />
    "operationDate": "20140812T201148"<br />
    },<br />
    {<br />
    "receiptNumber": "14",<br />
    "paymentId": 777,<br />
    "cashRegistryId": 20,<br />
    "customerId": 777,<br />
    "phoneNumber": "79112422116",<br />
    "accountNumber": "777",<br />
    "paymentCategory": 1,<br />
    "amount": 777.78,<br />
    "operationDate": "20140812T201148"<br />
    }]</code>
     */
    public static String msgRabbitBodyCreate(int cntPay, int offset) {

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
            messageBuilder.append(templateMsg.replace("%1", Integer.toString(i + 1 + offset)));
            if (i != cntPay - 1) {
                messageBuilder.append(",\n");
            }
        }
        messageBuilder.append("]");
        return messageBuilder.toString();
    }
}
