package org.billing.jlab.exceptions;

import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Элементарное логирование (глобальное)
 * Усовершенствованное протоколитование
 */
public class Logging {
    private static Logger myLogger = Logger.getLogger(Logging.class.getName());

    public static void main(String[] args) {

        /* Уровни протоколирования:
           SEVERE - типа ERROR, WARNING, INFO - используются по умолчанию
           CONFIG, FINE, FINER, FINEST  - эти уровни логирования требуется включать явно, используются для диагностики программ
        */

        // Явное задание уровня логгирования выше INFO
        myLogger.setLevel(Level.FINER);
        // Logger.getGlobal().setLevel(Level.OFF); // запрет вывода протокольных записей
        // Logger.getLogger(loggerName).setLevel(Level.ALL); // разрешение протоколирования на всех уровнях

        // (!!!) Задание уровня логирования выше INFO не достаточно для его вывода на консоль
        // Для консоли ураве нь выводимых сообщений нудно задавать отдельно.
        ConsoleHandler handler = new ConsoleHandler();
        // задаём уравень логирования для вывода на консоль
        handler.setLevel(Level.FINER);
        myLogger.addHandler(handler);

        Logging logging = new Logging();
        logging.run();
    }

    public void run() {
        // Для элементарного протоколоирования служит глобальный регистратор
        Logger.getGlobal().info("Сообщение getGlobal");

        // В промышленных приложения, как правило, все записи не накапливаются в одном глобальном протоколе
        // Создаются собственные регистраторы, см. myLogger.

        // способы логгирование
        myLogger.warning("1. ой, что-то пошно не так");
        myLogger.log(Level.WARNING, "2. что-то опять пошло не так");

        myLogger.fine("1. всё идёт по плану");
        myLogger.log(Level.CONFIG, "2. всё идёт по плану");

        // Для уточнения вызывающего класса, метода следует применять logp
        myLogger.logp(Level.INFO, Logging.class.getName(), "Method: run()", "Сообщение-logp");

        // Пример диагностики выполнения функции
        func("Строка 1", "Строка 2");
    }


    /**
     * Для отследивания порядка выполнения диагностируемой программы имеются служебные методы
     * entering и exiting. При их вызове формируются записи, имеющие уровень FINER, и начнаются со сторк  ENTRY и RETURN
     * @param str1
     * @param str2
     * @return
     */
    public static int func(String str1, String str2) {
        myLogger.entering(Logging.class.getName(), "Функция: func() на вход получает", new Object[]{str1, str2});

        int i = 3;
        myLogger.exiting(Logging.class.getName(), "Функция: func() возвращает: ", i);
        return i;
    }
}
