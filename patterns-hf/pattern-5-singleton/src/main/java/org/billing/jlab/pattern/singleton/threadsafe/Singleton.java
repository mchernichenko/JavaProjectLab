package org.billing.jlab.pattern.singleton.threadsafe;

public class Singleton {
	private static Singleton uniqueInstance;
 
	// other useful instance variables here
 
	private Singleton() {}

	// все потоки, которые будут обращаться за получение интанса класса будут выстраиваться в очередь, что ужасно, т.к. он уже создан
	// и его требуется только вернуть
	public static synchronized Singleton getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new Singleton();
		}
		return uniqueInstance;
	}
 
	// other useful methods here
}
