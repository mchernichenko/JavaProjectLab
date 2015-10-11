package org.billing.jlab.factory.pizzas;

/**
 * Пример простой фабрики.
 * Это класс представляющий поведение, а именно, алгоритм создания конкретного объекта
 * Основан на композиции, т.е. этому классу делегируется создание объектов
 * Не является полноценным шаблоном, обеспечивает простой механизм изоляции клиентов от конкретных классов.
 *
 */
public class SimplePizzaFactory {
	Pizza pizza = null;

	/**
	 * Фабричный метод создания экземпляра объекта типа Pizza
	 * @param type - наименование создаваемой пиццы: (cheese, pepperoni, clam, veggie)
	 * @return объект типа Pizza
	 */
	public Pizza createPizza(String type) {

		if (type.equals("cheese")) {
			pizza = new CheesePizza();
		} else if (type.equals("pepperoni")) {
			pizza = new PepperoniPizza();
		} else if (type.equals("clam")) {
			pizza = new ClamPizza();
		} else if (type.equals("veggie")) {
			pizza = new VeggiePizza();
		}
		return pizza;
	}
}
