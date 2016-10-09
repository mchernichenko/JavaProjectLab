package org.billing.jlab.strategy;

/**
 *  Реализация поведения для уток, которые крякают тихо
 */
public class MuteQuack implements QuackBehavior {
	public void quack() {
		System.out.println("<< Silence >>");
	}
}
