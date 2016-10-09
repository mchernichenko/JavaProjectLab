package org.billing.jlab.strategy;

public class RedHeadDuck extends Duck {
 
	public RedHeadDuck() {
		setFlyBehavior(new FlyWithWings());
		setQuackBehavior(new Quack());
	}

	public RedHeadDuck(FlyBehavior flyBehavior, QuackBehavior quackBehavior) {
		setFlyBehavior(flyBehavior);
		setQuackBehavior(quackBehavior);
	}
	public void display() {
		System.out.println("I'm a real Red Headed duck");
	}
}
