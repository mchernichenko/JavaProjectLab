package org.billing.jlab.dcl;

/**
 *   Самая лучшая реализация паттерна-одиночка, но поддерживается только в Java 5 и выше
 *   volatile - гарантирует, что параллельные программные потоки будут правильно работать с переменной uniqueInstance при её инициализации экзампляром Singleton
 *
 *   (!) Когда переменная объявлена как volatile, любая запись её будет осуществляться прямо в память, минуя кеш.
 *   Также как и считываться будет прямо из памяти, а не из всевозможного кеша.
 *   Это значит, что все потоки будут "видеть" одно и то же значение переменной одновременно
 *
 *   Далее, если два потока прошли условие if (uniqueInstance == null), то только один поток сможет выполнить синхронизационный блок и установить переменнцю uniqueInstance
 *   Второй поток уже увидит "правильное" значение uniqueInstance и обломиться на внутренней проверке if (uniqueInstance == null)
 */

public class Singleton {
	private volatile static Singleton uniqueInstance;
 
	private Singleton() {}
 
	public static Singleton getInstance() {
		if (uniqueInstance == null) {    // синхронизация выполняется только при первом вызове => минимизируется использование синхронизации
			synchronized (Singleton.class) {
				if (uniqueInstance == null) {
					uniqueInstance = new Singleton();
				}
			}
		}
		return uniqueInstance;
	}
}
