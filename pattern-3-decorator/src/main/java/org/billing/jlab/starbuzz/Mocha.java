package org.billing.jlab.starbuzz;

/**
 * Шоколадная добавка
 */
public class Mocha extends CondimentDecorator {
	Beverage beverage;

	/**
	 * Добавляем шоколадную добавку
	 * @param beverage
	 */
	public Mocha(Beverage beverage) {
		this.beverage = beverage;
	}
 
	public String getDescription() {
		return beverage.getDescription() + ", Mocha";
	}
 
	public double cost() {
		return .20 + beverage.cost();
	}
}
