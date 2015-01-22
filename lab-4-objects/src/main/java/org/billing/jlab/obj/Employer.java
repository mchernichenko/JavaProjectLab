package org.billing.jlab.obj;   // Класс должен быть соответствующем подкаталоге. Если пакет не указан, то берётся пакет по умолчанию - т.е. базовый каталог для исходников /src/main/java

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import static java.lang.Math.sqrt;

/**
 * Рассматриваются: <br />
 * - Пакеты <br />
 * - classpath : точки входа для поиска классов <br />
 * - Импорт, статический импорт <br />
 * - неизменяемые, статические поля, константы и ститические методы
 * - Перегрузка конструкторов <br />
 * - Вызов одного конструктора из другого <br />
 * - Применени конструкторов без аргументов <br />
 * - Применение блока инициализации <br />
 * - Применение статической инициализации <br />
 * - Инициализация полей экземпляра <br /> <br />

 Пакеты и classpath:
 Иерархия файловой системы должна отражать иерархию пакета Java, а classpath указывает, какие директории в файловой системе являются корневыми для иерархии пакета Java.
 Цель вложенных пакетов - гарантировать однозначность имен, т.к. полное имя класса состоит из имени пакета и имени класса.

 Пример компиляции: javac -d target\classes -sourcepath src\main\java -classpath C:\project1\classes;C:\project2\classes src\main\java\org\billing\jlab\obj\Employer.java
 ключ -d указывает, что откомпилированные файлы требуется положить в папку согласно package относительно target\classes\ (базовый каталог)
 При запуске .jar или .class поиск зависимых библиотек производится в базовых каталогах (точки входа для поиска классов):
 - указанных в файле MANIFEST
 - указанных в -classpath или переменной окружения CLASSPATH, но её лучше не использовать, чтобы избежать случайную загрузку неверного (неверной версии) класса!!!
 - в системных каталогах: jre/lib, jre/lib/ext, jre\lib\endorsed - в этих каталогах лучше ничего не размещать!!!
 JVM лезет в каждый jar базового каталога и ищет используемые классы по их полному имени.

 Импорт:
 - Полное имя класса состоит из имени пакета и имени класса. Импорт пакета или отдельного класса позволяет обращаться к классу только по имени.
 - Компилятор производит поиск классов в каждом из источников и если находит несколько одинаковых классов ругается.
 - Импорт на объем файла не влияет. Из других пакетов импортировать можно только public классы. Из текущего пакета импортироваться могут любые классы.
 - Cтатический импорт позволяет импортировать статические методы и поля

 Имя public класса и файла должно совпадать и класс может содержать только один public класс => компилятор легко определяет их местоположение

 * ****************************

 1. При создании методов доступа (геттеров), возвращающих ссылки на изменяемый объект нужно возвращать КЛОН объекта obj.clone(),
 иначе нарушение инкапсуляции! Например, объект имеет данные типа Date, получив геттером ссылку на этот объект можно изменить сам объект
 2. Метод имеет доступ к закрытым данным того объекта, для которого он вызывается, но также может обращаться к закрытым данным всех объектов своего класса
 например: name.equals(other.name)
 3. Неизменяемым называется такой класс, методы которого не позволяют изменить состояние объекта (состоит только из не изменяемых полей).
 Например класс String
 4. Статичекие методы применяются в 2-х случаях:
    - методу не требуется доступ к данным о состоянии объекта, т.к. все необходимые параметры задаются явно
    - методу требуется лишь доступ к статическим полям класса
 5. Каждый класс может содержать класс main(). C его помощью удобно организовывать блочное тестирование
 6. В Java всегда используется только вызов по значению (т.е. передаются копии).
    -  Методы не могут изменить параметры примитивных типов,
    -  Метод может изменить состояние объекта, передаваемое в качестве параметра
 7. Перегрузка: нельзя создать 2 метода, имеющих одинаковые имена и типы и отличающихся только типом возвращаемого значения
 8. Конструкторы: Если есть конструктор с параметрами, то конструктор без параметров, если он нужен, нужно определять явно

 9. Инициальзация: локальные переменные всегда должны явно инициализироваться в методе!!!
 10. Инициализация полей объекта:
    - в конструкторе
    - явная инициализация (при объявлении). Выполняется всегда до вызова конструктора. Полезно, кода требуется чтобы поле имело конкретное значение независимо от вызова конструктора класса
    - в блоке инициализации: выполняется первым, а вслед за ним тело конструктора
    - иначе автоматически присваивается (0, false, null)
 */

public class Employer // Имя public класса и файла должно совпадать и класс может содержать только один public класс => компилятор легко определяет их местоположение
{
    private String name;
    private double salary;
    private Date hireDay;
    private int id = Employer.nextId;  // явная инициализация

    /* Неизменяемое поле экземпляра после создания объекта. */
    private final String finalName; // Удобно применять для полей простых типов или полей, типы которых задаются неизменяемыми классами.

    /* Статическое поле. */
    private static int nextId = 1; // Может служить глобальным счетчиком созданных объектов. Существует в одном экземпяре для всех объектов Employee.

    private static int randomId;

    /* Константа */
    public static final double sqrt2 = sqrt(2);

    /* Статический блок инициализации полей класса.
    Выполняется при первичной загрузке класса (а не при создании объекта класса). Полезен, если нужен сложный код для инициализации */
    static {
        Random random = new Random();
        randomId = random.nextInt(1000);
    }

    /* Блок инициализации полей класса.
     * Выполняется первым, вслед за ним - тело конструктора. Этот механизм необязателен, и применяется не так часто */ {
        finalName = "Employer_" + Employer.nextId; // глобальное имя объекта
        Employer.nextId++;                        // при создании объекта увеличиваем счетчик
    }

    public Employer(String name, int salary, String hireDay) {
        this.name = name;
        this.salary = salary;

        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        try {
            this.hireDay = formatter.parse(hireDay);
        } catch (ParseException e) {
        }
    }

    /* Пустой конструктор по умолчанию есть у любого объекта. Если в классе нет конструкторов, он создается автоматически.
     * Иначе его нужно создавать явно, если, конечно, он нужен
     */
    public Employer() {
        // name инициализируется "???" в абстрактном классе Person
        // this.salary не устанавливается явно => инициализируется по умолчанию 0
        // this.hireDay не устанавливается явно => инициализируется по умолчанию null
        // this.id инициализируется в блоке инициализации
    }

    public Date getHireDay() {
        // return (Date) hireDay.clone(); // важно вернуть именно клон объекта, иначе нарушение инкапсуляции
        return hireDay;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public void setld() {
        this.id = Employer.nextId;
        Employer.nextId++;
    }

    public static int getNextId() {
        return nextId;
    }

    public String getFinalName() {
        return finalName;
    }

    public int getId() {
        return id;
    }

    /**
     * Увеличивает ЗП сотрудников на %
     *
     * @param byPercent % увеличения ЗП (например, 10 = 10%)
     * @return Величина, на которую повышается ЗП
     */
    public double raiseSalary(double byPercent) {
        double raise = salary * byPercent / 100;
        this.salary += raise;
        return raise;
    }

    /* блочный тест */
    public static void main(String[] args) {
        Date date = null;
        Employer[] staff = new Employer[3];
        staff[0] = new Employer("Harry", 15000, "15.12.1987");
        staff[1] = new Employer("Имя работничка_1", 15000, "15.12.1997");
        staff[2] = new Employer();

        for (Employer e : staff) {
            System.out.println(e);
            //System.out.println("Name=" + e.getName() + "\tid=" + e.getId() + "\tSalary=" + e.getSalary() + "\thireDay=" + e.getHireDay());
            e.raiseSalary(10);
            date = e.getHireDay();
            System.out.println(e.getFinalName());
            System.out.printf("Name=%1$s; id=%2$d; Salary=%3$.2f; hireDay = %4$tB %4$te, %4$tY \n", e.getName(), e.getId(), e.getSalary(), date);
            System.out.println("=====================================================================");
        }
    }
}
