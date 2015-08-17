package org.billing.jlab.collections;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;

/**
 * Описание к public классу<br />
 * Javadoc извлекает сведения следующих компонент программы <br />
 * - из пакетов<br />
 * - из public классов и интерфейсов<br />
 * - из public и private методов<br />
 * - из public и private полей<br />
 * Можно использовать HTML разметку для выделения текста <em>КУРСИВОМ</em>, для формирования текста <code>МОНОШИРИННОГО ШРИФТА</code>, для
 * выделения текста <strong>ПОЛУЖИРНЫМ</strong> и даже для вставки рисунков <img src="../../../resources/uml.png" alt="UML диаграмма!!!"/>
 * @author MCH
 * @version 0.01
 */
public class App
{
    public static void main( String[] args ) throws IOException {
        // определяем кодировку консоли. В Windows по дефолту это Ср866.
        //setConsoleEncodeCp866();

        // список доступных системных свойств
        //getListSystemProperties();


        // список доступных кодировок
        //getListEncode();

   /*     String fileName = "c:\\Users\\Mikhail.Chernichenko\\Documents\\Dropbox\\IdeaProjects\\input1.txt";

        FileReader fileReader = new FileReader(fileName);*/
/*
        FileInputStream fileInputStream = new FileInputStream(fileReader);
*/

        File file = new File("input.txt");
        Scanner scanner = new Scanner(file, "Cp866");
        //scanner.useDelimiter("\n");
        String stroka;
        while (scanner.hasNextLine()) {
            stroka = scanner.nextLine();
            System.out.println("stroka = " + stroka);

        }
        scanner.close();


//      Разобрать строку по буквам
/*        String str = "\uD835\uDD0A" + "𤴊Hello World";
        String str1 = getSpellingString(str);
        System.out.println(str);
        System.out.println(str1);*/

//       загрузка данных из файла и получение отсортированные строки в массиве
        FileReader in = new FileReader("input.txt");
        String[] array = loadAndSort(in);
        for (String list : array) {
            System.out.println(list);
        }

        System.out.println("======workWithCalendar===========");
        MyCalendar.my_calendarik();

        System.out.println("======testEmployee===========");
        //testEmployee();
        testPerson();

    }

    /**
     * Список доступных кодировок
     */
    public static void getListEncode() {
        Map<String, Charset> charsets = Charset.availableCharsets();
        Iterator<Charset> iterator = charsets.values().iterator();
        while (iterator.hasNext()) {
            Charset cs = (Charset) iterator.next();
            System.out.println(cs.displayName() + "\t" + cs.name());
        }
    }

    /**
     * Установка кодировки консольного окна в Ср866, которая является дефолтной для Windows
     */
    public static void setConsoleEncodeCp866() {
        try {
            String consoleEnc = System.getProperty("console.encoding", "Cp866");
            System.out.println("consoleEnc = " + consoleEnc);
            System.setOut(new PrintStream(System.out, true, consoleEnc));
            System.setErr(new PrintStream(System.err, true , consoleEnc));
        } catch (UnsupportedEncodingException e) {
            System.out.println("e = " + e);
        }
    }


    /**
     * Список доступных системных свойств
    */
    public static void  getListSystemProperties() {
        System.out.println("===== Список доступных системных свойств =======");
        for (Enumeration<?> enumeration = System.getProperties().propertyNames(); enumeration.hasMoreElements();) {
            String s1 = enumeration.nextElement().toString();
            System.out.println(s1+"="+System.getProperty(s1));
        }
    }

    /**
     * Перебор строки по буквам
     */
    private static String getSpellingString(String str) {
        String str1 = "";
        for (int j = 0; j < str.length(); j++) {
            int cp = str.codePointAt(j);
            // если кодовое зачение Unicode из дополнительного диапазона, то он представляется двумя кодовыми точками
            if (Character.isSupplementaryCodePoint(cp)) {
                /* выводим сурогантую пару, т.к. символьного представления кодовой точки из зарезервированного диапазова нет, выдаст "?" */
                str1 = str1 + "\"" + str.substring(j, j + 2) + "\" ";
                j += 1;
            } else {
                str1 = str1 + "\"" + str.substring(j, j + 1) + "\" ";
            }
        }
        return str1;
    }

    /**
     * Сортировко строк входного потока (файла). Отсортированные строки возвращаем в массиве
    */
    public static String[] loadAndSort(Reader in) throws IOException {
        BufferedReader reader = new BufferedReader(in);
        List<String> lists = new ArrayList<String>();
        String line;
        while ((line = reader.readLine()) != null) {
            if (line.isEmpty()) {
                continue;
            }
            lists.add(line);

        }
        reader.close();
        String[] array = lists.toArray(new String[lists.size()]);
        Arrays.sort(array);
        return array;
    }

    public static void testEmployee() {
        Employee[] staff = new Employee[3];

        Manager manager = new Manager("Манагер", 100000, 1980, 12, 12);
        manager.setBonus(1000);

        staff[0] = manager;
        staff[1] = new Employee("Name2", 76000, 1976, 11, 13);
        staff[2] = new Employee("Name3", 77000, 1976, 11, 13);


        for (Employee employee : staff) {
            employee.raiseSalary(5);
        }

        for (Employee employee : staff) {
            System.out.println("Name = " + employee.getName() + ", salary=" + employee.getSalary() + ", hireDay" + employee.getHireDay());
        }
    }

    public static void testPerson() {
        Person[] pers = new Person[10];

        pers[0] = new Employee("Employee1", 76000, 1976, 11, 13);
        pers[1] = pers[0];
        pers[2] = new Employee("Employee1", 76000, 1976, 11, 13);
        pers[3] = new Employee("Employee2", 80000, 1976, 11, 13);

        pers[4] = new Manager("Manager1", 100000, 1980, 11, 14);
        Manager boss = new Manager("Manager1", 100000, 1980, 11, 14);
        pers[5] = boss;
      //  boss.setBonus(5000);

        pers[6] = new Student("Student1", "Math");
        pers[7] = new Student("Student2", "Math");

        for (Person per : pers) {
            System.out.println(per);
        }

        System.out.println("pers[0] = pers[1]: " + (pers[0] == pers[2]));
        System.out.println("pers[0].equals(pers[1]: " + (pers[0].equals(pers[2])));
        System.out.println("pers[0].equals(pers[4]: " + (pers[0].equals(pers[4])));
        System.out.println("pers[4].equals(pers[5]: " + (pers[4].equals(pers[5])));

        System.out.println("pers[0].hashCode(): " + pers[0].hashCode());
        System.out.println("pers[1].hashCode(): " + pers[1].hashCode());
        System.out.println("pers[2].hashCode(): " + pers[2].hashCode());
        System.out.println("pers[4].hashCode(): " + pers[4].hashCode());
        System.out.println("pers[5].hashCode(): " + pers[5].hashCode());

    }
}
