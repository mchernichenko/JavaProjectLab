package org.billing.jlab.exceptoins;

import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Исключения, основные моменты:
 * - исключения - это всегда объект типа Exception
 * - есть 2 типа исключений:
 *    ~ проверяемые  (IOException)
 *    ~ непроверяемые (RuntimeException) - всегда по вине программиста
 * - компилятор не обращает внимания на непроверяемые исключения, т.к. это косяки кода, их нужно исправлять, а не отлавливать, например, NullPointerException (использование пустой переменной)
 * - проверяемые исключекния должны быть объявлены (проброс) или обработаны согласно правилам (Это закон: "обработай или объяви"):
 *    ~ метод выбрасывает исключение с помощью throw, за которым следует объект типа Exception
      ~ метод выбрасываемый проверяемое исколючение обязат объявить об этом с помощью выражения throws
      ~ если метод готов обрабтать исключение, то опасный код должен быть заключен в try/catch
 */
public class App 
{
    public static void main( String[] args ) throws Throwable {
      //  readData("data/input.txt");
      //  readData_1("data/input_1.txt");
        readData_2("data/input_2.txt");
    }

    /**
     * Функция построчного чтения из файла -
     * демонстрирует явное генерирование проверяемого исключения EOFException - "непредвиденный конец файла" и объявлекие проверяемых исключений
     * Список выбрасываемых исключений (throws) при объявлении метода говорит, что он "разорвется" на части, если не найдет файл или закончится раньше времени.
     * @param fileName имя файла
     * @throws java.io.EOFException - если файл < 10_000 символов
     */
    public static void readData(String fileName) throws FileNotFoundException, EOFException {
        File file = new File(fileName);
        long len = 0;
        Scanner scanner = new Scanner(file);
        String stroka;
        while (scanner.hasNextLine()) {
            stroka = scanner.nextLine();  // пока есть строка читаем её
            len = len + stroka.length();  // считаем кол-во символов
            System.out.println("stroka = " + stroka);
        }
        scanner.close();

        /* здесь мы решаем, что если файл меньше 10000 символов, т.е. закончился раньше чем следовало, то это нечто ужасное и прекращаем работу.
           Находим подходящее исклчение типа IOException (т.е. проверяемое исключение), например, EOFException и генерируем его  */
        if (len < 100) {
            throw new EOFException("\nERROR:Файл слишком короткий");
        }
    }

    /**
     * Пример использоваения блока try/catch для отлавливания исклчений
     * Аналогично предыдущей функции readData, только здесь исключение FileNotFoundException отлавливается, а не пробрасывается дальше
     * @param fileName
     * @throws EOFException
     */
    public static void readData_1(String fileName) throws EOFException {
        File file = new File(fileName);
        long len = 0;
        try {
            Scanner scanner = new Scanner(file); // это опасный код, который может выбросить исключение !!! если файло не найдено, то переходим на блок catch
            String stroka;
            while (scanner.hasNextLine()) {
                stroka = scanner.nextLine();  // пока есть строка читаем её
                len = len + stroka.length();  // считаем кол-во символов
                System.out.println("stroka = " + stroka);
            }
            scanner.close();

           /* здесь мы решаем, что если файл меньше 10000 символов, т.е. закончился раньше чем следовало, то это нечто ужасное и прекращаем работу.
              Находим подходящее исклчение типа IOException (т.е. проверяемое исключение), например, EOFException и генерируем его  */
            if (len < 10000) {
                throw new EOFException("\nERROR: Файл слишком короткий");
            }

          /* если каждое исключение нужно обработать уникальным образом, то создаются отдельные catch блоки для каждого, но можно отловить все сразу, т.к. все исключения наследники Exception
             блок catch полиморфен => можно указать любой радительский класс, но
             (!!!) здесь важен порядок, от меньшего к большему, т.е. если отловим Exception, то другие исключения не будут отловлены
             Если используется try/catch, то нужно обязательно отловить ВСЕ исключения, выбрасываемые опасным кодом, иначе компилятор будет ругаться
           */
        } catch (FileNotFoundException ex) {
            System.out.println("\nERROR: Файл " + fileName + " не найден. Для того чтобы детально разобраться в проблеме выводим трассировку стека.");
            ex.printStackTrace();
        }
          catch (Exception ex) {
              System.out.println("Здесь ловим все остальные, не понятные нам ошибки или чтобы компилятор не жаловался на то, что мы что-то не перехватываем. " +
                                  "А для того чтобы разобраться в проблеме выводим трассировку стека.\n");
              ex.printStackTrace();
        } finally { // блоб для освобождения используемых ресурсов
              System.out.println("Блок кода, который выполняется всегда, вне зависимости от того выкинул блок try исключение или нет");
              System.out.println("Если блоки try/catch содержат оператор return, то finally все равно будет выполнятся!!");
        }
        System.out.println("Блок за catch запускается, вне зависимости от того выкинул блок try исключение или нет, кроме случая, если блоки try/catch содержат оператор return");
    }

    /**
     * Пример повторного использования исключений или так называемый перехват событий.
     *
     * @param fileName
     * @throws EOFException
     */
    public static void readData_2(String fileName) throws Throwable {
        File file = new File(fileName);
        long len = 0;
        try {
            Scanner scanner = new Scanner(file); // это опасный код, который может выбросить исключение !!! если файло не найдено, то переходим на блок catch
            String stroka;
            while (scanner.hasNextLine()) {
                stroka = scanner.nextLine();  // пока есть строка читаем её
                len = len + stroka.length();  // считаем кол-во символов
                System.out.println("stroka = " + stroka);
            }
            scanner.close();
            if (len < 10000) {
                throw new EOFException("\nERROR: Файл слишком короткий");
            }

          /* Подменяем одно исключение другим. Данный способ полезен, когда методу никак нельзя генерировать проверяемые исключения, например, переопределяемый метод суперкласса
             вообще негенерирует проверяемых исключений => подкласс этого тоже делать не может. Д
             Чтобы обойти это ограничение, перехватываем проверяемое исключение и заключаем его в оболочку RuntimeException, которое делает его непроверяемым.

             Еще один usecase - это логирование исключения, например, logger.log(level, message, ex)
           */
        } catch (FileNotFoundException ex) {
            Throwable exception = new RuntimeException();
            exception.initCause(ex); // предыдущее исключение делаем источником для нового исключения, чтобы не потерять подробных сведений об исходном исключении
            throw exception;
          //  System.out.println("\nERROR: Файл " + fileName + " не найден. Для того чтобы детально разобраться в проблеме выводим трассировку стека.");
           // ex.printStackTrace();
        }

    }
}
