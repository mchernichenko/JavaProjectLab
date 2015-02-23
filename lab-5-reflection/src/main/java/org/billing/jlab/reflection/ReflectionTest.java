package org.billing.jlab.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Scanner;

/**
 * Примерение рефлексии для вывода всех компонентов класса.
 *
 *  <img src="../../../../resources/java.lang.Class и прочие.png" alt="API по анализу классов" />
 *
 * <a href="http://www.javable.com/tutorials/fesunov/lesson22/">Еще примеры</a>
 * <a href="http://www.quizful.net/post/java-reflection-api">Введение в Java Reflection API</a>
 *
 */
public class ReflectionTest
{
    public static void main( String[] args ) {

        // извлекаем имя класса из аргументов командной строки или введённых пользователем данных
        String name;
        if (args.length > 0) name = args[0];
        else {
            Scanner in = new Scanner(System.in);
            System.out.println("Введите имя класса (например: java.util.Date): ");
            name = in.next();
        }

        // Вывести описание класса name
        printClass(name);

    }

    /**
     * Выводит полное описание класса по его текстовому представления.
     * Выводит полное имя класса, его суперкласс, кроме Object, а также все его конструкторы, поля и методы.
     * @param name - полное имя класса, например, java.util.Date
     */
    public static void printClass(String name) {
        // вывести имя класса и суперкласс, кроме Object
        try {
            // объект типа Class можно получить по его строковому предствлению, включая имя пакета!
            Class<?> aClass = Class.forName(name);

            System.out.println("==== Полное имя класса и его суперкласс ====");
            System.out.println(" ");

            Class<?> superclass = aClass.getSuperclass();
            String modifiers = Modifier.toString(aClass.getModifiers());
            if (modifiers.length() > 0) System.out.print(modifiers + " ");
            System.out.print("class " + name);
            if (superclass != null && superclass != Object.class) {
                System.out.println(" extents " + superclass.getName());
            }

            System.out.println("{");
            printConstructors(aClass);
            System.out.println();
            printMethods(aClass);
            System.out.println();
            printFields(aClass);
            System.out.println("}");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
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

        System.out.println("===== Поля класса =====");
        Field[] fields = cl.getFields();

        for (Field field : fields) {
            Class<?> type = field.getType();
            String name = field.getName();
            System.out.println(" ");
            String modifiers = Modifier.toString(field.getModifiers());
            if (modifiers.length() > 0) System.out.print(modifiers + " ");
            System.out.print(type.getName() + " " + name + ";");
        }
    }
}
