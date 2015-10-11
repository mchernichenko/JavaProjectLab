package org.billing.jlab.factory.pizzafm;

/**
 * Нью-йокркская пица готовится с соусом Маринара на тонкой основе
 */
public class NYStyleCheesePizza extends Pizza {

	public NYStyleCheesePizza() { 
		name = "NY Style Sauce and Cheese Pizza";
		dough = "Thin Crust Dough";
		sauce = "Marinara Sauce";
 
		toppings.add("Добавка сыр 'реджано'");
	}
}
