package org.billing.jlab.strategy;

/**
 *  Реализация поведения для уток, которые  умеют летать очень быстро
 */
public class FlyRocketPowered implements FlyBehavior {
	public void fly() {
		System.out.println("Я летаю очень быстро");
	}
}
