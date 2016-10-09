package org.billing.jlab.factory.pizzaaf;

/**
 * Абстрактная фабрика для создания объектов-атрибутов: Dough, Sauce, Veggies, Cheese, Pepperoni, Clams
 * для Pizza
 */

public interface PizzaIngredientFactory {
 
	public Dough createDough();
	public Sauce createSauce();
	public Cheese createCheese();
	public Veggies[] createVeggies();
	public Pepperoni createPepperoni();
	public Clams createClam();
 
}
