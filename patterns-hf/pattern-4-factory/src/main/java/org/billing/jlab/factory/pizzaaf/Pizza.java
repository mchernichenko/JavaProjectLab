package org.billing.jlab.factory.pizzaaf;

public abstract class Pizza {
	String name;

	Dough dough;
	Sauce sauce;
	Veggies veggies[];
	Cheese cheese;
	Pepperoni pepperoni;
	Clams clam;

	/**
	 * Абстрактный метод реализующий интерфейс абстрактной фабрики т.е.
	 * Метод инициализации объектов-атрибутов: Dough, Sauce, Veggies, Cheese, Pepperoni, Clams
	 * с помощью абстрактной фабрики
	 */
	abstract void prepare();

	void bake() {
		System.out.println("bake(): Bake for 25 minutes at 350");
	}

	void cut() {
		System.out.println("cut(): Cutting the pizza into diagonal slices");
	}

	void box() {
		System.out.println("box(): Place pizza in official PizzaStore box");
	}

	void setName(String name) {
		this.name = name;
	}

	String getName() {
		return name;
	}

	public String toString() {
		StringBuffer result = new StringBuffer();
		result.append("Состав пиццы: " + name + "\n");
		if (dough != null) {
			result.append("Тесто: " + dough);
			result.append("\n");
		}
		if (sauce != null) {
			result.append("Соус: " + sauce);
			result.append("\n");
		}
		if (cheese != null) {
			result.append("Сыр: " + cheese);
			result.append("\n");
		}
		if (veggies != null) {
			result.append("Набор специй: ");
			for (int i = 0; i < veggies.length; i++) {
				result.append(veggies[i]);
				if (i < veggies.length-1) {
					result.append(", ");
				}
			}
			result.append("\n");
		}
		if (clam != null) {
			result.append("Слизняки: " + clam);
			result.append("\n");
		}
		if (pepperoni != null) {
			result.append("Колбаски: " + pepperoni);
			result.append("\n");
		}
		return result.toString();
	}
}
