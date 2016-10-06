package org.billing.jlab.factory.pizzaaf;

public class PizzaTestDrive {
 
	public static void main(String[] args) {
		PizzaStore nyStore = new NYPizzaStore();
		PizzaStore chicagoStore = new ChicagoPizzaStore();
 
		Pizza pizza = nyStore.orderPizza("cheese");
		System.out.println("1-й чел. заказал " + pizza + "\n");
 
		pizza = chicagoStore.orderPizza("cheese");
		System.out.println("2-й чел. заказал " + pizza + "\n");

		pizza = nyStore.orderPizza("clam");
		System.out.println("1-й чел. заказал " + pizza + "\n");
 
		pizza = chicagoStore.orderPizza("clam");
		System.out.println("2-й чел. заказал " + pizza + "\n");

		pizza = nyStore.orderPizza("pepperoni");
		System.out.println("1-й чел. заказал " + pizza + "\n");
 
		pizza = chicagoStore.orderPizza("pepperoni");
		System.out.println("2-й чел. заказал " + pizza + "\n");

		pizza = nyStore.orderPizza("veggie");
		System.out.println("1-й чел. заказал " + pizza + "\n");
 
		pizza = chicagoStore.orderPizza("veggie");
		System.out.println("2-й чел. заказал " + pizza + "\n");
	}
}
