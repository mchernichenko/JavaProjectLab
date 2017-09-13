package org.billing.jlab.oop.interfaces;

import org.billing.jlab.oop.interfaces.printer.AdvanceConsolePrinter;
import org.billing.jlab.oop.interfaces.printer.ConsolePrinter;
import org.billing.jlab.oop.interfaces.printer.IPrinter;
import org.billing.jlab.oop.interfaces.reader.IReader;
import org.billing.jlab.oop.interfaces.reader.Reader;

/**
 * В данном примере описано применение интерфейсов.
 * Имеем задачу:
 *      1. прочитать тестовую информацию с произвольного устройства
 *      2. заменить символы в тескте
 *      3. вывести текст на произвольное устройство
 * Устройства чтения и вывода результата могут заранее не определены и могут меняться.
 * Итого имеем 3 функции, конкретная реализация которых может изменяться:
 *    чтение -> замена символов -> вывод
 *
 *
 */

public class Replacer {

    private IReader reader;
    private IPrinter printer;

    public Replacer(IReader reader, IPrinter printer) {
        this.reader = reader;
        this.printer = printer;
    }

    public void replacer () {
        String str1 = reader.reader();
        String str2 = str1.replace("=)",";)");
        printer.printer(str2);
    }

    public static void main(String[] args) {

        // в данном случае ститываемая строка передаётся в конструкторе, хотя может быть указан url откуда скачать текст
        IReader reader = new Reader("qqweee=)dfdfd0=)");

        // определяем куда подет выводиться информация, в данном случае на консоль
        IPrinter printer = new ConsolePrinter();

        // создаём экзампляр класса заменителя, куда передаём конктетный источник текста и куда следует выводить результат
        Replacer replacer = new Replacer(reader, printer);

        // собственно вызов функции чтения, замены и вывода результата
        replacer.replacer();

        // пример изменения функции вывода. Ничего в реализации Replacer не меняется!!
        // передаётся другая реализация метода вывода и всё
        IPrinter advPrinter = new AdvanceConsolePrinter();
        replacer = new Replacer(reader, advPrinter);
        replacer.replacer();
    }
}
