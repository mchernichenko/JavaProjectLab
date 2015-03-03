package org.billing.jlab.reflection;

import org.billing.jlab.oop.Employee;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Scanner;

/**
 * Применение рефлексии для вывода всех компонентов класса.
 *
 *  <img src="../../../../resources/java.lang.Class и прочие.png" alt="API по анализу классов" />
 *
 * <a href="http://www.javable.com/tutorials/fesunov/lesson22/">Еще примеры</a>
 * <a href="http://www.quizful.net/post/java-reflection-api">Введение в Java Reflection API</a>
 *
 * Полное имя класса указываем во входном параметре, но можно нарваться на ошибку, что класс не найден, хотя classpath указан верно
 * Это происходит из-за того, что динамический загрузчик этот класс не загружает. Нужно положить временно jar в "c:\Program Files\Java\jdk1.7.0_72\jre\lib\ext\"
 * или указать путь для Extension ClassLoader: java -Djava.ext.dirs=c:\tmp2 -jar prog.jar
 * См. о динамической загрузке классов: http://media.techtarget.com/tss/static/articles/content/dm_classForname/DynLoad.pdf
 */
public class ReflectionTest
{
    public static void main( String[] args ) throws ClassNotFoundException {

        // извлекаем имя класса из аргументов командной строки или введённых пользователем данных
        String name;
        if (args.length > 0) name = args[0];
        else {
            Scanner in = new Scanner(System.in);
            System.out.println("\n (!!!) ВНИМАНИЕ: в случае ClassNotFoundException требуется указать путь к Extension ClassLoader и скопировать туды jar с требуемым классом:\n java -Djava.ext.dirs=jre\\lib\\ext;c:\\tmp2 -jar ReflectionTest.jar org.billing.jlab.inner.TalkingClock$TimePrinter\n");
            System.out.println("Введите имя класса (например: java.util.Date): ");
            System.out.println("или 'def' для класса по умолчанию (org.billing.jlab.inner.TalkingClock$TimePrinter)");
            name = in.next();
        }

        // Вывести описание класса
        if (name.equals("def")) name = "org.billing.jlab.inner.TalkingClock$TimePrinter";
        printClass(name);

    }

    /**
     * Выводит полное описание класса по его текстовому представления.
     * Выводит полное имя класса, его суперкласс, кроме Object, а также все его конструкторы, поля и методы.
     * @param name - полное имя класса, например, java.util.Date
     */
    public static void printClass(String name) throws ClassNotFoundException {
        // вывести имя класса и суперкласс, кроме Object
        // объект типа Class можно получить по его строковому предствлению, включая имя пакета!
        //Class<?> aClass = Class.forName(name);

        Thread thread = Thread.currentThread();
        ClassLoader cl = thread.getContextClassLoader();
        Class<?> aClass = cl.loadClass(name);

        System.out.println("==== Полное имя класса и его суперкласс ====");
        System.out.println(" ");

        Class<?> superclass = aClass.getSuperclass(); // нахождение суперкласса, он возможет только один, т.к. множественного наследования нет! Остальные суперклассы можно получить только рекурсивно вызывать метод getSuperclass().
        String modifiers = Modifier.toString(aClass.getModifiers()); // определение модификаторов класса, например public final synchronized strictfp. getModifiers возвращает int, биты которого представляют модификаторы класса
        if (modifiers.length() > 0) System.out.print(modifiers + " ");
        System.out.print("class " + name);
        if (superclass != null && superclass != Object.class) {
            System.out.print(" extents " + superclass.getName());
        }

        Class<?>[] interfaces = aClass.getInterfaces();  // интерфейсы реализованные в заданном классе
        if (interfaces.length > 0) System.out.print(" implements");
        for (int i = 0; i < interfaces.length; i++) {
            if (i > 0) System.out.print(", ");
            System.out.print(interfaces[i].getName());
        }
        System.out.println(";");

        System.out.println("{");
        printConstructors(aClass);
        System.out.println();
        printFields(aClass);
        System.out.println();
        printMethods(aClass);
        System.out.println("}");

    }

    /**
     * Вывод всех конструкторов класса и типы его параметров
     * @param cl - класс
     */
    public static void printConstructors(Class cl) {
        Constructor[] constructors = cl.getDeclaredConstructors();
        System.out.println("===== Конструкторы ======");
        System.out.println(" ");

        for (Constructor constructor : constructors) {
            String name = constructor.getName();
            String modifiers = Modifier.toString(constructor.getModifiers());// получение модификатора конструктора

            // выводим модификатор и конструктор
            if (modifiers.length() > 0) System.out.print(modifiers + " ");
            System.out.print(name + "(");

            // выводим типы параметров конструкторов
            Class[] parameterTypes = constructor.getParameterTypes();
            for (int j = 0; j < parameterTypes.length; j++) {
                if (j > 0)  System.out.print(", ");
                System.out.print(parameterTypes[j].getName());
            }
            System.out.println(");");
        }
    }

    /**
     * Вывод всех методов класса и их типов
     * @param cl
     */
    public static void printMethods(Class cl) {
        System.out.println("===== Методы =====");
        System.out.println(" ");

        Method[] methods = cl.getDeclaredMethods();
        for (Method method : methods) {
            Class<?> returnType = method.getReturnType();
            String name = method.getName();

            // вывести модификатор, тип, имя метода
            String modifiers = Modifier.toString(method.getModifiers());
            if (modifiers.length() > 0) System.out.print(modifiers + " ");
            System.out.print(returnType.getName() + " " + name + "(");

            // вывести типы параметров метода
            Class<?>[] parameterTypes = method.getParameterTypes();
            for (int j = 0; j < parameterTypes.length; j++) {
                if (j > 0) System.out.print(", ");
                System.out.print(parameterTypes[j].getName());
            }
            System.out.println(");");
        }
    }

    /**
     * Вывод всех полей класса cl
     * @param cl - класс
     */
    public static void printFields(Class cl) {

        System.out.println("===== Поля определённые в классе, включая суперклассы и интерфейсы этого класса =====");
        Field[] fields = cl.getDeclaredFields(); // получить все поля, даже private

        for (Field field : fields) {
            Class<?> type = field.getType();
            String name = field.getName();
            System.out.println(" ");
            String modifiers = Modifier.toString(field.getModifiers());
            if (modifiers.length() > 0) System.out.print(modifiers + " ");
            System.out.print(type.getName() + " " + name + ";");
        }
        System.out.println();
    }
}
