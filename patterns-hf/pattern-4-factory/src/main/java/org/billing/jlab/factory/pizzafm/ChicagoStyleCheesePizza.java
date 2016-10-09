package org.billing.jlab.factory.pizzafm;

/**
 * Чикагская пицца использует томатный соус и отовится на толстой основе
 */
public class ChicagoStyleCheesePizza extends Pizza {

	public ChicagoStyleCheesePizza() { 
		name = "Chicago Style Deep Dish Cheese Pizza";
		dough = "Extra Thick Crust Dough";
		sauce = "Plum Tomato Sauce";
 
		toppings.add("Добавка сыр 'моцарелла'");
	}
 
	void cut() {
		System.out.println("Нарезается на клиньями, а квадратами");
	}
}
