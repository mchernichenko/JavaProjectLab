package org.billing.jlab.strategy;

public abstract class Duck {

	// все утки имеют поведение, т.е. могут как-то летать и крякать, только конкретное поведение
	// определяется конкретным классом поведения
	private FlyBehavior flyBehavior;
	private QuackBehavior quackBehavior;
 
	public Duck() {
	}
 
	public void setFlyBehavior (FlyBehavior fb) {
		flyBehavior = fb;
	}
 
	public void setQuackBehavior(QuackBehavior qb) {
		quackBehavior = qb;
	}
 
	abstract void display();

	/**
	 * Функция лететь, а вот как саму операцию лететь делегируем классу поведения
	 */
	public void performFly() {
		flyBehavior.fly();
	}

	/**
	 * Функция крякать, а вот как саму операцию крякания делегируем классу поведения
	 */
	public void performQuack() {
		quackBehavior.quack();
	}
 
	public void swim() {
		System.out.println("Утки все плавают!");
	}
}
