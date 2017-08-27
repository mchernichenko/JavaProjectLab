package org.billing.jlab.pattern.singleton.statlaizy;

public class SingletonClient {
	public static void main(String[] args) {

		System.out.println(Singleton.getVar());
		Singleton singleton = Singleton.getInstance();
	}
}
