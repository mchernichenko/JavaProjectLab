package org.billing.jlab.javacore.intro;

import java.io.Console;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Рассматриваются строки - неизменяемые объекты<br/>
 * - Базовое API для работы со строками<br/>
 * - StringBuilder - построение символьных строк<br/>
 * - printf - форматирование строк<br/>
 * - базовый файловый ввод/вывод, чтение вводимых даных (Scanner)<br/>
 */
public class StringType {
    public static void main(String[] args) throws IOException {

 /*       String msgBody = msgRabbitBodyCreate(450);
        byte[] body = msgBody.getBytes();
        System.out.println("Длина (Кб):" + body.length/1024.00);*/

//      Примеры использования базового API
            useBaseStringApi();

//      Построение строк
           useStringBuilder();

//      Файловый ввод/вывод, чтение вводимых даных
            useScanner();
    }

    /**
     * Чтение вводимых данных с использованием Scanner/Console
     */
    public static void useScanner() throws IOException {
        Scanner in = new Scanner(System.in); // чтение из консольного потока ввода
        System.out.println("Введите Ваше имя?");
        //  String name = in.nextLine(); // чтение целой строки, т.к. имя м.б. из пробелов. Для чтения слова используется next()
        System.out.println("Введите количество лет?");
        //  int age = in.nextInt(); // чтение целочисленног значения

        //System.out.println("Hello, " + name + ". Тебе " + age + " лет."); // вывод результата на консоль

//      Для ввода паролей с консоли Scanner не подходит, т.к. видно чтo вводится. Для этой цели специально предназначен класс Console
//      Отдельных слов и чисел Console читаль не умеет, поэтому для остального пользуем Scanner
        Console console = System.console();
        if (console != null) {
            char[] passwd = console.readPassword("[%s]", "Пароль: "); // пароль возвращается в массив char из соображений безопасности
            String username = console.readLine("Имя пользователя: ");
        }

        // построчное чтение из файла.
        String currentDir = System.getProperty("user.dir");
        System.out.println("Текущий рабочий каталог: " + currentDir); // по умолчанию текущим каталогом является каталог проекта т.е. \JavaProjectLab
        File file = new File("data/FileData.txt"); // задаём относительный путь, т.к. по умолчанию файл ищется в текущем каталоге

        Scanner inFromFile = new Scanner(file);
        while (inFromFile.hasNext()) { // пока есть строка читаем её
            System.out.printf(inFromFile.next() + " ");
        }

        // чтение подряд идущих целых чисел из потока содержащий разделитель. По умолчание разделитель - пробел
        String input = "1 fish 2 fish red fish blue fish";
        System.out.println();
        Scanner inFromString = new Scanner(input); // Важно: в данном случае символьная строка интерпретируется как отдельные символы, а не как имя файла
        inFromString.useDelimiter("\\s*fish\\s*");
        while (inFromString.hasNextInt()) {
            System.out.printf(inFromString.nextInt() + " ");
        }

    }

    /**
     * Построение символьных строк с помощью StringBuilder.
     * Если требуется создать символьную строку из нескольких небольших фрагментов пользоватья сцеплением строк неэфективно, т.к. при каждом
     * сцеплеении создаётся новый объект типа String.
     * Есть ещё StringBuffer, но он менее эффективен и используется при многопоточности.
     */
    public static void useStringBuilder() {
        StringBuilder builder = new StringBuilder();

        builder.append('A'); // добавить единственный символ
        builder.append("строка"); // добавить строку
        builder.insert(1, ".) "); // вставить строку с указанной позиции
        String str = builder.toString(); //при завершении составления строки нужно вызвать toString для получения объекта String состояций из символов содержащихся в StringBuilder
        System.out.println(str); // A.) строка

    }


    /**
     * Пример использования базового API для работы со строками
     */
    public static void useBaseStringApi() {
        String str = "Hello";

        "Hello".equalsIgnoreCase("hello"); // сравнение без учета регистра

        if (str.length() == 0) {
            System.out.println("строка пустая");
        }
        if (str.equals("")) {
            System.out.println("Ещё один способ проверки на пустоту строки.");
        }
        if (str == null) {
            System.out.println("объект String ни на что не ссылается");
        }
        if (str != null && str.length() != 0) { // важно!! если объект ни на что не ссылается, вызов его методов вызовет ошибку, поэтому проверяем
            System.out.println("Строка не пустая и не null");
        }

        // особености определения длины строки в UTF-16
        str = "A" + "\uD835\uDD0A" + "B" + "C"; // A@BC length- 5, codePointCount- 4.
        System.out.println("length() возвращает кол-ко кодовых единиц: " + str.length());
        System.out.println("Истинная длина, т.е. количество коловых точек определяется - codePointCount: " + str.codePointCount(0, str.length()));
        // пример перебора строки по символам
        String strSpell = DataTypePrimitives.getSpellingString(str);
        System.out.println(strSpell);

        // Основное API по работе со строками
        String str1 = "Строка_1";
        String str2 = "Строка_2";

        System.out.println( str1.charAt(0)); // первая кодовая единица, т.е. символ 'C'

        char[] charArray = new char[10];
        str1.getChars(0, 4, charArray, 2);
        System.out.println(charArray);

        str1.codePointAt(0); // первая кодовая точка, т.е. первая буква unicode

        if (str1.compareTo(str2) == -1) { // сравнение строк
            System.out.println("str1 < str2");
        }
        if (str1.endsWith("_1")) {
            System.out.println("Строка заканчивается на _1");
        }

        if (str1.equals(str2)) {
            System.out.println("Строки совпадают. Говорить, что они равны нельзя, т.к. могут ссылаться на разные объекты");
        }

        System.out.println("Индекс начала подстроки \'_\' в строке начиная с позиции 0: " + str1.indexOf("_", 0));
        System.out.println("Индекс начала последней подстроки \'а\' в строке начиная с конца: " + str1.lastIndexOf("а"));

        String replace = str1.replace("Строка", "Stroka");
        System.out.println("Замена подстроки подстрокой: " + replace); // в качестве CharSequence могут быть String или StringBuilder, т.к. это интерфейс

        // выделение подстроки из строки, 1-я граница включена, верхняя нет, итого - Strok.
        // верхняя граница может не указываться => до конца
        System.out.println("Выделение подстроки из строки: " + replace.substring(0, 5));

        System.out.println("Перевод в верхний регистр: " + replace.toUpperCase());
        System.out.println("Убираем начальные и конечные пробелы: " + replace.trim());

        String[] strArr = new String[10];
        System.out.println(Arrays.toString("123,456,7,8,90".split(","))); // разделяет данную строку вокруг данного регулярного выражения

    }

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
