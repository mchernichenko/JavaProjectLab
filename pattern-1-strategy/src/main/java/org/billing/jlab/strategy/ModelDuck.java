package org.billing.jlab.strategy;

public class ModelDuck extends Duck {
	public ModelDuck() {
		setFlyBehavior(new FlyNoWay());
		setQuackBehavior(new Quack());
	}

	public ModelDuck(FlyBehavior flyBehavior, QuackBehavior quackBehavior) {
		setFlyBehavior(flyBehavior);
		setQuackBehavior(quackBehavior);
	}

	public void display() {
		System.out.println("I'm a model duck");
	}
}
