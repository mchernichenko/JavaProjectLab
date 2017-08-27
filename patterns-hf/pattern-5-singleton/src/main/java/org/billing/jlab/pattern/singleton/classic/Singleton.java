package org.billing.jlab.pattern.singleton.classic;

/**
 *  NOTE: This is not thread safe!
 *  Если этот код выполняется в разных потоках, то в условие if (uniqueInstance == null) можно войти одновременнно в разных потоках
 *
 */

public class Singleton {
	private static Singleton uniqueInstance;
 
	// other useful instance variables here
 
	private Singleton() {}
 
	public static Singleton getInstance() {
		if (uniqueInstance == null) {
			uniqueInstance = new Singleton();
		}
		return uniqueInstance;
	}
 
	// other useful methods here
}
