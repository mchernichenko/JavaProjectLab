package org.billing.jlab.rmq;

import java.io.*;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Mikhail.Chernichenko on 25.03.2015.
 */
public class Util {
    public static void main(String[] args) throws IOException {
        List<UnsuccessWriteOff> list = fillCartData("data/MsisdnForPPS.txt");
        for (UnsuccessWriteOff unsuccessWriteOff : list) {
            System.out.println(unsuccessWriteOff.toString());
        }

        //System.out.println("Вывод двумерного массива:" + Arrays.deepToString(dd1));
        //System.out.println("Длина массива:" + dd1.length);
    }


    /**
     * Формирование пачки платежей в JSON для отправки в RabbitMQ
     *
     * @param cntPay - количество сообщений в пачке
     * @param offset - смещение для формирования уникального номера чека
     * @return - возвращает тело сообщения в JSON для отправки в RabbitMQ  <br />
     * Пример:<code><br />
     * [{<br />
     * "receiptNumber": "12",<br />
     * "paymentId": 777,<br />
     * "cashRegistryId": 20,<br />
     * "customerId": 777,<br />
     * "phoneNumber": "79112422116",<br />
     * "accountNumber": "777",<br />
     * "paymentCategory": 1,<br />
     * "amount": 777.77,<br />
     * "operationDate": "20140812T201148"<br />
     * },<br />
     * {<br />
     * "receiptNumber": "14",<br />
     * "paymentId": 777,<br />
     * "cashRegistryId": 20,<br />
     * "customerId": 777,<br />
     * "phoneNumber": "79112422116",<br />
     * "accountNumber": "777",<br />
     * "paymentCategory": 1,<br />
     * "amount": 777.78,<br />
     * "operationDate": "20140812T201148"<br />
     * }]</code>
     */
    public static String msgRabbitBodyCreate(int cntPay, int offset) {

        String msg = "";
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
            // добавляем , перевод строки для формирования следующего сообщения, если оно не последнее
            if (i != cntPay - 1) {
                messageBuilder.append(",\n");
            }
        }
        messageBuilder.append("]");
        return messageBuilder.toString();
    }

    /**
     * Формирование CART-сообщений в JSON для отправки в RabbitMQ
     *
     * @param cntWriteOff - количество сообщений в пачке. Указать 1
     * @param offset      - смещение для формирования уникального номера подписки в CART_SRV
     * @return - возвращает тело сообщения в JSON для отправки в RabbitMQ
     * Пример:
     * {   "subscriptionId":   "1"
     * ,"subscriberId":     1278356578
     * ,"msisdn":           9268888881
     * ,"objectTypeId":     1
     * ,"objectId":         1
     * ,"objectName":       "Object name"
     * ,"balance":          75
     * ,"chargeAmount":     120
     * }
     */
    public static String msgRabbitCreateMsgForPPS(int cntWriteOff, int offset) {

        String msg = "";
        StringBuilder messageBuilder = new StringBuilder();
        String templateMsg = "{" +
                "\"subscriptionId\": \"%1\",\n " +
                "\"subscriberId\": %2,\n" +
                "\"msisdn\": 9268888881,\n" +
                "\"objectTypeId\": 1,\n" +
                "\"objectId\": 1,\n" +
                "\"objectName\": \"Object name\",\n" +
                "\"balance\": 75,\n" +
                "\"chargeAmount\": 120\n" +
                "}";

        for (int i = 0; i < 1; i++) {
            messageBuilder.append(templateMsg.replace("%1", Integer.toString(i + 1 + offset)));
            if (i != cntWriteOff - 1) {
                messageBuilder.append(",\n");
            }
        }

        return messageBuilder.toString();
    }

    public static List<UnsuccessWriteOff> fillCartData(String filename) throws IOException {

     //   String[][] d = new String[100][2];
        String[] row;
        int iCnt = 1;

        // File file = new File("data/MsisdnForPPS.txt"); // задаём относительный путь, т.к. по умолчанию файл ищется в текущем каталоге

        List<UnsuccessWriteOff> list = new ArrayList<>();

        FileReader fr = new FileReader(filename);
        BufferedReader br = new BufferedReader(fr);
        String line;
        while ((line = br.readLine()) != null) {
            row = line.split(",");
            UnsuccessWriteOff unsuccessWriteOff = new UnsuccessWriteOff();
            unsuccessWriteOff.setMsisdn(row[0]);
            unsuccessWriteOff.setSubscriberId(Long.parseLong(row[1].trim()));
            unsuccessWriteOff.setSubscriptionId(iCnt++);
            list.add(unsuccessWriteOff);
        }
        br.close();
        fr.close();

      //  String[][] copyArray = new String[iCnt][2];

       // copyArray = Arrays.copyOf(d, iCnt);
        return list;
    }
}
