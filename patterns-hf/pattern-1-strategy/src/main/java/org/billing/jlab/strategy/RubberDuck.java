package org.billing.jlab.strategy;

public class RubberDuck extends Duck {
 
	public RubberDuck() {
		setFlyBehavior(new FlyNoWay());
		setQuackBehavior(new Squeak());
	}

	public RubberDuck(FlyBehavior flyBehavior, QuackBehavior quackBehavior) {
		setFlyBehavior(flyBehavior);
		setQuackBehavior(quackBehavior);
	}

	public void display() {
		System.out.println("I'm a rubber duckie");
	}
}
