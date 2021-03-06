package org.billing.jlab.starbuzz;
 
public class Whip extends CondimentDecorator {
	Beverage beverage;

	/**
	 * К напитку beverage добавляем взбитые сливки
	 * @param beverage
	 */
	public Whip(Beverage beverage) {
		this.beverage = beverage;
	}
 
	public String getDescription() {
		return beverage.getDescription() + ", Whip";
	}
 
	public double cost() {
		return .10 + beverage.cost();
	}
}
