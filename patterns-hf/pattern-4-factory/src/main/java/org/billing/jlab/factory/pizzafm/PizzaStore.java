package org.billing.jlab.factory.pizzafm;

public abstract class PizzaStore {
 
	abstract Pizza createPizza(String type);

	/**
	 * Метод приготовления пиццы
	 * @param type
	 * @return
	 */
	public Pizza orderPizza(String type) {
		Pizza pizza = createPizza(type);
		System.out.println("--- Готовим " + pizza.getName() + " ---");
		pizza.prepare();
		pizza.bake();
		pizza.cut();
		pizza.box();
		return pizza;
	}
}
