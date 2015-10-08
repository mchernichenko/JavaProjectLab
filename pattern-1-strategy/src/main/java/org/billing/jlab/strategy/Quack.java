package org.billing.jlab.strategy;

/**
 *  Реализация поведения для уток, которые  умеют крякать
 */
public class Quack implements QuackBehavior {
	public void quack() {
		System.out.println("Quack");
	}
}
