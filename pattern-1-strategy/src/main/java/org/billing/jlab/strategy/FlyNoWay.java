package org.billing.jlab.strategy;

/**
 *  Реализация поведения для уток, которые не умеют летать
 */

public class FlyNoWay implements FlyBehavior {
	public void fly() {
		System.out.println("Я не могу летать =(");
	}
}
