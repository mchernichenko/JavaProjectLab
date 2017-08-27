package org.billing.jlab.pattern.singleton.statlaizy;

public class Singleton {
    /*
	 Laizy вариант, т.е. инстанс создастся только при вызове getInstance.
	 Тут все аналогично Non Laizy варианту, но финт ушам тут следующий:
	 убрав статическую переменную uniqueInstance во внутренний класс, класс Singleton может попрежнему загружаться в память
	 для обращения к статическим переменным класса, но без создания его инстанса!!
	 в этом варианте вообще не нужно заботиться о синхронизации. Все потоки, которые зайдут в getInstance будут ждать загрузчика.
	 ClassLoader начнёт загружать в память класс SingletonInstanceHolder и загрузка закончится только лишь тогда, когда все статические переменные
	 будут проинициализированны. Т.е. всю проблематику синхронизаци делегируетм класслоадеру, а он работает одинаково вне зависимости от JVM
    */
	private static final String VAR = "Какая-то переменная";
 
	private Singleton() {
		System.out.println("Создание Singleton ...");
	}
 
	public static Singleton getInstance() {
		return Singleton.SingletonInstanceHolder.uniqueInstance;
	}

	public static String getVar() {
		return VAR;
	}

	// внутренний класс
	public static class SingletonInstanceHolder {
		private static Singleton uniqueInstance = new Singleton();

		private SingletonInstanceHolder() {}

	}
}
