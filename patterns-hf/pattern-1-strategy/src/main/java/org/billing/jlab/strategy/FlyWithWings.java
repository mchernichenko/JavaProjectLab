package org.billing.jlab.strategy;

/**
 *  Реализация поведения для уток, которые умеют летать
 */
public class FlyWithWings implements FlyBehavior {
	public void fly() {
		System.out.println("Я лечу!!");
	}
}
