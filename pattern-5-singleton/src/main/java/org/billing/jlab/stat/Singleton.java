package org.billing.jlab.stat;

public class Singleton {

	//  Статические переменные инициализируются при первичной загрузке класса (а не при создании объекта класса)
	// потоковая безопасность этого кода гарантирована
	private static Singleton uniqueInstance = new Singleton();
 
	private Singleton() {}
 
	public static Singleton getInstance() {
		return uniqueInstance;
	}
}
