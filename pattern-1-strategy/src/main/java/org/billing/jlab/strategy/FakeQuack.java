package org.billing.jlab.strategy;

/**
 *  Реализация поведения для уток, которые  не умеют крякать
 */
public class FakeQuack implements QuackBehavior {
	public void quack() {
		System.out.println("Я не умею крякать");
	}
}
