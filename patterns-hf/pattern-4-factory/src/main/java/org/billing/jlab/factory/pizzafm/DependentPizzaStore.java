package org.billing.jlab.factory.pizzafm;

/**
 * Фабрика создания пицц
 */
public class DependentPizzaStore extends PizzaStoreNew {
	Pizza pizza = null;

	/**
	 * @param style - стиль пиццы: 'NY', 'Chicago'
	 * @param type - тип пиццы: 'cheese', 'pepperoni', 'clam', 'veggie'
	 * @return
	 */
	public Pizza createPizzaNew(String style, String type) {

		if (style.equals("NY")) {
			if (type.equals("cheese")) {
				pizza = new NYStyleCheesePizza();
			} else if (type.equals("veggie")) {
				pizza = new NYStyleVeggiePizza();
			} else if (type.equals("clam")) {
				pizza = new NYStyleClamPizza();
			} else if (type.equals("pepperoni")) {
				pizza = new NYStylePepperoniPizza();
			}
		} else if (style.equals("Chicago")) {
			if (type.equals("cheese")) {
				pizza = new ChicagoStyleCheesePizza();
			} else if (type.equals("veggie")) {
				pizza = new ChicagoStyleVeggiePizza();
			} else if (type.equals("clam")) {
				pizza = new ChicagoStyleClamPizza();
			} else if (type.equals("pepperoni")) {
				pizza = new ChicagoStylePepperoniPizza();
			}
		} else {
			System.out.println("Error: Неверный тип пиццы. Выберите из: cheese, pepperoni, clam, veggie");
			return null;
		}
		pizza.prepare();
		pizza.bake();
		pizza.cut();
		pizza.box();
		return pizza;
	}
}
