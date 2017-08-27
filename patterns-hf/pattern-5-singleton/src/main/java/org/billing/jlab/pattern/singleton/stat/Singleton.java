package org.billing.jlab.pattern.singleton.stat;

public class Singleton {

	/* Non Laizy вариант, т.е. инстанс создастся независимо от того, нужен он нам или нет.
	   Статические переменные инициализируются при первичной загрузке класса, а не при создании объекта класса
	   Первичная загрузка класса в память происходит, например, при первом обращении к классу, например к статическому методу getVar,
	   а это значит что проинициализируются все статические переменные класса, т.е. создастся инстаннс.
	   потоковая безопасность этого кода гарантирована
    */
	private static Singleton uniqueInstance = new Singleton();

	private static final String VAR = "Какая-то переменная";
 
	private Singleton() {
		System.out.println("Создание Singleton ...");
	}
 
	public static Singleton getInstance() {
		return uniqueInstance;
	}

	public static String getVar() {
		return VAR;
	}
}
