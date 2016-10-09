package org.billing.jlab.factory.pizzas;

public class PizzaTestDrive {
 
	public static void main(String[] args) {
		SimplePizzaFactory factory = new SimplePizzaFactory();

		/* передаем алгоритм создания объектов (т.е. фабрику)
		   Нечто похожее на стратегию, когда объекту Duck передавали алгоритмы крякания и летания
		   Т.е. простая фабрика инкапсулирует создание объектов
		 */
		PizzaStore store = new PizzaStore(factory);

		Pizza pizza = store.orderPizza("cheese");
		System.out.println("Мы приготовили: " + pizza.getName() + "\n");
 
		pizza = store.orderPizza("veggie");
		System.out.println("Мы приготовили: " + pizza.getName() + "\n");
	}
}
