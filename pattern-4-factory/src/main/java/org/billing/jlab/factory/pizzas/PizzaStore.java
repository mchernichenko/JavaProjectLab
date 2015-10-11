package org.billing.jlab.factory.pizzas;

/**
 * Клиент фабрики.
 * Класс который способен определять какую пиццу он будет готовить и далее её готовить
 * PazzaStore сохраняет ссылку на фабрику в конструкторе, далее, orderPizza обращается к фабрике
 * с запросом на создание объекта, передавая тип заказа.
 */
public class PizzaStore {

	SimplePizzaFactory factory; // класс, имеющий фабричный метод
 
	public PizzaStore(SimplePizzaFactory factory) { 
		this.factory = factory;
	}

	/**
	 * Метод приготовления пиццы
	 * @param type - тип приготовляемой пиццы: (cheese, pepperoni, clam, veggie)
	 * @return экземпляр объекта Pizza, который приготовили
	 */
	public Pizza orderPizza(String type) {
		Pizza pizza;
 
		pizza = factory.createPizza(type);
 
		pizza.prepare();
		pizza.bake();
		pizza.cut();
		pizza.box();

		return pizza;
	}

}
