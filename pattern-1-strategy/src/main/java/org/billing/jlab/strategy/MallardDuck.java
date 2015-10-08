package org.billing.jlab.strategy;

public class MallardDuck extends Duck {
 
	public MallardDuck() {

		setQuackBehavior(new Quack());
		setFlyBehavior(new FlyWithWings());
	}

	public MallardDuck(FlyBehavior flyBehavior, QuackBehavior quackBehavior) {
		setFlyBehavior(flyBehavior);
		setQuackBehavior(quackBehavior);
	}

	public void display() {
		System.out.println("I'm a real Mallard duck");
	}
}
