package org.billing.jlab.subclass;

/**
 * Субклассирование синглетного класса.
 */
public class SingletonTestDrive {
	public static void main(String[] args) {
		Singleton foo = CoolerSingleton.getInstance();
		Singleton bar = HotterSingleton.getInstance();
		System.out.println(foo);
		System.out.println(bar);
		if (foo == bar) {
			System.out.println("Экземпляры равны");
		}
 	}
}
