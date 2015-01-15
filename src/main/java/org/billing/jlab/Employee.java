package org.billing.jlab;

import java.util.*;

/**
 * Рассматриваются: <br />
 * - Перегрузка конструкторов <br />
 * - Вызов одного конструктора из другого <br />
 * - Применени конструкторов без аргументов <br />
 * - Применение блока инициализации <br />
 * - Применение статической инициализации <br />
 * - Инициализация полей экземпляра <br /> <br />
 * <p/>
 * Просто ссылка {@link java.util.Random#nextInt() ссылка_на_Random.nextInt()} в javadoc <br />
 * <a href="http://docs.oracle.com/javase/1.5.0/docs/tooldocs/solaris/javadoc.html#{@link}">Почитать про использование @link</a> <br />
 * <p/>
 * Раздел See Also: содержит ссылки на внешние док. или части этого же документа
 *
 * @author Создаёт раздел автора программы
 * @author Автор1
 * @author Автор2
 * @version 0.01
 * @see Employee#Employee(String, double, int, int, int)
 * @see <a href="www.horstmann.com/">Web страница книги</a>
 * @see "Не гиппертекстовая ссылка "
 */
public class Employee extends Person {
    private double salary;  // ЗП
    private Date hireDay;   // дата приема на работу
    int id;

    /* Глобальная переменная для всех объектов Employee.
    * Может служить глобальным счетчиком созданных объектов
    */
    private static int nextID;

    /* Статический блок инициализации полей класса. Выполняется при первичной загрузке класса (а не создании объекта класса).
     * Полезен, если нужен сложный код для инициализации
    */
    static {
        //nextID = 1;
        Random random = new Random();
        int nextInt = random.nextInt(1000);
    }

    /** Блок инициализации объекта
     * Выполняется первым, вслед за ним - тело конструктора
     * Этот механизм необязателен, и применяется не так часто
     */
    {
        id = nextID;
        nextID++;
    }

    /* Пустой конструктор по умолчанию есть у любого объекта. Если в классе нет конструкторов, он создается автоматически.
     * Иначе его нужно создавать явно, если, конечно, он нужен
     */
    public Employee() {
        // name инициализируется "???" в абстрактном классе Person
        // this.salary не устанавливается явно => инициализируется по умолчанию 0
        // this.hireDay не устанавливается явно => инициализируется по умолчанию null
        // this.id инициализируется в блоке инициализации
    }

    /**
     * @param name   Имя сотрудника
     * @param salary Зарплата
     * @param year   Год приёма на работу
     * @param month  Месяц приема на работу
     * @param day    День приема на работу
     */
    public Employee(String name, double salary, int year, int month, int day) {
        super(name);
        this.salary = salary;
        Calendar calendar = new GregorianCalendar(year, month - 1, day);
        hireDay = calendar.getTime();
    }

    public Employee(double salary) {
        this("Employee #" + nextID, salary, 1970, 1, 1);
    }

    @Override
    public String getDescription() {
        return String.format("Сотрудник с зарплатой $%.2f", salary);
    }

    public double getSalary() {
        return salary;
    }

    /**
     * @return Возвращает дату приёма сотрудника на работу
     */
    public Date getHireDay() {
        return hireDay;
    }

    public int getId() {
        return id;
    }

    public static int getNextID() {
        return nextID;
    }

    public void setID() {
        id = nextID;
        nextID++;
    }

    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) return false;

        /* В методе super.equals() уже проверяется, принадлежат ли ссылки this и employee одному и тому же классу */
        Employee employee = (Employee) o;

        if (Double.compare(employee.salary, salary) != 0) return false;
        if (!Objects.equals(hireDay, employee.hireDay)) return false;

        //if (hireDay != null ? !hireDay.equals(employee.hireDay) : employee.hireDay != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(salary);
        result = (int) (temp ^ (temp >>> 32));
        result = 31 * result + (hireDay != null ? hireDay.hashCode() : 0);
        //return result;
        return super.hashCode() + Objects.hash(this.hireDay, this.salary);
    }

    @Override
    public String toString() {
        return super.toString() + "Employee{" +
                "salary=" + salary +
                ", hireDay=" + hireDay +
                ", id=" + id +
                '}';
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
        Employee[] staff = new Employee[3];
        staff[0] = new Employee("Harry", 15000, 1975, 12, 3);
        staff[1] = new Employee(6000);
        staff[2] = new Employee();

        for (Employee e : staff) {
            System.out.println(e);
            //System.out.println("Name=" + e.getName() + "\tid=" + e.getId() + "\tSalary=" + e.getSalary() + "\thireDay=" + e.getHireDay());
            e.raiseSalary(10);
            System.out.printf("Name=%1$s; id=%2$d; Salary=%3$.2f; hireDay = %4$tB %4$te, %4$tY \n", e.getName(), e.getId(), e.getSalary(), e.getHireDay());
            System.out.println("=====================================================================");
        }

        // Используем коллекции
        Collection<Employee> collection = new ArrayList<>();
        collection.add(new Employee("Mike", 15000, 1975, 12, 3));
        collection.add(new Employee(6000));
        collection.add(new Employee());
        collection.add(new Employee("Mike_1", 77000, 1975, 12, 3));

        List<Employee> list = new ArrayList<>();
        boolean b = list.addAll(0, collection);
        list.add(0, new Employee("Mike_first", 15000, 1975, 12, 3));

        ListIterator<Employee> iter = list.listIterator();
        while (iter.hasNext()) {
            Employee e = iter.next();
            System.out.println("employeeList = " +e);
        }
        System.out.println("=====================================================================");

        // Работаем с коллекциями как с массивом
        Employee[] staff_out = collection.toArray(new Employee[0]);
        for (Employee employee : staff_out) {
            System.out.println("employee = " + employee);
        }


    }
}
