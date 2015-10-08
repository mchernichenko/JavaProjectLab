package org.billing.jlab.strategy;

/**
 *  Реализация поведения для уток, которые  умеют пищать
 */

public class Squeak implements QuackBehavior {
	public void quack() {
		System.out.println("Кряякать не умею, только пищать");
	}
}
